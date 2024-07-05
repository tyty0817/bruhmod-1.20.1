package name.bruhmod.recipe.natural;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public record NaturalSources(
        NonNullList<Holder<DamageType>> damage,
        Optional<Holder<Potion>> potion
//        NonNullList<Holder<MobEffect>> effects
) {

    public static final Codec<NaturalSources> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    DamageType.CODEC.listOf().optionalFieldOf("damage").flatXmap(r -> toNonNullList(r.orElse(NonNullList.create())), r -> DataResult.success(Optional.of(r))).forGetter(NaturalSources::damage),
                    Potion.CODEC.optionalFieldOf("potion").forGetter(NaturalSources::potion)
//                    MobEffect.CODEC.listOf().optionalFieldOf("effects").flatXmap(r -> toNonNullList(r.orElse(NonNullList.create())), r -> DataResult.success(Optional.of(r))).forGetter(NaturalSources::effects)
            ).apply(instance, NaturalSources::new)
    );

    public boolean match(NaturalSources other, Level world) {
        return
                this.damage.stream().anyMatch(type -> other.damage.stream().anyMatch(o -> type.is(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getKey(o.value()))))
                        || this.potion.stream().flatMap(p -> other.potion.stream().flatMap(o -> Optional.of(p.is(world.registryAccess().registryOrThrow(Registries.POTION).getKey(o.value()))).stream())).findFirst().orElse(false)
//                || this.effects.stream().anyMatch(type -> other.effects.stream().anyMatch(o -> type.is(world.registryAccess().registryOrThrow(Registries.MOB_EFFECT).getKey(o.value()))))
                ;
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, NaturalSources> STREAM_CODEC = new StreamCodec<>() {

        public @NotNull NaturalSources decode(RegistryFriendlyByteBuf buf) {

            int damageCount = buf.readVarInt();
            NonNullList<Holder<DamageType>> damage = NonNullList.createWithCapacity(damageCount);
            for (int i = 0; i < damageCount; i++) {
                damage.add(DamageType.STREAM_CODEC.decode(buf));
            }

            boolean potionExists = buf.readBoolean();
            Optional<Holder<Potion>> potion = potionExists ? Optional.of(Potion.STREAM_CODEC.decode(buf)) : Optional.empty();

//            int effectCount = buf.readVarInt();
//            NonNullList<Holder<MobEffect>> effects = NonNullList.createWithCapacity(effectCount);
//            for (int i = 0; i < effectCount; i++) {
//                effects.add(MobEffect.STREAM_CODEC.decode(buf));
//            }

            return new NaturalSources(damage, potion);
        }

        public void encode(RegistryFriendlyByteBuf buf, NaturalSources sources) {
            buf.writeVarInt(sources.damage.size());
            sources.damage.forEach(type -> DamageType.STREAM_CODEC.encode(buf, type));
            buf.writeBoolean(sources.potion.isPresent());
            sources.potion.ifPresent(potion -> Potion.STREAM_CODEC.encode(buf, potion));
//            buf.writeVarInt(sources.effects.size());
//            sources.effects.forEach(effect -> MobEffect.STREAM_CODEC.encode(buf, effect));
        }
    };

    public static <O> DataResult<NonNullList<O>> toNonNullList(List<O> oldList) {
        NonNullList<O> newList = NonNullList.createWithCapacity(oldList.size());
        newList.addAll(oldList);
        return DataResult.success(newList);
    }

}