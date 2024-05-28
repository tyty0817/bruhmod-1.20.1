package com.github.donotdoughnut.bruhmod.items;

import com.github.donotdoughnut.bruhmod.Mod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item MYTHRIL = registerItem("mythril", new Item(new FabricItemSettings()));

    public static final Item MYTHRIL_HELMET = registerItem("mythril_helmet",
            new ArmorItem(ModArmorMaterials.MYTHRIL, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item MYTHRIL_CHESTPLATE = registerItem("mythril_chestplate",
            new ArmorItem(ModArmorMaterials.MYTHRIL, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item MYTHRIL_LEGGINGS = registerItem("mythril_leggings",
            new ArmorItem(ModArmorMaterials.MYTHRIL, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item MYTHRIL_BOOTS = registerItem("mythril_boots",
            new ArmorItem(ModArmorMaterials.MYTHRIL, ArmorItem.Type.BOOTS, new FabricItemSettings()));



    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries){
        entries.add(MYTHRIL);
    }

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Mod.MOD_ID, name), item);
    }

    public static void registerModItems(){
        Mod.LOGGER.info("Registering Mod Items for " + Mod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
