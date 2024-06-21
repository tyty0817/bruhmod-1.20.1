package name.bruhmod.compat.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import name.bruhmod.recipe.NaturalRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

@SuppressWarnings("unused")
public class ModREIEntrypoint implements REIClientPlugin {

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
