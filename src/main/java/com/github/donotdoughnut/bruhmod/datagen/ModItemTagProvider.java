package com.github.donotdoughnut.bruhmod.datagen;

import com.github.donotdoughnut.bruhmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture){
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg){
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.MYTHRIL_STUDDED_HELMET, ModItems.MYTHRIL_STUDDED_CHESTPLATE, ModItems.MYTHRIL_STUDDED_LEGGINGS, ModItems.MYTHRIL_STUDDED_BOOTS,
                        ModItems.MYTHRIL_INFUSED_HELMET, ModItems.MYTHRIL_INFUSED_CHESTPLATE, ModItems.MYTHRIL_INFUSED_LEGGINGS, ModItems.MYTHRIL_INFUSED_BOOTS);

    }
}
