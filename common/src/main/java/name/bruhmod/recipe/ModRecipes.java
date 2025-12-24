package name.bruhmod.recipe;

import com.mojang.datafixers.util.Pair;
import name.bruhmod.recipe.natural.NaturalRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.function.BiConsumer;

public class ModRecipes {

    public static <T> void register(BiConsumer<ResourceLocation, Pair<RecipeType<?>, RecipeSerializer<?>>> registerer) {;
        registerer.accept(NaturalRecipe.ID, Pair.of(NaturalRecipe.TYPE, NaturalRecipe.SERIALIZER));
    }

}
