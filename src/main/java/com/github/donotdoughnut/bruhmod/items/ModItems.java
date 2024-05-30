package com.github.donotdoughnut.bruhmod.items;

import com.github.donotdoughnut.bruhmod.Mod;
import com.github.donotdoughnut.bruhmod.blocks.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.github.donotdoughnut.bruhmod.Mod.MOD_ID;

public class ModItems {

    /*
     * Items
     */
    public static final Item MYTHRIL = registerItem("mythril");
    public static final Item MYTHRIL_DUST = registerItem("mythril_dust");
    public static final Item MYTHRIL_FRAGMENT = registerItem("mythril_fragment");
    public static final Item MYTHRIL_UPGRADE = registerItem("mythril_upgrade_smithing_template");




    public static final Item BARRACKS_MAP = registerItem("barracks_map", new BarracksMapItem());

    /*
     * Blocks
     */
    public static final BlockItem
            MYTHRIL_ORE = registerBlock(ModBlocks.MYTHRIL_ORE),
            DEEPSLATE_MYTHRIL_ORE = registerBlock(ModBlocks.DEEPSLATE_MYTHRIL_ORE);

    /*
     * Armor
     */
    public static final Item MYTHRIL_STUDDED_HELMET = registerItem("mythril_studded_helmet",
            new ArmorItem(ModArmorMaterials.MYTHRIL_DIAMOND, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item MYTHRIL_STUDDED_CHESTPLATE = registerItem("mythril_studded_chestplate",
            new ArmorItem(ModArmorMaterials.MYTHRIL_DIAMOND, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item MYTHRIL_STUDDED_LEGGINGS = registerItem("mythril_studded_leggings",
            new ArmorItem(ModArmorMaterials.MYTHRIL_DIAMOND, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item MYTHRIL_STUDDED_BOOTS = registerItem("mythril_studded_boots",
            new ArmorItem(ModArmorMaterials.MYTHRIL_DIAMOND, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item MYTHRIL_INFUSED_HELMET = registerItem("mythril_infused_helmet",
            new ArmorItem(ModArmorMaterials.MYTHRIL_NETHERITE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item MYTHRIL_INFUSED_CHESTPLATE = registerItem("mythril_infused_chestplate",
            new ArmorItem(ModArmorMaterials.MYTHRIL_NETHERITE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item MYTHRIL_INFUSED_LEGGINGS = registerItem("mythril_infused_leggings",
            new ArmorItem(ModArmorMaterials.MYTHRIL_NETHERITE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item MYTHRIL_INFUSED_BOOTS = registerItem("mythril_infused_boots",
            new ArmorItem(ModArmorMaterials.MYTHRIL_NETHERITE, ArmorItem.Type.BOOTS, new FabricItemSettings()));



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
