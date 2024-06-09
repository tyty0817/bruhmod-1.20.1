package name.modid.items;

import name.modid.Mod;
import name.modid.blocks.ModBlocks;
import name.modid.sound.ModSounds;
import name.modid.entities.BossEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static name.modid.Mod.MOD_ID;

public class ModItems {

    /*
     * Items
     */
    public static final Item MYTHRIL = registerItem("mythril", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    public static final Item MYTHRIL_DUST = registerItem("mythril_dust");
    public static final Item MYTHRIL_FRAGMENT = registerItem("mythril_fragment");
    public static final Item MYTHRIL_UPGRADE = registerItem("mythril_upgrade_smithing_template");

    public static final Item MYSTERIOUS_CLUB = registerItem("mysterious_club");
    public static final Item CORRUPTED_CROWN = registerItem("corrupted_crown");
    public static final Item JEWEL_OF_CORRUPTION = registerItem("jewel_of_corruption");
    public static final Item BRANCH_OF_CORRUPTION = registerItem("branch_of_corruption");

    public static final Item UNSTABLE_CLAW = registerItem("unstable_claw");
    public static final Item CHARGED_CLAW = registerItem("charged_claw");
    public static final Item UNSTABLE_PILLAR = registerItem("unstable_pillar");
    public static final Item LIGHTNING_STICK = registerItem("lightning_stick", new LightningStick(new FabricItemSettings().maxDamage(128)));

    public static final Item DYING_LIGHT = registerItem("dying_light", new FireStaff((new FabricItemSettings().maxDamage(256))));
    public static final Item LIGHTNING_IN_A_BOTTLE = registerItem("lightning_in_a_bottle", new BottleOfLightning(new FabricItemSettings().fireproof().maxCount(1)));
    public static final Item WIND_STAFF = registerItem("wind_staff", new WindStaff(new FabricItemSettings().maxCount(1)));




    public static final Item BARRACKS_MAP = registerItem("barracks_map", new BarracksMapItem());
    /*
     * Blocks
     */
    public static final BlockItem
            MYTHRIL_ORE = registerBlock("mythril_ore", ModBlocks.MYTHRIL_ORE),
            DEEPSLATE_MYTHRIL_ORE = registerBlock("deepslate_mythril_ore", ModBlocks.DEEPSLATE_MYTHRIL_ORE),
            MYTHRIL_BLOCK = registerBlock("mythril_block", ModBlocks.MYTHRIL_BLOCK);
    /*
     * Armor
     */
    public static final Item MYTHRIL_STUDDED_HELMET = registerItem("mythril_studded_helmet", new ArmorItem(ModArmorMaterials.MYTHRIL_DIAMOND, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item MYTHRIL_STUDDED_CHESTPLATE = registerItem("mythril_studded_chestplate", new ArmorItem(ModArmorMaterials.MYTHRIL_DIAMOND, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item MYTHRIL_STUDDED_LEGGINGS = registerItem("mythril_studded_leggings", new ArmorItem(ModArmorMaterials.MYTHRIL_DIAMOND, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item MYTHRIL_STUDDED_BOOTS = registerItem("mythril_studded_boots", new ArmorItem(ModArmorMaterials.MYTHRIL_DIAMOND, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item MYTHRIL_INFUSED_HELMET = registerItem("mythril_infused_helmet", new ArmorItem(ModArmorMaterials.MYTHRIL_NETHERITE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item MYTHRIL_INFUSED_CHESTPLATE = registerItem("mythril_infused_chestplate", new ArmorItem(ModArmorMaterials.MYTHRIL_NETHERITE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item MYTHRIL_INFUSED_LEGGINGS = registerItem("mythril_infused_leggings", new ArmorItem(ModArmorMaterials.MYTHRIL_NETHERITE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item MYTHRIL_INFUSED_BOOTS = registerItem("mythril_infused_boots", new ArmorItem(ModArmorMaterials.MYTHRIL_NETHERITE, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item GERIATRIC_TAVERN_MUSIC_DISC = registerItem("geriatric_tavern_music_disc", new MusicDiscItem(7, ModSounds.GERIATRIC_TAVERN, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 24));

    public static final Item BRITISH_MAN_SPAWN_EGG = registerItem("british_man_spawn_egg", new SpawnEggItem(BossEntity.TYPE, 0xd59890, 0xd7b4ae, new FabricItemSettings()));


    public static void register() {
        Mod.LOGGER.info("Registering Mod Items for " + MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries ->
            entries.addAfter(Items.NETHERITE_INGOT, MYTHRIL)
        );
    }

    /**
     * Register an item with default parameters
     * @param name The ID of the item.
     * @return The item, registered
     */
    private static Item registerItem(String name) {
        return registerItem(name, new Item(new Item.Settings()));
    }

    /**
     * Register an item with custom parameters, for example if you were making armor or special items
     * @param name The ID of the item.
     * @return The item, registered
     */
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(MOD_ID, name), item);
    }

    /**
     * Register a block as an item.
     * @param block The block to be registered
     * @return The Block item, registered
     */
    private static BlockItem registerBlock(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
    }
}
