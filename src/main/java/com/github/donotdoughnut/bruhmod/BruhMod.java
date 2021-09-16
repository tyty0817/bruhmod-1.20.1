package com.github.donotdoughnut.bruhmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.donotdoughnut.bruhmod.blocks.BlockList;
import com.github.donotdoughnut.bruhmod.config.ModConfig;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
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

		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "test_item"),
				new Item(new FabricItemSettings().group(ItemGroup.MISC)));
		
		BlockList.register();
		
	
	}

}
