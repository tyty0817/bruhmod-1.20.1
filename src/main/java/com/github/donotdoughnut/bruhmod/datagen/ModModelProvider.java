package com.github.donotdoughnut.bruhmod.datagen;

import com.github.donotdoughnut.bruhmod.Mod;
import com.github.donotdoughnut.bruhmod.items.ModItems;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;

public class ModModelProvider {
    public void generateItemModels(ItemModelGenerator itemModelGenerator){

        /*
        ITEMS
         */
        itemModelGenerator.register(ModItems.DEEPSLATE_MYTHRIL_ORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MYTHRIL_ORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MYTHRIL, Models.GENERATED);
        itemModelGenerator.register(ModItems.MYTHRIL_DUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.MYTHRIL_FRAGMENT, Models.GENERATED);

        itemModelGenerator.register(ModItems.OLD_KING_TAVERN_MUSIC_DISC, Models.GENERATED);

        /*
        ARMOR
         */
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
