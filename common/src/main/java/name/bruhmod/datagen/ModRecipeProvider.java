package name.bruhmod.datagen;

import name.bruhmod.LeMod;
import name.bruhmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import name.bruhmod.blocks.ModBlocks;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    private static final List<ItemLike> MYTHRIL_SMELTABLES = List.of(ModItems.MYTHRIL_ORE,
            ModItems.DEEPSLATE_MYTHRIL_ORE, ModItems.MYTHRIL_DUST);

    private static final List<ItemLike> DIAMOND = List.of(Items.DIAMOND);

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, future);
    }


    @Override
    public void buildRecipes(@NotNull RecipeOutput exporter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MYTHRIL)
                .requires(ModItems.MYTHRIL_FRAGMENT, 4)
                .requires(Items.DIAMOND, 4)
                .unlockedBy(RecipeProvider.getItemName(Items.DIAMOND), RecipeProvider.has(Items.DIAMOND))
                .unlockedBy(RecipeProvider.getItemName(ModItems.MYTHRIL_FRAGMENT), RecipeProvider.has(ModItems.MYTHRIL_FRAGMENT))
                .save(exporter, LeMod.idOf("mythril2"));

        //-----------------------------------------------------------------------------------------------------------------//
        //                                               Staff Recipes                                                     //

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BRANCH_OF_CORRUPTION, 1)
                .pattern("  G")
                .pattern(" C ")
                .pattern("B  ")
                .define('G', ModItems.JEWEL_OF_CORRUPTION)
                .define('C', ModItems.CORRUPTED_CROWN)
                .define('B', ModItems.MYSTERIOUS_CLUB)
                .unlockedBy(RecipeProvider.getItemName(ModItems.JEWEL_OF_CORRUPTION), RecipeProvider.has(ModItems.JEWEL_OF_CORRUPTION))
                .unlockedBy(RecipeProvider.getItemName(ModItems.CORRUPTED_CROWN), RecipeProvider.has(ModItems.CORRUPTED_CROWN))
                .unlockedBy(RecipeProvider.getItemName(ModItems.MYSTERIOUS_CLUB), RecipeProvider.has(ModItems.MYSTERIOUS_CLUB))
                .save(exporter, LeMod.idOf(RecipeProvider.getSimpleRecipeName(ModItems.BRANCH_OF_CORRUPTION)));



        //                                                Maelstrom                                                   //
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VOLATILE_CLAW, 1)
                .pattern("IR ")
                .pattern("N R")
                .pattern("BNI")
                .define('B', Items.GOLD_BLOCK)
                .define('I', Items.GOLD_INGOT)
                .define('N', Items.GOLD_NUGGET)
                .define('R', Items.RAW_GOLD)
                .unlockedBy(RecipeProvider.getItemName(Items.GOLD_BLOCK), RecipeProvider.has(Items.GOLD_BLOCK))
                .unlockedBy(RecipeProvider.getItemName(Items.GOLD_INGOT), RecipeProvider.has(Items.GOLD_INGOT))
                .unlockedBy(RecipeProvider.getItemName(Items.GOLD_NUGGET), RecipeProvider.has(Items.GOLD_NUGGET))
                .unlockedBy(RecipeProvider.getItemName(Items.RAW_GOLD), RecipeProvider.has(Items.RAW_GOLD))
                .save(exporter, LeMod.idOf(RecipeProvider.getSimpleRecipeName(ModItems.VOLATILE_CLAW)));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VOLATILE_PILLAR, 1)
                .pattern(" ID")
                .pattern("IRI")
                .pattern("FI ")
                .define('F', ModItems.MYTHRIL_FRAGMENT)
                .define('I', Items.GOLD_INGOT)
                .define('R', Items.BLAZE_ROD)
                .define('D', ModItems.MYTHRIL_DUST)
                .unlockedBy(RecipeProvider.getItemName(ModItems.MYTHRIL_FRAGMENT), RecipeProvider.has(ModItems.MYTHRIL_FRAGMENT))
                .unlockedBy(RecipeProvider.getItemName(Items.GOLD_INGOT), RecipeProvider.has(Items.GOLD_INGOT))
                .unlockedBy(RecipeProvider.getItemName(Items.BLAZE_ROD), RecipeProvider.has(Items.BLAZE_ROD))
                .unlockedBy(RecipeProvider.getItemName(ModItems.MYTHRIL_DUST), RecipeProvider.has(ModItems.MYTHRIL_DUST))
                .save(exporter, LeMod.idOf(RecipeProvider.getSimpleRecipeName(ModItems.VOLATILE_PILLAR)));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHARGED_CLAW, 1)
                .pattern("  R")
                .pattern(" M ")
                .pattern("C  ")
                .define('C', ModItems.VOLATILE_CLAW)
                .define('M', ModItems.MYTHRIL)
                .define('R', Items.LIGHTNING_ROD)
                .unlockedBy(RecipeProvider.getItemName(ModItems.VOLATILE_CLAW), RecipeProvider.has(ModItems.VOLATILE_CLAW))
                .unlockedBy(RecipeProvider.getItemName(ModItems.MYTHRIL), RecipeProvider.has(ModItems.MYTHRIL))
                .unlockedBy(RecipeProvider.getItemName(Items.LIGHTNING_ROD), RecipeProvider.has(Items.LIGHTNING_ROD))
                .save(exporter, LeMod.idOf(RecipeProvider.getSimpleRecipeName(ModItems.CHARGED_CLAW)));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAELSTROM, 1)
                .pattern("  R")
                .pattern(" M ")
                .pattern("C  ")
                .define('C', ModItems.VOLATILE_PILLAR)
                .define('M', ModItems.CHARGED_CLAW)
                .define('R', ModItems.LIGHTNING_IN_A_BOTTLE)
                .unlockedBy(RecipeProvider.getItemName(ModItems.VOLATILE_PILLAR), RecipeProvider.has(ModItems.VOLATILE_PILLAR))
                .unlockedBy(RecipeProvider.getItemName(ModItems.CHARGED_CLAW), RecipeProvider.has(ModItems.CHARGED_CLAW))
                .unlockedBy(RecipeProvider.getItemName(ModItems.LIGHTNING_IN_A_BOTTLE), RecipeProvider.has(ModItems.LIGHTNING_IN_A_BOTTLE))
                .save(exporter, LeMod.idOf(RecipeProvider.getSimpleRecipeName(ModItems.MAELSTROM)));



        //                                     MONK'S CUDGEL AND SHILLELAGH                                               //
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MONKS_CUDGEL, 1)
                .pattern("  W")
                .pattern(" P ")
                .pattern("S  ")
                .define('S', ModItems.SPIRITED_BLUDGEON)
                .define('P', ModItems.PRONGED_CROWN)
                .define('W', ModItems.WIND_GEM)
                .unlockedBy(RecipeProvider.getItemName(ModItems.SPIRITED_BLUDGEON), RecipeProvider.has(ModItems.SPIRITED_BLUDGEON))
                .unlockedBy(RecipeProvider.getItemName(ModItems.PRONGED_CROWN), RecipeProvider.has(ModItems.PRONGED_CROWN))
                .unlockedBy(RecipeProvider.getItemName(ModItems.WIND_GEM), RecipeProvider.has(ModItems.WIND_GEM))
                .save(exporter, LeMod.idOf(RecipeProvider.getSimpleRecipeName(ModItems.MONKS_CUDGEL)));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SHILLELAGH)
                .requires(ModItems.MONKS_CUDGEL, 1)
                .requires(ModItems.WHIRLWIND_SASH, 1)
                .unlockedBy(RecipeProvider.getItemName(ModItems.MONKS_CUDGEL), RecipeProvider.has(ModItems.MONKS_CUDGEL))
                .unlockedBy(RecipeProvider.getItemName(ModItems.WHIRLWIND_SASH), RecipeProvider.has(ModItems.WHIRLWIND_SASH))
                .save(exporter, LeMod.idOf(RecipeProvider.getSimpleRecipeName(ModItems.SHILLELAGH)));

        //                                               Staff Recipes                                                     //
        //-----------------------------------------------------------------------------------------------------------------//

        RecipeProvider.oreSmelting(exporter, MYTHRIL_SMELTABLES, RecipeCategory.MISC, ModItems.MYTHRIL_FRAGMENT, 0.7f, 200, "MYTHRIL_FRAGMENT");
        RecipeProvider.oreBlasting(exporter, MYTHRIL_SMELTABLES, RecipeCategory.MISC, ModItems.MYTHRIL_FRAGMENT, 0.7f, 100, "MYTHRIL_FRAGMENT");
        RecipeProvider.oreBlasting(exporter, DIAMOND, RecipeCategory.MISC, ModItems.EMPTY_GEM, 0.7f, 500, "EMPTY_GEM");

        RecipeProvider.nineBlockStorageRecipes(exporter, RecipeCategory.MISC, ModItems.MYTHRIL, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MYTHRIL_BLOCK);

        RecipeProvider.copySmithingTemplate(exporter, ModItems.MYTHRIL_UPGRADE, ModItems.MYTHRIL_FRAGMENT);

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