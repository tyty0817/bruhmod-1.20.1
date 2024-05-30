package com.github.donotdoughnut.bruhmod.blocks;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

import static com.github.donotdoughnut.bruhmod.Mod.LOGGER;
import static com.github.donotdoughnut.bruhmod.Mod.MOD_ID;

public class ModBlocks {


	public static final Block
			MYTHRIL_ORE = registerBlock("mythril_ore", ExperienceDroppingBlock.Settings.create().strength(4.0f, 4.0f).luminance((hi) -> 2).requiresTool()),
			DEEPSLATE_MYTHRIL_ORE = registerBlock("deepslate_mythril_ore", ExperienceDroppingBlock.Settings.create().strength(5.5f, 4.0f).luminance((hi) -> 2).requiresTool());

	private static final RegistryKey<PlacedFeature> ORE_MYTHRIL_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(MOD_ID,"ore_mythril"));

	public static void register() {
		
		LOGGER.info("Registering blocks...");

		BiomeModifications.addFeature(BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, new Identifier("minecraft", "is_mountain"))), GenerationStep.Feature.UNDERGROUND_ORES, ORE_MYTHRIL_PLACED_KEY);

	}

	/**
	 * Register a block
	 * @param name The id of the block
	 * @param settings The block's settings
	 * @return The block, registered
	 */
	private static Block registerBlock(String name, AbstractBlock.Settings settings) {
		return Registry.register(Registries.BLOCK, new Identifier(MOD_ID, name), new Block(settings));
	}

}
