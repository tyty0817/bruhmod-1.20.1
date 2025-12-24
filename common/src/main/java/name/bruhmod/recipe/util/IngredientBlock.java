package name.bruhmod.recipe.util;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

public final class IngredientBlock implements Predicate<Block> {

    private final Value[] values;

    private IngredientBlock(Stream<? extends Value> values) {
        this.values = values.toArray(Value[]::new);
    }

    public static IngredientBlock of(Block... stacks) {
        return new IngredientBlock(Arrays.stream(stacks).map(BlockValue::new));
    }

    public static IngredientBlock of(TagKey<Block> tag) {
        return new IngredientBlock(Stream.of(new TagValue(tag)));
    }

    public boolean test(Block block) {
        return Arrays.stream(values).anyMatch(p -> p.test(block));
    }

    private interface Value {

        Codec<Value> CODEC = Codec.xor(BlockValue.CODEC, TagValue.CODEC)
                .xmap(to -> to.map(l -> l, r -> r), from -> {
                    if (from instanceof TagValue v) {
                        return Either.right(v);
                    } else if (from instanceof BlockValue v) {
                        return Either.left(v);
                    } else {
                        throw new UnsupportedOperationException("This is neither a block value nor a tag value.");
                    }
                });

        boolean test(Block block);

    }

    private record BlockValue(Block block) implements Value {

        @Override
        public boolean test(Block block) {
            return this.block == block;
        }
    }

    private record TagValue(TagKey<Block> tag) implements Value {

        @Override
        public boolean test(Block block) {
            return block.builtInRegistryHolder().is(tag);
        }
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, IngredientStack> STREAM_CODEC = new StreamCodec<>() {

        public @NotNull IngredientStack decode(RegistryFriendlyByteBuf buf) {
            int count = buf.readVarInt();
            if (count <= 0)
                return IngredientStack.EMPTY;
            else return new IngredientStack(Ingredient.CONTENTS_STREAM_CODEC.decode(buf), count);
        }

        public void encode(RegistryFriendlyByteBuf buf, IngredientStack stack) {
            buf.writeVarInt(stack.count());
            if (stack.count() > 0) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, stack.ingredient());
            }
        }
    };

}
