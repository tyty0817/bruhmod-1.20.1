package com.github.donotdoughnut.bruhmod.datagen;

import com.github.donotdoughnut.bruhmod.items.ModItems;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

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


        /*
        ARMOR
         */
        ModItems.MYTHRIL_STUDDED_ARMOR.apply(itemModelGenerator::registerArmor);
        ModItems.MYTHRIL_INFUSED_ARMOR.apply(itemModelGenerator::registerArmor);


    }
}
