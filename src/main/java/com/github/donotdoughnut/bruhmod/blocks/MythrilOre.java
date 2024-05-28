package com.github.donotdoughnut.bruhmod.blocks;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

import static com.github.donotdoughnut.bruhmod.Mod.MOD_ID;

public class MythrilOre {

    public static final Block
            ORE_MYTHRIL_STONE = Registry.register(Registries.BLOCK,
                    new Identifier(MOD_ID, "mythril_ore"),
                    new Block(AbstractBlock.Settings.create().strength(4.0f, 4.0f).luminance((hi) -> 2).requiresTool())
            ),
            ORE_MYTHRIL_DEEPSLATE = Registry.register(Registries.BLOCK,
                    new Identifier(MOD_ID, "deepslate_mythril_ore"),
                    new Block(AbstractBlock.Settings.create().strength(5.5f, 4.0f).luminance((hi) -> 2).requiresTool())
            );

    public static final BlockItem
            ORE_MYTHRIL_STONE_ITEM = Registry.register(Registries.ITEM,
                new Identifier(MOD_ID, "mythril_ore"),
                new BlockItem(ORE_MYTHRIL_STONE, new BlockItem.Settings())
            ),
            ORE_MYTHRIL_DEEPSLATE_ITEM = Registry.register(Registries.ITEM,
                    new Identifier(MOD_ID, "deepslate_mythril_ore"),
                    new BlockItem(ORE_MYTHRIL_DEEPSLATE, new BlockItem.Settings())
            );

    private static final RegistryKey<PlacedFeature> ORE_MYTHRIL_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(MOD_ID,"ore_mythril"));

    public static void register() {
        BiomeModifications.addFeature(BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, new Identifier("minecraft", "is_mountain"))), GenerationStep.Feature.UNDERGROUND_ORES, ORE_MYTHRIL_PLACED_KEY);
    }

}
