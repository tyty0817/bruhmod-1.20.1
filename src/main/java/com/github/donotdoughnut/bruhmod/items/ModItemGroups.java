package com.github.donotdoughnut.bruhmod.items;

import com.github.donotdoughnut.bruhmod.Mod;
import com.github.donotdoughnut.bruhmod.blocks.MythrilOre;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup BRUHMOD_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Mod.MOD_ID, "bruhmod"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.bruhmod"))
                    .icon(() -> new ItemStack(ModItems.MYTHRIL)).entries((displayContext, entries) -> {
                        entries.add(ModItems.MYTHRIL);
                        entries.add(MythrilOre.ORE_MYTHRIL_STONE_ITEM);
                        entries.add(MythrilOre.ORE_MYTHRIL_DEEPSLATE_ITEM);

                        entries.add(ModItems.MYTHRIL_HELMET);
                        entries.add(ModItems.MYTHRIL_CHESTPLATE);
                        entries.add(ModItems.MYTHRIL_LEGGINGS);
                        entries.add(ModItems.MYTHRIL_BOOTS);



                    }).build());



    public static void registerItemGroups(){
        Mod.LOGGER.info("Registering Item Groups for " + Mod.MOD_ID);
    }
}
