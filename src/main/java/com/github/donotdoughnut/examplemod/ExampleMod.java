package com.github.donotdoughnut.examplemod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExampleMod implements ModInitializer {

	public static final String MOD_ID = "examplemod";

	@Override
	public void onInitialize() {

		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "test_item"), new Item(new FabricItemSettings().group(ItemGroup.MISC)));

		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world!");
	}

}
