package name.modid.datagen;

import name.modid.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import name.modid.blocks.ModBlocks;
import net.minecraft.item.Item;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> MYTHRIL_SMELTABLES = List.of(ModBlocks.MYTHRIL_ORE,
            ModBlocks.DEEPSLATE_MYTHRIL_ORE, ModItems.MYTHRIL_DUST);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

        offerSmelting(exporter, MYTHRIL_SMELTABLES, RecipeCategory.MISC, ModItems.MYTHRIL_FRAGMENT,
                0.7f, 200, "MYTHRIL");
        offerBlasting(exporter, MYTHRIL_SMELTABLES, RecipeCategory.MISC, ModItems.MYTHRIL_FRAGMENT,
                0.7f, 100, "MYTHRIL");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.MYTHRIL, RecipeCategory.DECORATIONS,
                ModBlocks.MYTHRIL_BLOCK);

        offerSmithingTemplateCopyingRecipe(exporter, ModItems.MYTHRIL_UPGRADE, ModItems.MYTHRIL);

        offerMythrilUpgradeRecipe(exporter, Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, ModItems.MYTHRIL_INFUSED_BOOTS);
        offerMythrilUpgradeRecipe(exporter, Items.NETHERITE_CHESTPLATE, RecipeCategory.COMBAT, ModItems.MYTHRIL_INFUSED_CHESTPLATE);
        offerMythrilUpgradeRecipe(exporter, Items.NETHERITE_LEGGINGS, RecipeCategory.COMBAT, ModItems.MYTHRIL_INFUSED_LEGGINGS);
        offerMythrilUpgradeRecipe(exporter, Items.NETHERITE_HELMET, RecipeCategory.COMBAT, ModItems.MYTHRIL_INFUSED_HELMET);

        offerMythrilUpgradeRecipe(exporter, Items.DIAMOND_BOOTS, RecipeCategory.COMBAT, ModItems.MYTHRIL_STUDDED_BOOTS);
        offerMythrilUpgradeRecipe(exporter, Items.DIAMOND_CHESTPLATE, RecipeCategory.COMBAT, ModItems.MYTHRIL_STUDDED_CHESTPLATE);
        offerMythrilUpgradeRecipe(exporter, Items.DIAMOND_LEGGINGS, RecipeCategory.COMBAT, ModItems.MYTHRIL_STUDDED_LEGGINGS);
        offerMythrilUpgradeRecipe(exporter, Items.DIAMOND_HELMET, RecipeCategory.COMBAT, ModItems.MYTHRIL_STUDDED_HELMET);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MYTHRIL, 1)
                .pattern("SSS")
                .pattern("SRR")
                .pattern("RRR")
                .input('S', ModItems.MYTHRIL_FRAGMENT)
                .input('R', Items.DIAMOND)
                .criterion(hasItem(ModItems.MYTHRIL), conditionsFromItem(ModItems.MYTHRIL_FRAGMENT))
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MYTHRIL)));
    }
    public static void offerMythrilUpgradeRecipe(Consumer<RecipeJsonProvider> exporter, Item input, RecipeCategory category, Item result) {
        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(ModItems.MYTHRIL_UPGRADE), Ingredient.ofItems(input), Ingredient.ofItems(ModItems.MYTHRIL), category, result).criterion("has_netherite_ingot", RecipeProvider.conditionsFromItem(ModItems.MYTHRIL)).offerTo(exporter, RecipeProvider.getItemPath(result) + "_smithing");
    }

}