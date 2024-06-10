package name.bruhmod.datagen;

import name.bruhmod.items.custom.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import name.bruhmod.blocks.ModBlocks;

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
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MYTHRIL)
                .input(ModItems.MYTHRIL_FRAGMENT, 4)
                .input(Items.DIAMOND, 4)
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .criterion(hasItem(ModItems.MYTHRIL_FRAGMENT), conditionsFromItem(ModItems.MYTHRIL_FRAGMENT))
                .offerTo(exporter, new Identifier("mythril2"));

        //-----------------------------------------------------------------------------------------------------------------//
        //                                               Staff Recipes                                                     //

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BRANCH_OF_CORRUPTION, 1)
                .pattern("  G")
                .pattern(" C ")
                .pattern("B  ")
                .input('G', ModItems.JEWEL_OF_CORRUPTION)
                .input('C', ModItems.CORRUPTED_CROWN)
                .input('B', ModItems.MYSTERIOUS_CLUB)
                .criterion(hasItem(ModItems.JEWEL_OF_CORRUPTION), conditionsFromItem(ModItems.JEWEL_OF_CORRUPTION))
                .criterion(hasItem(ModItems.CORRUPTED_CROWN), conditionsFromItem(ModItems.CORRUPTED_CROWN))
                .criterion(hasItem(ModItems.MYSTERIOUS_CLUB), conditionsFromItem(ModItems.MYSTERIOUS_CLUB))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BRANCH_OF_CORRUPTION)));



        //                                                Maelstrom                                                   //
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.VOLATILE_CLAW, 1)
                .pattern("IR ")
                .pattern("N R")
                .pattern("BNI")
                .input('B', Items.GOLD_BLOCK)
                .input('I', Items.GOLD_INGOT)
                .input('N', Items.GOLD_NUGGET)
                .input('R', Items.RAW_GOLD)
                .criterion(hasItem(Items.GOLD_BLOCK), conditionsFromItem(Items.GOLD_BLOCK))
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(Items.GOLD_NUGGET), conditionsFromItem(Items.GOLD_NUGGET))
                .criterion(hasItem(Items.RAW_GOLD), conditionsFromItem(Items.RAW_GOLD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.VOLATILE_CLAW)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.VOLATILE_PILLAR, 1)
                .pattern(" ID")
                .pattern("IRI")
                .pattern("FI ")
                .input('F', ModItems.MYTHRIL_FRAGMENT)
                .input('I', Items.GOLD_INGOT)
                .input('R', Items.BLAZE_ROD)
                .input('D', ModItems.MYTHRIL_DUST)
                .criterion(hasItem(ModItems.MYTHRIL_FRAGMENT), conditionsFromItem(ModItems.MYTHRIL_FRAGMENT))
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(Items.BLAZE_ROD), conditionsFromItem(Items.BLAZE_ROD))
                .criterion(hasItem(ModItems.MYTHRIL_DUST), conditionsFromItem(ModItems.MYTHRIL_DUST))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.VOLATILE_PILLAR)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CHARGED_CLAW, 1)
                .pattern("  R")
                .pattern(" M ")
                .pattern("C  ")
                .input('C', ModItems.VOLATILE_CLAW)
                .input('M', ModItems.MYTHRIL)
                .input('R', Items.LIGHTNING_ROD)
                .criterion(hasItem(ModItems.VOLATILE_CLAW), conditionsFromItem(ModItems.VOLATILE_CLAW))
                .criterion(hasItem(ModItems.MYTHRIL), conditionsFromItem(ModItems.MYTHRIL))
                .criterion(hasItem(Items.LIGHTNING_ROD), conditionsFromItem(Items.LIGHTNING_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CHARGED_CLAW)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MAELSTROM, 1)
                .pattern("  R")
                .pattern(" M ")
                .pattern("C  ")
                .input('C', ModItems.VOLATILE_PILLAR)
                .input('M', ModItems.CHARGED_CLAW)
                .input('R', ModItems.LIGHTNING_IN_A_BOTTLE)
                .criterion(hasItem(ModItems.VOLATILE_PILLAR), conditionsFromItem(ModItems.VOLATILE_PILLAR))
                .criterion(hasItem(ModItems.CHARGED_CLAW), conditionsFromItem(ModItems.CHARGED_CLAW))
                .criterion(hasItem(ModItems.LIGHTNING_IN_A_BOTTLE), conditionsFromItem(ModItems.LIGHTNING_IN_A_BOTTLE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MAELSTROM)));



        //                                     MONK'S CUDGEL AND SHILLELAGH                                               //
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MONKS_CUDGEL, 1)
                .pattern("  W")
                .pattern(" P ")
                .pattern("S  ")
                .input('S', ModItems.SPIRITED_BLUDGEON)
                .input('P', ModItems.PRONGED_CROWN)
                .input('W', ModItems.WIND_GEM)
                .criterion(hasItem(ModItems.SPIRITED_BLUDGEON), conditionsFromItem(ModItems.SPIRITED_BLUDGEON))
                .criterion(hasItem(ModItems.PRONGED_CROWN), conditionsFromItem(ModItems.PRONGED_CROWN))
                .criterion(hasItem(ModItems.WIND_GEM), conditionsFromItem(ModItems.WIND_GEM))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MONKS_CUDGEL)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SHILLELAGH)
                .input(ModItems.MONKS_CUDGEL, 1)
                .input(ModItems.WHIRLWIND_SASH, 1)
                .criterion(hasItem(ModItems.MONKS_CUDGEL), conditionsFromItem(ModItems.MONKS_CUDGEL))
                .criterion(hasItem(ModItems.WHIRLWIND_SASH), conditionsFromItem(ModItems.WHIRLWIND_SASH))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.SHILLELAGH)));

        //                                               Staff Recipes                                                     //
        //-----------------------------------------------------------------------------------------------------------------//

        offerSmelting(exporter, MYTHRIL_SMELTABLES, RecipeCategory.MISC, ModItems.MYTHRIL_FRAGMENT,
                0.7f, 200, "MYTHRIL_FRAGMENT");
        offerBlasting(exporter, MYTHRIL_SMELTABLES, RecipeCategory.MISC, ModItems.MYTHRIL_FRAGMENT,
                0.7f, 100, "MYTHRIL_FRAGMENT");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.MYTHRIL, RecipeCategory.DECORATIONS, ModBlocks.MYTHRIL_BLOCK);

        offerSmithingTemplateCopyingRecipe(exporter, ModItems.MYTHRIL_UPGRADE, ModItems.MYTHRIL_FRAGMENT);

        offerMythrilUpgradeRecipe(exporter, Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, ModItems.MYTHRIL_INFUSED_BOOTS);
        offerMythrilUpgradeRecipe(exporter, Items.NETHERITE_CHESTPLATE, RecipeCategory.COMBAT, ModItems.MYTHRIL_INFUSED_CHESTPLATE);
        offerMythrilUpgradeRecipe(exporter, Items.NETHERITE_LEGGINGS, RecipeCategory.COMBAT, ModItems.MYTHRIL_INFUSED_LEGGINGS);
        offerMythrilUpgradeRecipe(exporter, Items.NETHERITE_HELMET, RecipeCategory.COMBAT, ModItems.MYTHRIL_INFUSED_HELMET);

        offerMythrilUpgradeRecipe(exporter, Items.DIAMOND_BOOTS, RecipeCategory.COMBAT, ModItems.MYTHRIL_STUDDED_BOOTS);
        offerMythrilUpgradeRecipe(exporter, Items.DIAMOND_CHESTPLATE, RecipeCategory.COMBAT, ModItems.MYTHRIL_STUDDED_CHESTPLATE);
        offerMythrilUpgradeRecipe(exporter, Items.DIAMOND_LEGGINGS, RecipeCategory.COMBAT, ModItems.MYTHRIL_STUDDED_LEGGINGS);
        offerMythrilUpgradeRecipe(exporter, Items.DIAMOND_HELMET, RecipeCategory.COMBAT, ModItems.MYTHRIL_STUDDED_HELMET);
    }
    public static void offerMythrilUpgradeRecipe(Consumer<RecipeJsonProvider> exporter, Item input, RecipeCategory category, Item result) {
        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(ModItems.MYTHRIL_UPGRADE), Ingredient.ofItems(input), Ingredient.ofItems(ModItems.MYTHRIL), category, result).criterion("has_netherite_ingot", RecipeProvider.conditionsFromItem(ModItems.MYTHRIL)).offerTo(exporter, RecipeProvider.getItemPath(result) + "_smithing");
    }

}