package name.modid.items;

import name.modid.Mod;
import name.modid.sound.ModSounds;
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
            new Identifier(Mod.MOD_ID, "bruhmod"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.bruhmod.group"))
                    .icon(() -> new ItemStack(ModItems.MYTHRIL))
                    .entries((displayContext, entries) -> {
                        /*
                        ITEMS
                         */
                        entries.add(ModItems.MYTHRIL);
                        entries.add(ModItems.MYTHRIL_DUST);
                        entries.add(ModItems.MYTHRIL_FRAGMENT);
                        entries.add(ModItems.MYTHRIL_UPGRADE);

                        entries.add(ModItems.STAFF_BASE);
                        entries.add(ModItems.STAFF_NECK);
                        entries.add(ModItems.STAFF_CROWN);
                        entries.add(ModItems.STAFF_GEM);


                        entries.add(ModItems.BRITISH_MAN_SPAWN_EGG);
                        entries.add(ModItems.GERIATRIC_TAVERN_MUSIC_DISC);
                        entries.add(ModItems.LIGHTNING_STICK);
                        entries.add(ModItems.BOTTLE_OF_LIGHTNING);
                        entries.add(ModItems.BARRACKS_MAP);
                        /*
                        BLOCKS
                         */
                        entries.add(ModItems.MYTHRIL_ORE);
                        entries.add(ModItems.DEEPSLATE_MYTHRIL_ORE);
                        entries.add(ModItems.MYTHRIL_BLOCK);
                        /*
                        ARMOR
                         */
                        entries.add(ModItems.MYTHRIL_STUDDED_HELMET);
                        entries.add(ModItems.MYTHRIL_STUDDED_CHESTPLATE);
                        entries.add(ModItems.MYTHRIL_STUDDED_LEGGINGS);
                        entries.add(ModItems.MYTHRIL_STUDDED_BOOTS);

                        entries.add(ModItems.MYTHRIL_INFUSED_HELMET);
                        entries.add(ModItems.MYTHRIL_INFUSED_CHESTPLATE);
                        entries.add(ModItems.MYTHRIL_INFUSED_LEGGINGS);
                        entries.add(ModItems.MYTHRIL_INFUSED_BOOTS);
                    })
                    .build()
    );



    public static void registerItemGroups() {
        Mod.LOGGER.info("Registering Item Groups for " + Mod.MOD_ID);
    }
}
