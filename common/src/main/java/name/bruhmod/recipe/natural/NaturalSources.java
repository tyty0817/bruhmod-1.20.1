package name.bruhmod.recipe.natural;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public record NaturalSources(
        NonNullList<Holder<DamageType>> damage,
        NonNullList<Holder<Potion>> potion,
        boolean cauldron
) {

    public static NaturalSources ofDamage(Holder<DamageType> damage) {
        return new NaturalSources(NonNullList.withSize(1, damage), NonNullList.create(), false);
    }

    public static NaturalSources ofPotion(Optional<Holder<Potion>> potion) {
        return new NaturalSources(NonNullList.create(), potion.map(p -> NonNullList.withSize(1, p)).orElse(NonNullList.create()), false);
    }

    public static NaturalSources ofCauldron() {
        return new NaturalSources(NonNullList.create(), NonNullList.create(), true);
    }

    private static <S> MapCodec<NonNullList<S>> optList(Codec<S> codec, String name) {
        return codec.listOf().optionalFieldOf(name).flatXmap(r -> toNonNullList(r.orElse(NonNullList.create())), r -> DataResult.success(Optional.of(r)));
    }

    private static final Codec<NaturalSources> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    optList(DamageType.CODEC, "damage").forGetter(NaturalSources::damage),
                    optList(Potion.CODEC, "potions").forGetter(NaturalSources::potion),
                    Codec.BOOL.optionalFieldOf("cauldron").xmap(o -> o.orElse(false), Optional::of).forGetter(NaturalSources::cauldron)
            ).apply(instance, NaturalSources::new)
    );

    public static final Codec<NaturalSources> STRICT_CODEC = CODEC.validate(NaturalSources::validate);

    private static <T> boolean otherListContainsAll(Registry<T> registry, NonNullList<Holder<T>> self, NonNullList<Holder<T>> other) {
        return self.stream().allMatch(t -> other.stream().anyMatch(o -> t.is(registry.getKey(o.value()))));
    }

    public boolean matchAnyIn(NaturalSources other, Level world) {
        return
                otherListContainsAll(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE), this.damage, other.damage)
                || otherListContainsAll(world.registryAccess().registryOrThrow(Registries.POTION), this.potion, other.potion)
                || (this.cauldron && other.cauldron)
//                || this.effects.stream().anyMatch(type -> other.effects.stream().anyMatch(o -> type.is(world.registryAccess().registryOrThrow(Registries.MOB_EFFECT).getKey(o.value()))))
                ;
    }

    private static DataResult<NaturalSources> validate(NaturalSources sources) {
        return sources.damage.isEmpty() && sources.potion.isEmpty() && !sources.cauldron ? DataResult.error(() -> "A natural recipe must contain a crafting method!") : DataResult.success(sources);
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, NaturalSources> STREAM_CODEC = new StreamCodec<>() {

        private static <B extends ByteBuf, O> NonNullList<O> readList(StreamCodec<B, O> codec, int count, B buf) {
            final NonNullList<O> list = NonNullList.createWithCapacity(count);
            for (int i = 0; i < count; i++) {
                list.add(codec.decode(buf));
            }
            return list;
        }

        public @NotNull NaturalSources decode(RegistryFriendlyByteBuf buf) {

            var damage = readList(DamageType.STREAM_CODEC, buf.readVarInt(), buf);
            var potions = readList(Potion.STREAM_CODEC, buf.readVarInt(), buf);
            var cauldron = buf.readBoolean();

            // return
            return new NaturalSources(damage, potions, cauldron);
        }

        public void encode(RegistryFriendlyByteBuf buf, NaturalSources sources) {
            buf.writeVarInt(sources.damage.size());
            sources.damage.forEach(type -> DamageType.STREAM_CODEC.encode(buf, type));

            buf.writeVarInt(sources.potion.size());
            sources.potion.forEach(potion -> Potion.STREAM_CODEC.encode(buf, potion));

            buf.writeBoolean(sources.cauldron);
        }
    };

    public static <O> DataResult<NonNullList<O>> toNonNullList(List<O> oldList) {
        NonNullList<O> newList = NonNullList.createWithCapacity(oldList.size());
        newList.addAll(oldList);
        return DataResult.success(newList);
    }

}
