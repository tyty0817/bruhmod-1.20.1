package com.github.donotdoughnut.bruhmod.datagen;

import com.github.donotdoughnut.bruhmod.items.ModItems;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.item.ArmorItem;

public class ModModelProvider {
    public void generateItemModels(ItemModelGenerator itemModelGenerator){
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MYTHRIL_STUDDED_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MYTHRIL_STUDDED_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MYTHRIL_STUDDED_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MYTHRIL_STUDDED_BOOTS));

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MYTHRIL_INFUSED_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MYTHRIL_INFUSED_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MYTHRIL_INFUSED_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MYTHRIL_INFUSED_BOOTS));

    }
}
