package name.bruhmod.items;

import name.bruhmod.Mod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    /**
     * The item group containing all the items in the mod.
     */
    public static final ItemGroup BRUHMOD_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Mod.MOD_ID, "bruhmod"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.bruhmod.group"))
                    .icon(() -> new ItemStack(ModItems.MYTHRIL))
                    .entries((displayContext, entries) -> {

                        //------------------------------------------ITEMS-----------------------------------------------//

                        entries.add(ModItems.MYTHRIL);
                        entries.add(ModItems.MYTHRIL_DUST);
                        entries.add(ModItems.MYTHRIL_FRAGMENT);
                        entries.add(ModItems.MYTHRIL_UPGRADE);
                        entries.add(ModItems.EMPTY_GEM);
                        entries.add(ModItems.EXPLOSIVE_JELLY);


                        entries.add(ModItems.CORRUPTED_SLAG);
                        entries.add(ModItems.MYSTERIOUS_CLUB);
                        entries.add(ModItems.CORRUPTED_CROWN);
                        entries.add(ModItems.JEWEL_OF_CORRUPTION);
                        entries.add(ModItems.BRANCH_OF_CORRUPTION);

                        entries.add(ModItems.LIGHTNING_IN_A_BOTTLE);
                        entries.add(ModItems.VOLATILE_CLAW);
                        entries.add(ModItems.CHARGED_CLAW);
                        entries.add(ModItems.VOLATILE_PILLAR);
                        entries.add(ModItems.MAELSTROM);

                        entries.add(ModItems.CLOUD_IN_A_BOTTLE);
                        entries.add(ModItems.SPIRITED_BLUDGEON);
                        entries.add(ModItems.PRONGED_CROWN);
                        entries.add(ModItems.WIND_GEM);
                        entries.add(ModItems.MONKS_CUDGEL);
                        entries.add(ModItems.WHIRLWIND_SASH);
                        entries.add(ModItems.SHILLELAGH);

                        entries.add(ModItems.UNSTABLE_GEM);
                        entries.add(ModItems.DYING_LIGHT);


                        entries.add(ModItems.BRITISH_MAN_SPAWN_EGG);

                        entries.add(ModItems.MUSIC_DISC_FALLOUT);
                        entries.add(ModItems.MUSIC_DISC_BLACK_MOON);
                        entries.add(ModItems.MUSIC_DISC_DARK_WOODS);
                        entries.add(ModItems.MUSIC_DISC_NIGHT_OWL);
                        entries.add(ModItems.MUSIC_DISC_OLD_KING);
                        entries.add(ModItems.MUSIC_DISC_THE_RANGER);


                        entries.add(ModItems.BARRACKS_MAP);
                        entries.add(ModItems.CARPENTER_MAP);
                        entries.add(ModItems.WITCH_HUT_MAP);
                        entries.add(ModItems.WIZARD_TOWER_MAP);
                        entries.add(ModItems.PORTAL_TOWER_MAP);
                        entries.add(ModItems.MESS_HALL_MAP);

                        //------------------------------------------BLOCKS----------------------------------------------//

                        entries.add(ModItems.MYTHRIL_ORE);
                        entries.add(ModItems.DEEPSLATE_MYTHRIL_ORE);
                        entries.add(ModItems.MYTHRIL_BLOCK);

                        //------------------------------------------ARMOR-----------------------------------------------//

                        ModItems.MYTHRIL_STUDDED_ARMOR.values().forEach(entries::add);
                        ModItems.MYTHRIL_INFUSED_ARMOR.values().forEach(entries::add);

                    })
                    .build()
    );



    public static void registerItemGroups() {
        Mod.LOGGER.info("Registering Item Groups for " + Mod.MOD_ID);
    }
}
