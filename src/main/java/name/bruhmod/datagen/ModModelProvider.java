package name.bruhmod.datagen;

import name.bruhmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;
import name.bruhmod.blocks.ModBlocks;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MYTHRIL_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_MYTHRIL_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MYTHRIL_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator){

        /*
        ITEMS
         */
        itemModelGenerator.register(ModItems.MYTHRIL, Models.GENERATED);
        itemModelGenerator.register(ModItems.MYTHRIL_DUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.MYTHRIL_FRAGMENT, Models.GENERATED);
        itemModelGenerator.register(ModItems.MYTHRIL_UPGRADE, Models.GENERATED);
        itemModelGenerator.register(ModItems.EMPTY_GEM, Models.GENERATED);
        itemModelGenerator.register(ModItems.EXPLOSIVE_JELLY, Models.GENERATED);


        itemModelGenerator.register(ModItems.BARRACKS_MAP, Models.GENERATED);
        itemModelGenerator.register(ModItems.CARPENTER_MAP, Models.GENERATED);
        itemModelGenerator.register(ModItems.WITCH_HUT_MAP, Models.GENERATED);
        itemModelGenerator.register(ModItems.WIZARD_TOWER_MAP, Models.GENERATED);
        itemModelGenerator.register(ModItems.PORTAL_TOWER_MAP, Models.GENERATED);
        itemModelGenerator.register(ModItems.MESS_HALL_MAP, Models.GENERATED);


        itemModelGenerator.register(ModItems.CORRUPTED_SLAG, Models.GENERATED);
        itemModelGenerator.register(ModItems.MYSTERIOUS_CLUB, Models.GENERATED);
        itemModelGenerator.register(ModItems.CORRUPTED_CROWN, Models.GENERATED);
        itemModelGenerator.register(ModItems.JEWEL_OF_CORRUPTION, Models.GENERATED);
        itemModelGenerator.register(ModItems.BRANCH_OF_CORRUPTION, Models.GENERATED);

        itemModelGenerator.register(ModItems.LIGHTNING_IN_A_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.VOLATILE_CLAW, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHARGED_CLAW, Models.GENERATED);
        itemModelGenerator.register(ModItems.VOLATILE_PILLAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.MAELSTROM, Models.HANDHELD);

        itemModelGenerator.register(ModItems.SPIRITED_BLUDGEON, Models.GENERATED);
        itemModelGenerator.register(ModItems.PRONGED_CROWN, Models.GENERATED);
        itemModelGenerator.register(ModItems.WIND_GEM, Models.GENERATED);
        itemModelGenerator.register(ModItems.WHIRLWIND_SASH, Models.GENERATED);
        itemModelGenerator.register(ModItems.MONKS_CUDGEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.SHILLELAGH, Models.HANDHELD);

        itemModelGenerator.register(ModItems.CLOUD_IN_A_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.UNSTABLE_GEM, Models.GENERATED);
        itemModelGenerator.register(ModItems.DYING_LIGHT, Models.GENERATED);




        itemModelGenerator.register(ModItems.MUSIC_DISC_FALLOUT, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_BLACK_MOON, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_DARK_WOODS, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_NIGHT_OWL, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_OLD_KING, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_THE_RANGER, Models.GENERATED);



        itemModelGenerator.register(ModItems.BRITISH_MAN_SPAWN_EGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));


        /*
        ARMOR
         */
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MYTHRIL_STUDDED_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MYTHRIL_STUDDED_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MYTHRIL_STUDDED_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MYTHRIL_STUDDED_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MYTHRIL_INFUSED_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MYTHRIL_INFUSED_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MYTHRIL_INFUSED_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MYTHRIL_INFUSED_BOOTS));


    }
}
