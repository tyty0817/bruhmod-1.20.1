package name.bruhmod.compat;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import name.bruhmod.compat.rei.NaturalREICategory;
import name.bruhmod.compat.rei.NaturalREIDisplay;
import name.bruhmod.recipe.NaturalRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

@SuppressWarnings("unused")
public class REIEntrypoint implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry helper) {
        helper.add(new NaturalREICategory());
        helper.addWorkstations(NaturalREIDisplay.CATEGORY, EntryIngredient.of(EntryStacks.of(new ItemStack(Items.TNT))));
        helper.setPlusButtonArea(NaturalREIDisplay.CATEGORY, null);
    }

    @Override
    public void registerDisplays(DisplayRegistry helper) {
        helper.registerFiller(NaturalRecipe.class, NaturalREIDisplay::new);
    }

}
