package name.bruhmod.item;

import name.bruhmod.LeMod;
import name.bruhmod.blocks.ModBlocks;
import name.bruhmod.entities.ModEntities;
import name.bruhmod.item.staff.*;
import name.bruhmod.sound.ModSounds;
import name.bruhmod.util.RegistryHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;

import java.util.EnumMap;

public class ModItems {

    public static final RegistryHelper<Item> REGISTERER = new RegistryHelper<>(BuiltInRegistries.ITEM);

    /*
     * Items
     */
    public static final Item

            ESSENCE_COLLECTOR = registerItem("essence_collector_test", new EssenceCollector()),

            MYTHRIL = registerItem("mythril", new Item(new Item.Properties().rarity(Rarity.UNCOMMON))),
            MYTHRIL_DUST = registerItem("mythril_dust"),
            MYTHRIL_FRAGMENT = registerItem("mythril_fragment"),
            MYTHRIL_UPGRADE = registerItem("mythril_upgrade_smithing_template"),
            EMPTY_GEM = registerItem("empty_gem", new Item(new Item.Properties().stacksTo(16))),
            EXPLOSIVE_JELLY = registerItem("explosive_jelly"),

            CORRUPTED_SLAG = registerItem("corrupted_slag", new Item(new Item.Properties().rarity(Rarity.UNCOMMON))),
            MYSTERIOUS_CLUB = registerItem("mysterious_club", new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1))),
            CORRUPTED_CROWN = registerItem("corrupted_crown", new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE))),
            JEWEL_OF_CORRUPTION = registerItem("jewel_of_corruption", new ShinyItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE))),
            BRANCH_OF_CORRUPTION = registerItem("branch_of_corruption", new BoltCaster(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));

    public static final Item LIGHTNING_IN_A_BOTTLE = registerItem("lightning_in_a_bottle", new LightningBottleItem(new Item.Properties().fireResistant().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final Item VOLATILE_CLAW = registerItem("volatile_claw", new Item(new Item.Properties().stacksTo(1)));
    public static final Item CHARGED_CLAW = registerItem("charged_claw", new Item(new Item.Properties().stacksTo(1)));
    public static final Item VOLATILE_PILLAR = registerItem("volatile_pillar", new Item(new Item.Properties().stacksTo(1)));
    public static final Item MAELSTROM = registerItem("maelstrom", new Maelstrom());

    public static final Item UNSTABLE_GEM = registerItem("unstable_gem", new ShinyItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final Item DYING_LIGHT = registerItem("dying_light", new DyingLight());

    public static final Item CLOUD_IN_A_BOTTLE = registerItem("cloud_in_a_bottle", new Item(new Item.Properties().stacksTo(1)));
    public static final Item SPIRITED_BLUDGEON = registerItem("spirited_bludgeon", new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final Item PRONGED_CROWN = registerItem("pronged_crown", new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final Item WIND_GEM = registerItem("wind_gem", new ShinyItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).fireResistant()));
    public static final Item MONKS_CUDGEL = registerItem("monks_cudgel", new MonksCudgel());
    public static final Item WHIRLWIND_SASH = registerItem("whirlwind_sash", new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final Item SHILLELAGH = registerItem("shillelagh", new Shillelagh());


    public static final Item MESS_HALL_MAP = registerItem("mess_hall_map", new ModMapItem("mess_hall", MapDecorationTypes.BROWN_BANNER));
    public static final Item PORTAL_TOWER_MAP = registerItem("portal_tower_map", new ModMapItem("portal_tower", MapDecorationTypes.PURPLE_BANNER));
    public static final Item WIZARD_TOWER_MAP = registerItem("wizard_tower_map", new ModMapItem("wizard_tower", MapDecorationTypes.LIGHT_GRAY_BANNER));
    public static final Item WITCH_HUT_MAP = registerItem("witch_hut_map", new ModMapItem("witch_hut", MapDecorationTypes.SWAMP_HUT));
    public static final Item CARPENTER_MAP = registerItem("carpenter_map", new ModMapItem("carpenter", MapDecorationTypes.BLUE_MARKER));
    public static final Item BARRACKS_MAP = registerItem("barracks_map", new ModMapItem("barracks", MapDecorationTypes.BLACK_BANNER));
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
            MYTHRIL_STUDDED_ARMOR = registerArmor(ModArmorMaterials.MYTHRIL_DIAMOND, new Item.Properties()),
            MYTHRIL_INFUSED_ARMOR = registerArmor(ModArmorMaterials.MYTHRIL_NETHERITE, new Item.Properties());


    public static final Item MUSIC_DISC_FALLOUT = registerMusicDisc(ModSounds.FALLOUT);
    public static final Item MUSIC_DISC_BLACK_MOON = registerMusicDisc(ModSounds.BLACK_MOON);
    public static final Item MUSIC_DISC_DARK_WOODS = registerMusicDisc(ModSounds.DARK_WOODS);
    public static final Item MUSIC_DISC_NIGHT_OWL = registerMusicDisc(ModSounds.NIGHT_OWL);
    public static final Item MUSIC_DISC_OLD_KING = registerMusicDisc(ModSounds.OLD_KING);
    public static final Item MUSIC_DISC_THE_RANGER = registerMusicDisc(ModSounds.THE_RANGER);


    public static final Item BRITISH_MAN_SPAWN_EGG = registerItem("british_man_spawn_egg", new SpawnEggItem(ModEntities.BOSS, 0xd59890, 0xd7b4ae, new Item.Properties()));


    private static <I extends Item> I registerItem(ResourceLocation id, I item) {
        return REGISTERER.add(id, item);
    }

    /**
     * Register an item with default parameters
     *
     * @param name The ID of the item.
     * @return The item, registered
     */
    private static Item registerItem(String name) {
        return registerItem(name, new Item(new Item.Properties()));
    }

    /**
     * Register an item with custom parameters, for example if you were making armor or special items
     *
     * @param name The ID of the item.
     * @return The item, registered
     */
    private static Item registerItem(String name, Item item) {
        return registerItem(LeMod.idOf(name), item);
    }

    /**
     * Register a block as an item.
     *
     * @param block The block to be registered
     * @return The Block item, registered
     */
    private static BlockItem registerBlock(Block block) {
        return registerItem(ModBlocks.REGISTERER.getKey(block), new BlockItem(block, new Item.Properties()));
    }

    private static EnumMap<ArmorItem.Type, ArmorItem> registerArmor(ArmorMaterial material, Item.Properties settings) {
        EnumMap<ArmorItem.Type, ArmorItem> items = new EnumMap<>(ArmorItem.Type.class);
        ResourceLocation id = ModArmorMaterials.REGISTERER.getKey(material);
        ArmorItem.Type[] types = ArmorItem.Type.values();
        for (int i = 0; i < 4; i++) {
            ArmorItem.Type type = types[i];
            items.put(type, (ArmorItem) registerItem(id.getPath() + "_" + type.getName(), new ArmorItem(Holder.direct(material), type, settings)));
        }
        return items;
    }

    private static Item registerMusicDisc(Holder<SoundEvent> song) {
        ResourceLocation id = ModSounds.REGISTERER.getKey(song.value());
        ResourceLocation itemId = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "music_disc_" + id.getPath());
        return registerItem(itemId, new Item((new Item.Properties()).stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, id))));
    }

    public static class ShinyItem extends Item {

        public ShinyItem(Item.Properties settings) {
            super(settings);
        }

        public boolean isFoil(ItemStack stack) {
            return true;
        }
    }

}
