package name.bruhmod.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import name.bruhmod.items.ModItems;
import name.bruhmod.recipe.NaturalRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import static name.bruhmod.Mod.MOD_ID;

public class NaturalRecipeJEICategory implements IRecipeCategory<NaturalRecipe> {

    public static final RecipeType<NaturalRecipe> TYPE = RecipeType.create(MOD_ID, NaturalRecipe.ID, NaturalRecipe.class);
    private final Component title = Component.translatable(MOD_ID + ".itemsearch." + NaturalRecipe.ID);
    private final IDrawable background, icon;

    public NaturalRecipeJEICategory(IGuiHelper helper) {
        background = helper.createBlankDrawable(114, 104);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, ModItems.LIGHTNING_IN_A_BOTTLE.getDefaultInstance());
    }

    @Override
    public @NotNull RecipeType<NaturalRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return title;
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, NaturalRecipe recipe, IFocusGroup focuses) {
        final int centerX = 48, centerY = 45;
        List<NaturalRecipe.IngredientStack> inputs = recipe.getInput();
        double angle = 360.0 / inputs.size();
//        builder.addSlot(RecipeIngredientRole.CATALYST, (int))
//        builder.addSlot();
        final double distance = 32.0;
        for (int i = 0; i < inputs.size(); i++) {
            NaturalRecipe.IngredientStack stack = inputs.get(i);
            List<ItemStack> ing = Arrays.asList(stack.ingredient().getItems());
            ing.forEach(s -> s.setCount(stack.count()));
            builder.addSlot(RecipeIngredientRole.INPUT, centerX + (int) (distance * Math.cos(i * angle)), centerY + (int) (distance * Math.sin(i * angle))).addItemStacks(ing);
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 10).addItemStack(recipe.getOutput());
    }
}