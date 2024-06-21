package name.bruhmod.items;

import name.bruhmod.Mod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroups {

    /**
     * The item group containing all the items in the mod.
     * TODO: re-add automatic item adder
     */
    public static final CreativeModeTab BRUHMOD_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Mod.idOf(Mod.MOD_ID),
            FabricItemGroup.builder()
                    .title(Component.translatable("itemgroup.bruhmod.group"))
                    .icon(() -> new ItemStack(ModItems.MYTHRIL))
                    .displayItems((displayContext, entries) -> {

                        //------------------------------------------ITEMS-----------------------------------------------//

                        entries.accept(ModItems.MYTHRIL);
                        entries.accept(ModItems.MYTHRIL_DUST);
                        entries.accept(ModItems.MYTHRIL_FRAGMENT);
                        entries.accept(ModItems.MYTHRIL_UPGRADE);
                        entries.accept(ModItems.EMPTY_GEM);
                        entries.accept(ModItems.EXPLOSIVE_JELLY);


                        entries.accept(ModItems.CORRUPTED_SLAG);
                        entries.accept(ModItems.MYSTERIOUS_CLUB);
                        entries.accept(ModItems.CORRUPTED_CROWN);
                        entries.accept(ModItems.JEWEL_OF_CORRUPTION);
                        entries.accept(ModItems.BRANCH_OF_CORRUPTION);

                        entries.accept(ModItems.LIGHTNING_IN_A_BOTTLE);
                        entries.accept(ModItems.VOLATILE_CLAW);
                        entries.accept(ModItems.CHARGED_CLAW);
                        entries.accept(ModItems.VOLATILE_PILLAR);
                        entries.accept(ModItems.MAELSTROM);

                        entries.accept(ModItems.CLOUD_IN_A_BOTTLE);
                        entries.accept(ModItems.SPIRITED_BLUDGEON);
                        entries.accept(ModItems.PRONGED_CROWN);
                        entries.accept(ModItems.WIND_GEM);
                        entries.accept(ModItems.MONKS_CUDGEL);
                        entries.accept(ModItems.WHIRLWIND_SASH);
                        entries.accept(ModItems.SHILLELAGH);

                        entries.accept(ModItems.UNSTABLE_GEM);
                        entries.accept(ModItems.DYING_LIGHT);


                        entries.accept(ModItems.BRITISH_MAN_SPAWN_EGG);

                        entries.accept(ModItems.MUSIC_DISC_FALLOUT);
                        entries.accept(ModItems.MUSIC_DISC_BLACK_MOON);
                        entries.accept(ModItems.MUSIC_DISC_DARK_WOODS);
                        entries.accept(ModItems.MUSIC_DISC_NIGHT_OWL);
                        entries.accept(ModItems.MUSIC_DISC_OLD_KING);
                        entries.accept(ModItems.MUSIC_DISC_THE_RANGER);


                        entries.accept(ModItems.BARRACKS_MAP);
                        entries.accept(ModItems.CARPENTER_MAP);
                        entries.accept(ModItems.WITCH_HUT_MAP);
                        entries.accept(ModItems.WIZARD_TOWER_MAP);
                        entries.accept(ModItems.PORTAL_TOWER_MAP);
                        entries.accept(ModItems.MESS_HALL_MAP);

                        //------------------------------------------BLOCKS----------------------------------------------//

                        entries.accept(ModItems.MYTHRIL_ORE);
                        entries.accept(ModItems.DEEPSLATE_MYTHRIL_ORE);
                        entries.accept(ModItems.MYTHRIL_BLOCK);

                        //------------------------------------------ARMOR-----------------------------------------------//

                        ModItems.MYTHRIL_STUDDED_ARMOR.values().forEach(entries::accept);
                        ModItems.MYTHRIL_INFUSED_ARMOR.values().forEach(entries::accept);

                    })
                    .build()
    );



    public static void registerItemGroups() {
        Mod.LOGGER.info("Registering Item Groups for " + Mod.MOD_ID);
    }
}
