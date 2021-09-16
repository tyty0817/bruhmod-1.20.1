package com.github.donotdoughnut.bruhmod.blocks;

import static com.github.donotdoughnut.bruhmod.BruhMod.LOGGER;
import static com.github.donotdoughnut.bruhmod.BruhMod.MOD_ID;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.WallStandingBlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockList {
	
	public static final SconceBlock SCONCE = new SconceBlock();
	public static final WallSconceBlock WALL_SCONCE = new WallSconceBlock();
	
	public static void register() {
		
		LOGGER.info("Registering blocks...");
		
		Identifier sconce = new Identifier(MOD_ID, "sconce");
		
		Registry.register(Registry.BLOCK, sconce, SCONCE);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "wall_sconce"), WALL_SCONCE);
		
		Registry.register(Registry.ITEM, sconce, new WallStandingBlockItem(SCONCE, WALL_SCONCE, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
		
	}

}
