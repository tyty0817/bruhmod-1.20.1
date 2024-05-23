package com.github.donotdoughnut.bruhmod.blocks;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.PlacedFeature;

import static com.github.donotdoughnut.bruhmod.Mod.LOGGER;
import static com.github.donotdoughnut.bruhmod.Mod.MOD_ID;

public class BlockList {
	
	public static final SconceBlock SCONCE = new SconceBlock();
	public static final WallSconceBlock WALL_SCONCE = new WallSconceBlock();

	public static void register() {
		
		LOGGER.info("Registering blocks...");

		MythrilOre.register();

//		Identifier sconce = new Identifier(MOD_ID, "sconce");
		
//		Registry.register(Registries.BLOCK, sconce, SCONCE);
//		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "wall_sconce"), WALL_SCONCE);
//
//		Registry.register(Registries.ITEM, sconce, new VerticallyAttachableBlockItem(SCONCE, WALL_SCONCE, new Item.Settings(), Direction.DOWN));

	}

}
