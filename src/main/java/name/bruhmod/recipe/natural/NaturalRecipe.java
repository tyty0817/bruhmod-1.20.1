package name.bruhmod.recipe.natural;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import name.bruhmod.Mod;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;


import java.util.*;

/**
 * TODO: center crafted item using positions of ingredients
 * TODO: add crafting via lingering potion tick in mixin
 * TODO: add sets of damage/effect sources instead of checking for any (like how item ingredients are processed)
 */
public class NaturalRecipe implements Recipe<NaturalInventory> {

    public static void register() {

    }

    public static final String ID = "natural";
    public static final RecipeSerializer<NaturalRecipe> SERIALIZER = Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Mod.idOf(ID), new Serializer());
    public static final RecipeType<NaturalRecipe> TYPE = registerRecipeType(ID);

    private final NaturalSources sources;
    private final NonNullList<IngredientStack> input;
    private final ItemStack output;

    public NaturalRecipe(NaturalSources sources, NonNullList<IngredientStack> input, ItemStack output) {
        this.sources = sources;
        this.input = input;
        this.output = output;
    }

    /**
     * Check if this recipe matches with the ingredients from the specified inventory.
     * @param inventory The inventory the recipe should compare ingredients against.
     * @return The list of slots of which items should be removed from when crafting, or an empty list specifying that there was no match.
     */
    public List<Pair<Integer, Integer>> matchWithRemovals(NaturalInventory inventory, Level world) {
        if (!sources.match(inventory.source, world)) {
            return new ArrayList<>();
        }

        ArrayList<Pair<Integer, Integer>> removals = new ArrayList<>();

        for (IngredientStack stack : this.input) {
            int remaining = stack.count();
            for (int slot = 0; slot < inventory.size(); slot++) {
                ItemStack invStack = inventory.getItem(slot);
                if (stack.ingredient().test(invStack)) {
                    int taken = Math.min(remaining, invStack.getCount());
                    removals.add(new Pair<>(slot, taken));
                    remaining -= taken;
                }
            }
            if (remaining > 0) {
                return new ArrayList<>();
            }
        }
        return removals;
    }

    public static Comparator<? super RecipeHolder<NaturalRecipe>> sort() {
        return Comparator.comparingInt(recipe -> -recipe.value().input.stream().mapToInt(IngredientStack::count).sum());
    }

    public static void craftAtPosition(Level world, AABB box, NaturalSources source) {
        NaturalInventory inventory = new NaturalInventory(source, world.getEntitiesOfClass(ItemEntity.class, box));

        // get all matched recipes
        // then sort them by most ingredients first (in case of duplicate ingredients, the larger one should be used)
        // then, for each recipe, get the times it should be crafted, removing items used each time
        // then spawn the item after

        world.getRecipeManager().getRecipesFor(TYPE, inventory, world).stream().sorted(sort()).forEachOrdered(recipe -> {
            List<Pair<Integer, Integer>> removals;
            int times = 0;
            while (!(removals = recipe.value().matchWithRemovals(inventory, world)).isEmpty()) {
                removals.forEach(pair -> inventory.removeStack(pair.getFirst(), pair.getSecond()));
                times++;
            }
            ItemStack output = recipe.value().getResultItem(world.registryAccess());
            output.setCount(output.getCount() * times);
            Vec3 center = box.getCenter();
            ItemEntity entity = new ItemEntity(world, center.x, center.y, center.z, output, 0.0, 0.3, 0.0);
            entity.setInvulnerable(true);
            world.addFreshEntity(entity);
        });
    }

    @Override
    public boolean matches(NaturalInventory inventory, Level world) {
        return !matchWithRemovals(inventory, world).isEmpty();
    }

    @Override
    public @NotNull ItemStack assemble(NaturalInventory recipeInput, HolderLookup.Provider provider) {
        return this.getOutput();
    }

    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String path) {
        ResourceLocation id = Mod.idOf(path);
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, id, new RecipeType<T>(){

            public String toString() {
                return id.toString();
            }
        });
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) { return true; }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.Provider provider) {
        return getOutput();
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return TYPE;
    }

    public NonNullList<IngredientStack> getInput() {
        NonNullList<IngredientStack> ing = NonNullList.create();
        ing.addAll(input);
        return ing;
    }

    public ItemStack getOutput() {
        return output.copy();
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ing = NonNullList.create();
        this.input.stream().map(IngredientStack::ingredient).forEach(ing::add);
        return ing;
    }

    public static class Serializer
            implements RecipeSerializer<NaturalRecipe> {

        public static final MapCodec<NaturalRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                NaturalSources.CODEC.fieldOf("sources").forGetter((recipe) -> recipe.sources),
                IngredientStack.CODEC.listOf().fieldOf("ingredients").flatXmap(NaturalSources::toNonNullList, DataResult::success).forGetter((recipe) -> recipe.input),
                ItemStack.STRICT_SINGLE_ITEM_CODEC.fieldOf("result").forGetter((recipe) -> recipe.output)
        ).apply(instance, NaturalRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, NaturalRecipe> PACKET_CODEC = new StreamCodec<>() {

            @Override
            public void encode(RegistryFriendlyByteBuf buf, NaturalRecipe value) {
                NaturalSources.STREAM_CODEC.encode(buf, value.sources);
                buf.writeVarInt(value.input.size());
                value.input.forEach(stack -> IngredientStack.STREAM_CODEC.encode(buf, stack));
                ItemStack.STREAM_CODEC.encode(buf, value.output);
            }

            public @NotNull NaturalRecipe decode(RegistryFriendlyByteBuf buf) {
                NaturalSources sources = NaturalSources.STREAM_CODEC.decode(buf);
                int inputSize = buf.readVarInt();
                NonNullList<IngredientStack> input = NonNullList.withSize(inputSize, IngredientStack.EMPTY);
                input.replaceAll((i) -> IngredientStack.STREAM_CODEC.decode(buf));
                ItemStack output = ItemStack.STREAM_CODEC.decode(buf);
                return new NaturalRecipe(sources, input, output);
            }
        };

        @Override
        public @NotNull MapCodec<NaturalRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, NaturalRecipe> streamCodec() {
            return PACKET_CODEC;
        }
    }
}
