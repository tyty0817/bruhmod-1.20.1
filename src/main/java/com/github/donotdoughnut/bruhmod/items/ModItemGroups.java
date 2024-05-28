package com.github.donotdoughnut.bruhmod.items;

import com.github.donotdoughnut.bruhmod.Mod;
import com.github.donotdoughnut.bruhmod.blocks.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.github.donotdoughnut.bruhmod.items.ModItems.*;

public class ModItemGroups {
    public static final ItemGroup BRUHMOD_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Mod.MOD_ID, "bruhmod"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.bruhmod.group"))
                    .icon(() -> new ItemStack(MYTHRIL)).entries((displayContext, entries) -> {
                        entries.add(MYTHRIL);
                        entries.add(MYTHRIL_ORE);
                        entries.add(DEEPSLATE_MYTHRIL_ORE);
                    }).build());
}
