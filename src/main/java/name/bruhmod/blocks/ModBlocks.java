package name.bruhmod.blocks;

import name.bruhmod.Mod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {

	public static final Block
			MYTHRIL_ORE = registerBlock("mythril_ore",
					new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(4.0f, 4.0f).luminance((hi) -> 2).requiresTool(), UniformIntProvider.create(5, 10))),
			DEEPSLATE_MYTHRIL_ORE = registerBlock("deepslate_mythril_ore",
					new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE).strength(4.0f, 4.0f).luminance((hi) -> 2).requiresTool(), UniformIntProvider.create(5, 10))),
			MYTHRIL_BLOCK = registerBlock("mythril_block",
					new Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK).strength(4.0f, 4.0f).luminance((hi) -> 2).requiresTool()));

	public static void register() {
		
		Mod.LOGGER.info("Registering blocks...");

	}

	private static Block registerBlock(String name, ExperienceDroppingBlock block) {
		return Registry.register(Registries.BLOCK, new Identifier(Mod.MOD_ID, name), block);
	}

	private static Block registerBlock(String name, Block block) {
		return Registry.register(Registries.BLOCK, new Identifier(Mod.MOD_ID, name), block);
	}

}
