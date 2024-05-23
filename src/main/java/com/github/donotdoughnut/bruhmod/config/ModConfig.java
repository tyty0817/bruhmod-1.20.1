package com.github.donotdoughnut.bruhmod.config;

import com.github.donotdoughnut.bruhmod.Mod;

//@Config(name = Mod.MOD_ID)
public class ModConfig /* implements ConfigData */ {

//	@Excluded
//	public static ModConfig config;
//
	public static void register() {
		Mod.LOGGER.info("Registering config...");
//		AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
//		config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
	}
}
