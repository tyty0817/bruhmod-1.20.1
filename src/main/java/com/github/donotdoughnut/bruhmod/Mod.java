package com.github.donotdoughnut.bruhmod;

import com.github.donotdoughnut.bruhmod.config.ModConfig;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.donotdoughnut.bruhmod.blocks.BlockList;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class Mod implements ModInitializer {

	public static final String MOD_ID = "bruhmod", NAME = "Bruh Mod";

	public static final Logger LOGGER = LogManager.getLogger(NAME);

	@Override
	public void onInitialize() {

		ModConfig.register();

		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "test_item"),
				new Item(new Item.Settings()));
		
		BlockList.register();
	}

}
