package name.bruhmod.compat.rei;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import name.bruhmod.items.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.ArrayList;

import static name.bruhmod.Mod.MOD_ID;

public class NaturalREICategory implements DisplayCategory<NaturalREIDisplay> {

    private static final EntryStack<ItemStack> ICON = EntryStacks.of(new ItemStack(ModItems.LIGHTNING_IN_A_BOTTLE));

    @Override
    public CategoryIdentifier<? extends NaturalREIDisplay> getCategoryIdentifier() {
        return NaturalREIDisplay.CATEGORY;
    }

    @Override
    public Component getTitle() {
        return Component.translatable(Mod.MOD_ID + ".recipe.natural");
    }

    @Override
    public Renderer getIcon() {
        return ICON;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public List<Widget> setupDisplay(NaturalREIDisplay display, Rectangle bounds) {
        List<EntryIngredient> inputs = display.getInputEntries();
        EntryStack<?> output = display.getOutputEntries().getFirst().getFirst();

        double seperation = 360.0 / inputs.size();
        Point center = new Point(bounds.getCenterX() - 8, bounds.getCenterY() - 6);

        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createRecipeBase(bounds));

        for (int i = 0; i < inputs.size(); i++){
            widgets.add(Widgets.createSlot(new Point(center.x + (int) Math.round(32.0 * Math.cos(i * seperation)), center.y + (int) Math.round(32.0 * Math.sin(i * seperation)))).entries(inputs.get(i)).disableBackground());
            widgets.add(Widgets.createSlot(new Point(center.x + 38, center.y - 35)).entry(output));
        }

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 114;
    }
}
