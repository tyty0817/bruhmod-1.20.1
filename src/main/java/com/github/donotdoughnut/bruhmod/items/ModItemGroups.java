package com.github.donotdoughnut.bruhmod.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static com.github.donotdoughnut.bruhmod.Mod.LOGGER;
import static com.github.donotdoughnut.bruhmod.Mod.MOD_ID;

public class ModItemGroups {

    public static void register() {

        ArrayList<ItemStack> items = new ArrayList<>(), eggs = new ArrayList<>();

        for (Field f : ModItems.class.getDeclaredFields()) {
            try {
                if (Item.class.isAssignableFrom(f.getType())) {
                    items.add(((Item) f.get(ModItems.class)).getDefaultStack());
                    if (f.getType().equals(SpawnEggItem.class)) {
                        eggs.add(((SpawnEggItem) f.get(ModItems.class)).getDefaultStack());
                    }
                } else if (f.getType().equals(ModItems.ArmorSet.class)) {
                    ModItems.ArmorSet armor = (ModItems.ArmorSet) f.get(ModItems.class);
                    armor.apply(i -> items.add(i.getDefaultStack()));
                }
            } catch (IllegalAccessException e) {
                LOGGER.error("Could not add item {} with error: {}", f.getName(), e);
            }
        }

        Registry.register(Registries.ITEM_GROUP,
                new Identifier(MOD_ID, MOD_ID),
                FabricItemGroup.builder()
                        .displayName(Text.translatable("itemgroup.bruhmod.group"))
                        .icon(ModItems.MYTHRIL::getDefaultStack)
                        .entries((displayContext, entries) -> entries.addAll(items))
                        .build());

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> entries.addAll(eggs));

    }

}
