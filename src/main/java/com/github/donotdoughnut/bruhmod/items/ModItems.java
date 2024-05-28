package com.github.donotdoughnut.bruhmod.items;

import com.github.donotdoughnut.bruhmod.Mod;
import com.github.donotdoughnut.bruhmod.blocks.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.github.donotdoughnut.bruhmod.Mod.MOD_ID;

public class ModItems {

    public static final Item MYTHRIL = register("mythril");

    public static final BlockItem
            MYTHRIL_ORE = registerBlock(ModBlocks.MYTHRIL_ORE),
            DEEPSLATE_MYTHRIL_ORE = registerBlock(ModBlocks.DEEPSLATE_MYTHRIL_ORE);

    public static void register() {
        Mod.LOGGER.info("Registering Mod Items for " + MOD_ID);

//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
//            entries.addAfter(Items.NETHERITE_INGOT, MYTHRIL);
//        });
    }

    private static Item register(String name) {
        return register(name, new Item.Settings());
    }

    private static Item register(String name, Item.Settings item) {
        return Registry.register(Registries.ITEM, new Identifier(MOD_ID, name), new Item(item));
    }

    private static BlockItem registerBlock(Block block) {
        return Registry.register(Registries.ITEM, Registries.BLOCK.getId(block), new BlockItem(block, new Item.Settings()));
    }
}
