package name.bruhmod.datagen;

import name.bruhmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import name.bruhmod.blocks.ModBlocks;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    private static final List<ItemLike> MYTHRIL_SMELTABLES = List.of(ModItems.MYTHRIL_ORE,
            ModItems.DEEPSLATE_MYTHRIL_ORE, ModItems.MYTHRIL_DUST);

    private static final List<ItemLike> DIAMOND = List.of(Items.DIAMOND);

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }


    @Override
    public void buildRecipes(RecipeOutput exporter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MYTHRIL)
                .requires(ModItems.MYTHRIL_FRAGMENT, 4)
                .requires(Items.DIAMOND, 4)
                .unlockedBy(getItemName(Items.DIAMOND), FabricRecipeProvider.has(Items.DIAMOND))
                .unlockedBy(getItemName(ModItems.MYTHRIL_FRAGMENT), FabricRecipeProvider.has(ModItems.MYTHRIL_FRAGMENT))
                .save(exporter, ResourceLocation.parse("mythril2"));

        //-----------------------------------------------------------------------------------------------------------------//
        //                                               Staff Recipes                                                     //

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BRANCH_OF_CORRUPTION, 1)
                .pattern("  G")
                .pattern(" C ")
                .pattern("B  ")
                .define('G', ModItems.JEWEL_OF_CORRUPTION)
                .define('C', ModItems.CORRUPTED_CROWN)
                .define('B', ModItems.MYSTERIOUS_CLUB)
                .unlockedBy(getItemName(ModItems.JEWEL_OF_CORRUPTION), has(ModItems.JEWEL_OF_CORRUPTION))
                .unlockedBy(getItemName(ModItems.CORRUPTED_CROWN), has(ModItems.CORRUPTED_CROWN))
                .unlockedBy(getItemName(ModItems.MYSTERIOUS_CLUB), has(ModItems.MYSTERIOUS_CLUB))
                .save(exporter, ResourceLocation.parse(getSimpleRecipeName(ModItems.BRANCH_OF_CORRUPTION)));



        //                                                Maelstrom                                                   //
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VOLATILE_CLAW, 1)
                .pattern("IR ")
                .pattern("N R")
                .pattern("BNI")
                .define('B', Items.GOLD_BLOCK)
                .define('I', Items.GOLD_INGOT)
                .define('N', Items.GOLD_NUGGET)
                .define('R', Items.RAW_GOLD)
                .unlockedBy(getItemName(Items.GOLD_BLOCK), has(Items.GOLD_BLOCK))
                .unlockedBy(getItemName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .unlockedBy(getItemName(Items.GOLD_NUGGET), has(Items.GOLD_NUGGET))
                .unlockedBy(getItemName(Items.RAW_GOLD), has(Items.RAW_GOLD))
                .save(exporter, ResourceLocation.parse(getSimpleRecipeName(ModItems.VOLATILE_CLAW)));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VOLATILE_PILLAR, 1)
                .pattern(" ID")
                .pattern("IRI")
                .pattern("FI ")
                .define('F', ModItems.MYTHRIL_FRAGMENT)
                .define('I', Items.GOLD_INGOT)
                .define('R', Items.BLAZE_ROD)
                .define('D', ModItems.MYTHRIL_DUST)
                .unlockedBy(getItemName(ModItems.MYTHRIL_FRAGMENT), has(ModItems.MYTHRIL_FRAGMENT))
                .unlockedBy(getItemName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .unlockedBy(getItemName(Items.BLAZE_ROD), has(Items.BLAZE_ROD))
                .unlockedBy(getItemName(ModItems.MYTHRIL_DUST), has(ModItems.MYTHRIL_DUST))
                .save(exporter, ResourceLocation.parse(getSimpleRecipeName(ModItems.VOLATILE_PILLAR)));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHARGED_CLAW, 1)
                .pattern("  R")
                .pattern(" M ")
                .pattern("C  ")
                .define('C', ModItems.VOLATILE_CLAW)
                .define('M', ModItems.MYTHRIL)
                .define('R', Items.LIGHTNING_ROD)
                .unlockedBy(getItemName(ModItems.VOLATILE_CLAW), has(ModItems.VOLATILE_CLAW))
                .unlockedBy(getItemName(ModItems.MYTHRIL), has(ModItems.MYTHRIL))
                .unlockedBy(getItemName(Items.LIGHTNING_ROD), has(Items.LIGHTNING_ROD))
                .save(exporter, ResourceLocation.parse(getSimpleRecipeName(ModItems.CHARGED_CLAW)));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAELSTROM, 1)
                .pattern("  R")
                .pattern(" M ")
                .pattern("C  ")
                .define('C', ModItems.VOLATILE_PILLAR)
                .define('M', ModItems.CHARGED_CLAW)
                .define('R', ModItems.LIGHTNING_IN_A_BOTTLE)
                .unlockedBy(getItemName(ModItems.VOLATILE_PILLAR), has(ModItems.VOLATILE_PILLAR))
                .unlockedBy(getItemName(ModItems.CHARGED_CLAW), has(ModItems.CHARGED_CLAW))
                .unlockedBy(getItemName(ModItems.LIGHTNING_IN_A_BOTTLE), has(ModItems.LIGHTNING_IN_A_BOTTLE))
                .save(exporter, ResourceLocation.parse(getSimpleRecipeName(ModItems.MAELSTROM)));



        //                                     MONK'S CUDGEL AND SHILLELAGH                                               //
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MONKS_CUDGEL, 1)
                .pattern("  W")
                .pattern(" P ")
                .pattern("S  ")
                .define('S', ModItems.SPIRITED_BLUDGEON)
                .define('P', ModItems.PRONGED_CROWN)
                .define('W', ModItems.WIND_GEM)
                .unlockedBy(getItemName(ModItems.SPIRITED_BLUDGEON), has(ModItems.SPIRITED_BLUDGEON))
                .unlockedBy(getItemName(ModItems.PRONGED_CROWN), has(ModItems.PRONGED_CROWN))
                .unlockedBy(getItemName(ModItems.WIND_GEM), has(ModItems.WIND_GEM))
                .save(exporter, ResourceLocation.parse(getSimpleRecipeName(ModItems.MONKS_CUDGEL)));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SHILLELAGH)
                .requires(ModItems.MONKS_CUDGEL, 1)
                .requires(ModItems.WHIRLWIND_SASH, 1)
                .unlockedBy(getItemName(ModItems.MONKS_CUDGEL), has(ModItems.MONKS_CUDGEL))
                .unlockedBy(getItemName(ModItems.WHIRLWIND_SASH), has(ModItems.WHIRLWIND_SASH))
                .save(exporter, ResourceLocation.parse(getSimpleRecipeName(ModItems.SHILLELAGH)));

        //                                               Staff Recipes                                                     //
        //-----------------------------------------------------------------------------------------------------------------//

        oreSmelting(exporter, MYTHRIL_SMELTABLES, RecipeCategory.MISC, ModItems.MYTHRIL_FRAGMENT, 0.7f, 200, "MYTHRIL_FRAGMENT");
        oreBlasting(exporter, MYTHRIL_SMELTABLES, RecipeCategory.MISC, ModItems.MYTHRIL_FRAGMENT, 0.7f, 100, "MYTHRIL_FRAGMENT");
        oreBlasting(exporter, DIAMOND, RecipeCategory.MISC, ModItems.EMPTY_GEM, 0.7f, 500, "EMPTY_GEM");

        nineBlockStorageRecipes(exporter, RecipeCategory.MISC, ModItems.MYTHRIL, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MYTHRIL_BLOCK);

        copySmithingTemplate(exporter, ModItems.MYTHRIL_UPGRADE, ModItems.MYTHRIL_FRAGMENT);

        offerMythrilUpgradeRecipe(exporter, Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, ModItems.MYTHRIL_INFUSED_ARMOR.get(ArmorItem.Type.BOOTS));
        offerMythrilUpgradeRecipe(exporter, Items.NETHERITE_CHESTPLATE, RecipeCategory.COMBAT, ModItems.MYTHRIL_INFUSED_ARMOR.get(ArmorItem.Type.CHESTPLATE));
        offerMythrilUpgradeRecipe(exporter, Items.NETHERITE_LEGGINGS, RecipeCategory.COMBAT, ModItems.MYTHRIL_INFUSED_ARMOR.get(ArmorItem.Type.LEGGINGS));
        offerMythrilUpgradeRecipe(exporter, Items.NETHERITE_HELMET, RecipeCategory.COMBAT, ModItems.MYTHRIL_INFUSED_ARMOR.get(ArmorItem.Type.HELMET));

        offerMythrilUpgradeRecipe(exporter, Items.DIAMOND_BOOTS, RecipeCategory.COMBAT, ModItems.MYTHRIL_STUDDED_ARMOR.get(ArmorItem.Type.BOOTS));
        offerMythrilUpgradeRecipe(exporter, Items.DIAMOND_CHESTPLATE, RecipeCategory.COMBAT, ModItems.MYTHRIL_STUDDED_ARMOR.get(ArmorItem.Type.CHESTPLATE));
        offerMythrilUpgradeRecipe(exporter, Items.DIAMOND_LEGGINGS, RecipeCategory.COMBAT, ModItems.MYTHRIL_STUDDED_ARMOR.get(ArmorItem.Type.LEGGINGS));
        offerMythrilUpgradeRecipe(exporter, Items.DIAMOND_HELMET, RecipeCategory.COMBAT, ModItems.MYTHRIL_STUDDED_ARMOR.get(ArmorItem.Type.HELMET));
    }
    public static void offerMythrilUpgradeRecipe(RecipeOutput exporter, Item requires, RecipeCategory category, Item result) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.MYTHRIL_UPGRADE), Ingredient.of(requires), Ingredient.of(ModItems.MYTHRIL), category, result).unlocks("has_netherite_ingot", RecipeProvider.has(ModItems.MYTHRIL)).save(exporter, RecipeProvider.getItemName(result) + "_smithing");
    }

}