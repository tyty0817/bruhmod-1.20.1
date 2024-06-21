package name.bruhmod.compat.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import name.bruhmod.recipe.NaturalRecipe;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static name.bruhmod.Mod.MOD_ID;

public class NaturalREIDisplay implements Display {

    public static final CategoryIdentifier<NaturalREIDisplay> CATEGORY = CategoryIdentifier.of(Mod.MOD_ID, "plugins/natural");

    protected final NaturalRecipe recipe;
    protected List<EntryIngredient> inputs;
    protected EntryIngredient output;

    public NaturalREIDisplay(NaturalRecipe recipe) {
        this.recipe = recipe;

        this.inputs = recipe.getInput().stream().map(stack -> {
            ItemStack[] stacks = stack.ingredient().getItems();
            EntryIngredient.Builder result = EntryIngredient.builder(stacks.length);
            Arrays.stream(stacks).forEach(i -> {
                i.setCount(stack.count());
                result.add(EntryStacks.of(i));
            });
            return result.build();
        }).toList();

        this.output = EntryIngredient.of(EntryStacks.of(recipe.getOutput()));
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return inputs;
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return Collections.singletonList(output);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CATEGORY;
    }
}
