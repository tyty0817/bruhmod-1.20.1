package com.github.donotdoughnut.examplemod;

import com.github.donotdoughnut.examplemod.config.ModConfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExampleMod implements ModInitializer {

	public static final String MOD_ID = "examplemod", FANCY_ID = "Example Mod";

	public static final Logger LOGGER = LogManager.getLogger(FANCY_ID);

	@Override
	public void onInitialize() {

		ModConfig.register();

		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "test_item"), new Item(new FabricItemSettings().group(ItemGroup.MISC)));

		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello World!");
	}

}
