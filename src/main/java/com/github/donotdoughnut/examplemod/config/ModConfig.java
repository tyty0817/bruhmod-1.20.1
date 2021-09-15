package com.github.donotdoughnut.examplemod.config;

import com.github.donotdoughnut.examplemod.ExampleMod;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.Excluded;

@Config(name = ExampleMod.MOD_ID)
public class ModConfig implements ConfigData {

    @Excluded
	public static ModConfig config;
	
	public static void register() {
		ExampleMod.LOGGER.info("Registering config...");
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();		
	}
    
}
