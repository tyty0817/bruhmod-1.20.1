package name.bruhmod.items;

import name.bruhmod.Mod;
import name.bruhmod.blocks.ModBlocks;
import name.bruhmod.entities.ModEntities;
import name.bruhmod.items.gems.JewelOfCorruption;
import name.bruhmod.items.gems.UnstableGem;
import name.bruhmod.items.gems.WindGem;
import name.bruhmod.items.staffs.*;
import name.bruhmod.sound.ModSounds;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.block.jukebox.JukeboxSongs;
import net.minecraft.item.*;
import net.minecraft.block.Block;
import net.minecraft.item.map.MapDecorationTypes;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.Util;

import java.util.EnumMap;

import static name.bruhmod.Mod.MOD_ID;

public class ModItems {

    /*
     * Items
     */
    public static final Item MYTHRIL = registerItem("mythril", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item MYTHRIL_DUST = registerItem("mythril_dust");
    public static final Item MYTHRIL_FRAGMENT = registerItem("mythril_fragment");
    public static final Item MYTHRIL_UPGRADE = registerItem("mythril_upgrade_smithing_template");
    public static final Item EMPTY_GEM = registerItem("empty_gem", new Item(new Item.Settings().maxCount(16)));
    public static final Item EXPLOSIVE_JELLY = registerItem("explosive_jelly", new Item(new Item.Settings()));


    public static final Item CORRUPTED_SLAG = registerItem("corrupted_slag", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item MYSTERIOUS_CLUB = registerItem("mysterious_club", new Item(new Item.Settings().rarity(Rarity.RARE).maxCount(1)));
    public static final Item CORRUPTED_CROWN = registerItem("corrupted_crown", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item JEWEL_OF_CORRUPTION = registerItem("jewel_of_corruption", new JewelOfCorruption(new Item.Settings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item BRANCH_OF_CORRUPTION = registerItem("branch_of_corruption", new BoltCaster(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)));

    public static final Item LIGHTNING_IN_A_BOTTLE = registerItem("lightning_in_a_bottle", new LightningBottleItem(new Item.Settings().fireproof().maxCount(1).rarity(Rarity.UNCOMMON)));
    public static final Item VOLATILE_CLAW = registerItem("volatile_claw", new Item(new Item.Settings().maxCount(1)));
    public static final Item CHARGED_CLAW = registerItem("charged_claw", new Item(new Item.Settings().maxCount(1)));
    public static final Item VOLATILE_PILLAR = registerItem("volatile_pillar", new Item(new Item.Settings().maxCount(1)));
    public static final Item MAELSTROM = registerItem("maelstrom", new Maelstrom(new Item.Settings().maxDamage(128).rarity(Rarity.RARE)));

    public static final Item UNSTABLE_GEM = registerItem("unstable_gem", new UnstableGem(new Item.Settings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item DYING_LIGHT = registerItem("dying_light", new DyingLight((new Item.Settings().maxDamage(256))));

    public static final Item CLOUD_IN_A_BOTTLE = registerItem("cloud_in_a_bottle", new Item(new Item.Settings().maxCount(1)));
    public static final Item SPIRITED_BLUDGEON = registerItem("spirited_bludgeon", new Item(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));
    public static final Item PRONGED_CROWN = registerItem("pronged_crown", new Item(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));
    public static final Item WIND_GEM = registerItem("wind_gem", new WindGem(new Item.Settings().maxCount(1).rarity(Rarity.RARE).fireproof()));
    public static final Item MONKS_CUDGEL = registerItem("monks_cudgel", new MonksCudgel(new Item.Settings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item WHIRLWIND_SASH = registerItem("whirlwind_sash", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item SHILLELAGH = registerItem("shillelagh", new Shillelagh(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)));



    public static final Item MESS_HALL_MAP = registerItem("mess_hall_map", new ModMapItem("mess_hall", MapDecorationTypes.BANNER_BROWN));
    public static final Item PORTAL_TOWER_MAP = registerItem("portal_tower_map", new ModMapItem("portal_tower", MapDecorationTypes.BANNER_PURPLE));
    public static final Item WIZARD_TOWER_MAP = registerItem("wizard_tower_map", new ModMapItem("wizard_tower", MapDecorationTypes.BANNER_LIGHT_GRAY));
    public static final Item WITCH_HUT_MAP = registerItem("witch_hut_map", new ModMapItem("witch_hut", MapDecorationTypes.SWAMP_HUT));
    public static final Item CARPENTER_MAP = registerItem("carpenter_map", new ModMapItem("carpenter", MapDecorationTypes.BLUE_MARKER));
    public static final Item BARRACKS_MAP = registerItem("barracks_map", new ModMapItem("barracks", MapDecorationTypes.BANNER_BLACK));
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
    public static final EnumMap<ArmorItem.Type, ArmorItem>
            MYTHRIL_STUDDED_ARMOR = registerArmor(ModArmorMaterials.MYTHRIL_DIAMOND, new Item.Settings()),
            MYTHRIL_INFUSED_ARMOR = registerArmor(ModArmorMaterials.MYTHRIL_NETHERITE, new Item.Settings());


    public static final Item MUSIC_DISC_FALLOUT = registerMusicDisc("music_disc_fallout", ModSounds.FALLOUT, 24, 7);
    public static final Item MUSIC_DISC_BLACK_MOON = registerMusicDisc("music_disc_black_moon", ModSounds.BLACK_MOON,203, 7);
    public static final Item MUSIC_DISC_DARK_WOODS = registerMusicDisc("music_disc_dark_woods", ModSounds.DARK_WOODS, 184, 7);
    public static final Item MUSIC_DISC_NIGHT_OWL = registerMusicDisc("music_disc_night_owl", ModSounds.NIGHT_OWL, 187, 7);
    public static final Item MUSIC_DISC_OLD_KING = registerMusicDisc("music_disc_old_king", ModSounds.OLD_KING, 209, 7);
    public static final Item MUSIC_DISC_THE_RANGER = registerMusicDisc("music_disc_the_ranger", ModSounds.THE_RANGER, 192, 7);


    public static final Item BRITISH_MAN_SPAWN_EGG = registerItem("british_man_spawn_egg", new SpawnEggItem(ModEntities.BOSS, 0xd59890, 0xd7b4ae, new Item.Settings()));


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
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item);
    }

    /**
     * Register a block as an item.
     * @param block The block to be registered
     * @return The Block item, registered
     */
    private static BlockItem registerBlock(Block block) {
        return Registry.register(Registries.ITEM, Registries.BLOCK.getId(block), new BlockItem(block, new Item.Settings()));
    }

    private static EnumMap<ArmorItem.Type, ArmorItem> registerArmor(RegistryEntry<ArmorMaterial> material, Item.Settings settings) {
        EnumMap<ArmorItem.Type, ArmorItem> items = new EnumMap<>(ArmorItem.Type.class);
        Identifier id = Identifier.of(material.getIdAsString());
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            items.put(type, (ArmorItem) registerItem(id.getPath() + "_" + type.getName(), new ArmorItem(material, type, settings)));
        }
        return items;
    }

    private static Item registerMusicDisc(String name, RegistryKey<SoundEvent> track, int length, int comparatorOutput) {
//        RegistryEntry<JukeboxSong> track = registerSong(Regi)
        return registerItem(name, new Item((new Item.Settings()).maxCount(1).rarity(Rarity.RARE)/*.jukeboxPlayable(track)*/));
    }

    /*

    private static RegistryEntry<JukeboxSong> registerSong(Registerable<JukeboxSong> registry, String id, RegistryEntry<SoundEvent> soundEvent, int length, int comparatorOutput) {
        Identifier i = Identifier.of(MOD_ID, id);
        return registry.register(RegistryKey.of(RegistryKeys.JUKEBOX_SONG, i), new JukeboxSong(soundEvent, Text.translatable(Util.createTranslationKey("jukebox_song", i)), (float) length, comparatorOutput));
    }

    */

}
