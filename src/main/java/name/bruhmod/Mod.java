package name.bruhmod;

import name.bruhmod.items.ModItemGroups;
import name.bruhmod.items.ModItems;
import name.bruhmod.sound.ModSounds;
import name.bruhmod.blocks.ModBlocks;
import name.bruhmod.effect.ModEffects;
import name.bruhmod.events.EntityLoads;
import name.bruhmod.events.EntityUnloads;
import name.bruhmod.events.ExplosionCrafter;
import name.bruhmod.events.ExplosionDetector;
import name.bruhmod.entities.*;
import name.bruhmod.world.ModCustomTrades;
import name.bruhmod.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;
import dev.architectury.event.events.common.ExplosionEvent;

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
		ModEntities.register();
		ModEffects.registerEffects();
		ModSounds.registerSounds();
		ModCustomTrades.registerCustomTrades();

		ServerEntityEvents.ENTITY_LOAD.register(new EntityLoads());
		ServerEntityEvents.ENTITY_UNLOAD.register(new EntityUnloads());
		ExplosionEvent.PRE.register(new ExplosionDetector());
		ExplosionEvent.DETONATE.register(new ExplosionCrafter());

		ModWorldGeneration.generateModWorldGen();
	}

}