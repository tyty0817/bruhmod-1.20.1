package com.github.donotdoughnut.bruhmod.blocks;

import com.github.donotdoughnut.bruhmod.Mod;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
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

			MYTHRIL_ORE = registerBlock("mythril_ore",
					new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(4.0f, 4.0f).luminance((hi) -> 2).requiresTool(), UniformIntProvider.create(5, 10))),
			DEEPSLATE_MYTHRIL_ORE = registerBlock("deepslate_mythril_ore",
					new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE).strength(4.0f, 4.0f).luminance((hi) -> 2).requiresTool(), UniformIntProvider.create(5, 10))),
			MYTHRIL_BLOCK = registerBlock("mythril_block",
					new Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK).strength(4.0f, 4.0f).luminance((hi) -> 2).requiresTool()));

	/*
	public static final Block MYTHRIL_BLOCK = registerBlock("mythril_block",
			new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)));
	*/

	private static final RegistryKey<PlacedFeature> ORE_MYTHRIL_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(MOD_ID,"ore_mythril"));

	public static void register() {
		
		LOGGER.info("Registering blocks...");

		BiomeModifications.addFeature(BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, new Identifier("minecraft", "is_mountain"))), GenerationStep.Feature.UNDERGROUND_ORES, ORE_MYTHRIL_PLACED_KEY);

	}

	private static Block registerBlock(String name, ExperienceDroppingBlock block) {
		return Registry.register(Registries.BLOCK, new Identifier(MOD_ID, name), block);
	}

	private static Block registerBlock(String name, Block block) {
		return Registry.register(Registries.BLOCK, new Identifier(Mod.MOD_ID, name), block);
	}

}
