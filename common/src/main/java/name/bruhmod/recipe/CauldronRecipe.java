package name.bruhmod.recipe;

import name.bruhmod.recipe.util.WorldRecipeInput;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

//TODO
public class CauldronRecipe implements Recipe<WorldRecipeInput> {

    @Override
    public boolean matches(WorldRecipeInput naturalInventory, Level level) {
        return false;
    }

    @Override
    public ItemStack assemble(WorldRecipeInput naturalInventory, HolderLookup.Provider provider) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int u, int v) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return null;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return null;
    }
}
