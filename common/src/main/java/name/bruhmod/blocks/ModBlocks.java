package name.bruhmod.blocks;

import name.bruhmod.LeMod;
import name.bruhmod.util.RegistryHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {

	public static final RegistryHelper<Block> REGISTERER = new RegistryHelper<>(BuiltInRegistries.BLOCK);

	public static final Block
			MYTHRIL_ORE = registerBlock("mythril_ore",
					new DropExperienceBlock(UniformInt.of(5, 10), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(4.0f, 4.0f).lightLevel((hi) -> 2).requiresCorrectToolForDrops())),
			DEEPSLATE_MYTHRIL_ORE = registerBlock("deepslate_mythril_ore",
					new DropExperienceBlock(UniformInt.of(5, 10), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(4.0f, 4.0f).lightLevel((hi) -> 2).requiresCorrectToolForDrops())),
			MYTHRIL_BLOCK = registerBlock("mythril_block",
					new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK).strength(4.0f, 4.0f).lightLevel((hi) -> 2).requiresCorrectToolForDrops()));

	private static Block registerBlock(String name, Block block) {
		return REGISTERER.add(LeMod.idOf(name), block);
	}

}
