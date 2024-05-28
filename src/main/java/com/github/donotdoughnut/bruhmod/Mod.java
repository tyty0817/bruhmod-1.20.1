package com.github.donotdoughnut.bruhmod;

import com.github.donotdoughnut.bruhmod.entities.TestEntity;
import com.github.donotdoughnut.bruhmod.items.ModItemGroups;
import com.github.donotdoughnut.bruhmod.items.ModItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.donotdoughnut.bruhmod.blocks.ModBlocks;

import net.fabricmc.api.ModInitializer;

public class Mod implements ModInitializer {

	public static final String MOD_ID = "bruhmod", NAME = "Le Mod";

	public static final Logger LOGGER = LogManager.getLogger(NAME);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModBlocks.register();
		ModItems.register();
		TestEntity.register();
	}

}
