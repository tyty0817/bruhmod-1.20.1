package name.bruhmod.datagen;

import name.bruhmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import name.bruhmod.blocks.ModBlocks;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createGenericCube(ModBlocks.MYTHRIL_ORE);
        blockStateModelGenerator.createGenericCube(ModBlocks.DEEPSLATE_MYTHRIL_ORE);
        blockStateModelGenerator.createGenericCube(ModBlocks.MYTHRIL_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator){

        /*
        ITEMS
         */
        itemModelGenerator.generateFlatItem(ModItems.MYTHRIL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MYTHRIL_DUST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MYTHRIL_FRAGMENT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MYTHRIL_UPGRADE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.EMPTY_GEM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.EXPLOSIVE_JELLY, ModelTemplates.FLAT_ITEM);


        itemModelGenerator.generateFlatItem(ModItems.BARRACKS_MAP, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CARPENTER_MAP, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.WITCH_HUT_MAP, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.WIZARD_TOWER_MAP, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PORTAL_TOWER_MAP, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MESS_HALL_MAP, ModelTemplates.FLAT_ITEM);


        itemModelGenerator.generateFlatItem(ModItems.CORRUPTED_SLAG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MYSTERIOUS_CLUB, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CORRUPTED_CROWN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.JEWEL_OF_CORRUPTION, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BRANCH_OF_CORRUPTION, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.LIGHTNING_IN_A_BOTTLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.VOLATILE_CLAW, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CHARGED_CLAW, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.VOLATILE_PILLAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MAELSTROM, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.SPIRITED_BLUDGEON, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PRONGED_CROWN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.WIND_GEM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.WHIRLWIND_SASH, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MONKS_CUDGEL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SHILLELAGH, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.CLOUD_IN_A_BOTTLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.UNSTABLE_GEM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DYING_LIGHT, ModelTemplates.FLAT_ITEM);




        itemModelGenerator.generateFlatItem(ModItems.MUSIC_DISC_FALLOUT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MUSIC_DISC_BLACK_MOON, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MUSIC_DISC_DARK_WOODS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MUSIC_DISC_NIGHT_OWL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MUSIC_DISC_OLD_KING, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MUSIC_DISC_THE_RANGER, ModelTemplates.FLAT_ITEM);



        itemModelGenerator.generateFlatItem(ModItems.BRITISH_MAN_SPAWN_EGG,
                new ModelTemplate(Optional.of(ResourceLocation.parse("item/template_spawn_egg")), Optional.empty()));


        /*
        ARMOR
         */
        ModItems.MYTHRIL_STUDDED_ARMOR.values().forEach(itemModelGenerator::generateArmorTrims);
        ModItems.MYTHRIL_INFUSED_ARMOR.values().forEach(itemModelGenerator::generateArmorTrims);


    }
}
