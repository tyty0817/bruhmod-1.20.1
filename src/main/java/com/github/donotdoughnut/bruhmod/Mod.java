package com.github.donotdoughnut.bruhmod;

import com.github.donotdoughnut.bruhmod.config.ModConfig;
import com.github.donotdoughnut.bruhmod.entities.TestEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.donotdoughnut.bruhmod.blocks.BlockList;

import net.fabricmc.api.ModInitializer;

public class Mod implements ModInitializer {

	public static final String MOD_ID = "bruhmod", NAME = "Bruh Mod";

	public static final Logger LOGGER = LogManager.getLogger(NAME);

	@Override
	public void onInitialize() {

		ModConfig.register();
		
		BlockList.register();

		TestEntity.register();
	}

}
