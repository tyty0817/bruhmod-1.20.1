package name.bruhmod.items;

import name.bruhmod.Mod;
import name.bruhmod.blocks.ModBlocks;
import name.bruhmod.entities.ModEntities;
import name.bruhmod.items.gems.JewelOfCorruption;
import name.bruhmod.items.gems.UnstableGem;
import name.bruhmod.items.gems.WindGem;
import name.bruhmod.items.maps.*;
import name.bruhmod.items.staffs.DyingLight;
import name.bruhmod.items.staffs.Maelstrom;
import name.bruhmod.items.staffs.MonksCudgel;
import name.bruhmod.items.staffs.Shillelagh;
import name.bruhmod.sound.ModSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static name.bruhmod.Mod.MOD_ID;

public class ModItems {

    /*
     * Items
     */
    public static final Item MYTHRIL = registerItem("mythril", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    public static final Item MYTHRIL_DUST = registerItem("mythril_dust");
    public static final Item MYTHRIL_FRAGMENT = registerItem("mythril_fragment");
    public static final Item MYTHRIL_UPGRADE = registerItem("mythril_upgrade_smithing_template");
    public static final Item EMPTY_GEM = registerItem("empty_gem", new Item(new FabricItemSettings().maxCount(16)));
    public static final Item EXPLOSIVE_JELLY = registerItem("explosive_jelly", new Item(new FabricItemSettings()));


    public static final Item CORRUPTED_SLAG = registerItem("corrupted_slag", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    public static final Item MYSTERIOUS_CLUB = registerItem("mysterious_club", new Item(new FabricItemSettings().rarity(Rarity.RARE).maxCount(1)));
    public static final Item CORRUPTED_CROWN = registerItem("corrupted_crown", new Item(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item JEWEL_OF_CORRUPTION = registerItem("jewel_of_corruption", new JewelOfCorruption(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item BRANCH_OF_CORRUPTION = registerItem("branch_of_corruption", new Item(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));

    public static final Item LIGHTNING_IN_A_BOTTLE = registerItem("lightning_in_a_bottle", new LightningBottleItem(new FabricItemSettings().fireproof().maxCount(1).rarity(Rarity.UNCOMMON)));
    public static final Item VOLATILE_CLAW = registerItem("volatile_claw", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item CHARGED_CLAW = registerItem("charged_claw", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item VOLATILE_PILLAR = registerItem("volatile_pillar", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item MAELSTROM = registerItem("maelstrom", new Maelstrom(new FabricItemSettings().maxDamage(128).rarity(Rarity.RARE)));

    public static final Item UNSTABLE_GEM = registerItem("unstable_gem", new UnstableGem(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item DYING_LIGHT = registerItem("dying_light", new DyingLight((new FabricItemSettings().maxDamage(256))));

    public static final Item CLOUD_IN_A_BOTTLE = registerItem("cloud_in_a_bottle", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item SPIRITED_BLUDGEON = registerItem("spirited_bludgeon", new Item(new FabricItemSettings().maxCount(1).rarity(Rarity.UNCOMMON)));
    public static final Item PRONGED_CROWN = registerItem("pronged_crown", new Item(new FabricItemSettings().maxCount(1).rarity(Rarity.UNCOMMON)));
    public static final Item WIND_GEM = registerItem("wind_gem", new WindGem(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE).fireproof()));
    public static final Item MONKS_CUDGEL = registerItem("monks_cudgel", new MonksCudgel(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item WHIRLWIND_SASH = registerItem("whirlwind_sash", new Item(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item SHILLELAGH = registerItem("shillelagh", new Shillelagh(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));



    public static final Item MESS_HALL_MAP = registerItem("mess_hall_map", new MessHallMapItem());
    public static final Item PORTAL_TOWER_MAP = registerItem("portal_tower_map", new PortalTowerMapItem());
    public static final Item WIZARD_TOWER_MAP = registerItem("wizard_tower_map", new WizardTowerMapItem());
    public static final Item WITCH_HUT_MAP = registerItem("witch_hut_map", new WitchHutMapItem());
    public static final Item CARPENTER_MAP = registerItem("carpenter_map", new CarpenterMapItem());
    public static final Item BARRACKS_MAP = registerItem("barracks_map", new BarracksMapItem());
    /*
     * Blocks
     */
    public static final BlockItem
            MYTHRIL_ORE = registerBlock(ModBlocks.MYTHRIL_ORE),
            DEEPSLATE_MYTHRIL_ORE = registerBlock(ModBlocks.DEEPSLATE_MYTHRIL_ORE),
            MYTHRIL_BLOCK = registerBlock(ModBlocks.MYTHRIL_BLOCK);
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



    public static final Item MUSIC_DISC_FALLOUT = registerItem("music_disc_fallout", new MusicDiscItem(7, ModSounds.FALLOUT, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 24));
    public static final Item MUSIC_DISC_BLACK_MOON = registerItem("music_disc_black_moon", new MusicDiscItem(7, ModSounds.BLACK_MOON, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 203));
    public static final Item MUSIC_DISC_DARK_WOODS = registerItem("music_disc_dark_woods", new MusicDiscItem(7, ModSounds.DARK_WOODS, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 184));
    public static final Item MUSIC_DISC_NIGHT_OWL = registerItem("music_disc_night_owl", new MusicDiscItem(7, ModSounds.NIGHT_OWL, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 187));
    public static final Item MUSIC_DISC_OLD_KING = registerItem("music_disc_old_king", new MusicDiscItem(7, ModSounds.OLD_KING, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 209));
    public static final Item MUSIC_DISC_THE_RANGER = registerItem("music_disc_the_ranger", new MusicDiscItem(7, ModSounds.THE_RANGER, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 192));


    public static final Item BRITISH_MAN_SPAWN_EGG = registerItem("british_man_spawn_egg", new SpawnEggItem(ModEntities.BOSS, 0xd59890, 0xd7b4ae, new FabricItemSettings()));


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
    private static BlockItem registerBlock(Block block) {
        return Registry.register(Registries.ITEM, Registries.BLOCK.getId(block), new BlockItem(block, new Item.Settings()));
    }
}
