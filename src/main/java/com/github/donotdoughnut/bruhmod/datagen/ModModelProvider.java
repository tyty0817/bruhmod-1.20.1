package com.github.donotdoughnut.bruhmod.datagen;

import com.github.donotdoughnut.bruhmod.items.ModItems;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.item.ArmorItem;

public class ModModelProvider {
    public void generateItemModels(ItemModelGenerator itemModelGenerator){
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MYTHRIL_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MYTHRIL_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MYTHRIL_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MYTHRIL_BOOTS));

    }
}
