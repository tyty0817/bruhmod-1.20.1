package name.bruhmod.recipe.natural;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public record IngredientStack(Ingredient ingredient, int count) {

    public static final IngredientStack EMPTY = new IngredientStack(Ingredient.EMPTY, 0);

    public static final Codec<IngredientStack> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(IngredientStack::ingredient),
                    Codec.intRange(1, 99).fieldOf("count").orElse(1).forGetter(IngredientStack::count)
            ).apply(instance, IngredientStack::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, IngredientStack> STREAM_CODEC = new StreamCodec<>() {

        public @NotNull IngredientStack decode(RegistryFriendlyByteBuf buf) {
            int count = buf.readVarInt();
            if (count <= 0)
                return EMPTY;
            else return new IngredientStack(Ingredient.CONTENTS_STREAM_CODEC.decode(buf), count);
        }

        public void encode(RegistryFriendlyByteBuf buf, IngredientStack stack) {
            buf.writeVarInt(stack.count);
            if (stack.count > 0) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, stack.ingredient);
            }
        }
    };

    public String toString() {
        return count + " " +  ingredient.toString();
    }
}
