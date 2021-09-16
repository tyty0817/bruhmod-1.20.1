package com.github.donotdoughnut.bruhmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.donotdoughnut.bruhmod.blocks.Sconce;
import com.github.donotdoughnut.bruhmod.config.ModConfig;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BruhMod implements ModInitializer {

	public static final String MOD_ID = "bruhmod", FANCY_ID = "Bruh Mod";

	public static final Logger LOGGER = LogManager.getLogger(FANCY_ID);

	@Override
	public void onInitialize() {

		ModConfig.register();

		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "test_item"), new Item(new FabricItemSettings().group(ItemGroup.MISC)));
		Sconce sconce=new Sconce(FabricBlockSettings.of(Material.WOOD));
Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "sconce"), sconce);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "sconce"), new BlockItem(sconce, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello World!");
	}

}
