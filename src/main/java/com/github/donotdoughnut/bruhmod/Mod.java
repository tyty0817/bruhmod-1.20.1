package com.github.donotdoughnut.bruhmod;

import com.github.donotdoughnut.bruhmod.entities.BossEntity;
import com.github.donotdoughnut.bruhmod.items.ModItemGroups;
import com.github.donotdoughnut.bruhmod.items.ModItems;
import com.github.donotdoughnut.bruhmod.sounds.ModSounds;
import com.github.donotdoughnut.bruhmod.util.ModCustomTrades;
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
		ModCustomTrades.registerCustomTrades();
		BossEntity.register();
		ModSounds.registerSounds();
	}

}
