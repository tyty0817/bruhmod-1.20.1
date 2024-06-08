package name.modid;

import name.modid.blocks.ModBlocks;
import name.modid.effect.ModEffects;
import name.modid.events.LightningFinder;
import name.modid.events.LightningTracker;
import name.modid.items.ModItemGroups;
import name.modid.items.ModItems;
import name.modid.entities.*;
import name.modid.sound.ModSounds;
import name.modid.util.ModCustomTrades;
import name.modid.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mod implements ModInitializer {
	public static final String MOD_ID = "bruhmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModBlocks.register();
		ModItems.register();
		ModCustomTrades.registerCustomTrades();
		ModEntities.registerModEntities();
		ModEffects.registerEffects();
		ModSounds.registerSounds();

		ServerEntityEvents.ENTITY_LOAD.register(new LightningFinder());
		ServerEntityEvents.ENTITY_UNLOAD.register(new LightningTracker());

		ModWorldGeneration.generateModWorldGen();
	}
}