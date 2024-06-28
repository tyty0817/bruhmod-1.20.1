package name.bruhmod.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.*;
import name.bruhmod.LeMod;
import name.bruhmod.recipe.natural.NaturalRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;


@JeiPlugin
public class JEIModPlugin implements IModPlugin {

    private static final ResourceLocation ID = LeMod.idOf("main");
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new NaturalRecipeJEICategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<NaturalRecipe> recipes = Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(NaturalRecipe.TYPE).stream().sorted(NaturalRecipe.sort()).map(RecipeHolder::value).toList();
        LeMod.LOGGER.info("Registering {} recipes in JEI...", recipes.size());
        registration.addRecipes(NaturalRecipeJEICategory.TYPE, recipes);
    }



//    @Override
//    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
//        IModPlugin.super.registerRecipeCatalysts(registration);
//    }


}
