package name.bruhmod.recipe.staff;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.MapCodec;
import name.bruhmod.LeMod;
import name.bruhmod.entity.StaffRecipeTicker;
import name.bruhmod.recipe.natural.NaturalSources;
import name.bruhmod.recipe.util.IngredientBlock;
import name.bruhmod.recipe.util.IngredientStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StaffRecipe implements Recipe<StaffRecipeInput> {

    public static final ResourceLocation ID = LeMod.idOf("staff");
    public static RecipeSerializer<StaffRecipe> SERIALIZER = new StaffRecipe.Serializer();
    public static RecipeType<StaffRecipe> TYPE = new RecipeType<>(){
        public String toString() {
            return ID.toString();
        }
    };


    private final NonNullList<IngredientStack> input;
    private final NonNullList<Holder<MobEffect>> effects;
    private final NonNullList<IngredientBlock> blocks;
    private final ItemStack output;


    public StaffRecipe(NonNullList<IngredientStack> input, NonNullList<Holder<MobEffect>> effects, NonNullList<IngredientBlock> blocks, ItemStack output) {
        this.input = input;
        this.effects = effects;
        this.blocks = blocks;
        this.output = output;
    }

    public static void craftAtPosition(Level level, AABB bounds, PotionContents potion) {

        Optional<Pair<StaffRecipeInput, StaffRecipe>> recipe = BlockPos.betweenClosedStream(bounds).filter(pos -> level.getBlockState(pos).is(Blocks.NOTE_BLOCK)).map(pos -> new StaffRecipeInput(level, pos, potion)).flatMap(input -> level.getRecipeManager().getAllRecipesFor(TYPE).stream().filter(r -> r.value().matches(input, level)).map(r -> new Pair<>(input, r.value()))).findFirst();

        if (recipe.isPresent()) {
            level.addFreshEntity(new StaffRecipeTicker(level, recipe.get()));
        }

    }

    @Override
    public boolean matches(StaffRecipeInput input, Level level) {
        boolean ingredientsMatch = this.ingredientsMatch(input.getItems());
        boolean effectsMatch = StreamSupport.stream(input.potion.getAllEffects().spliterator(), false).map(MobEffectInstance::getEffect).allMatch(this.effects::contains);
        boolean blocksMatch = this.blocksMatch(input.neighbors);
        return effectsMatch & blocksMatch && ingredientsMatch;
    }

    private boolean ingredientsMatch(Stream<ItemStack> items) {
        ArrayList<ItemStack> copy = new ArrayList<>(items.toList());
        for (IngredientStack ing : this.input) {
            boolean found = false;
            for (ItemStack stack : copy) {
                if (ing.ingredient().test(stack) && stack.getCount() >= ing.count()) {
                    found = true;
                    stack.setCount(stack.getCount() - ing.count());
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    private boolean blocksMatch(Block[] neighbors) {
        ArrayList<Block> copy = new ArrayList<>(Arrays.asList(neighbors));
        for (IngredientBlock ing : this.blocks) {
            copy.stream().filter(ing::test).findAny().ifPresent(copy::remove);
            if (copy.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack assemble(StaffRecipeInput input, HolderLookup.Provider registries) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return this.getOutput();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return TYPE;
    }

    public ItemStack getOutput() {
        return output.copy();
    }

    public static class Serializer
            implements RecipeSerializer<StaffRecipe> {

        @Override
        public MapCodec<StaffRecipe> codec() {
            return null;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, StaffRecipe> streamCodec() {
            return null;
        }
    }
}
