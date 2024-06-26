package name.bruhmod;

import name.bruhmod.item.ModItemGroups;
import name.bruhmod.potion.ModPotions;
import name.bruhmod.entities.*;
import name.bruhmod.world.ModCustomTrades;
import name.bruhmod.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;

public class ModFabric implements ModInitializer {

	@Override
	public void onInitialize() {
		Mod.initialize();

		ModEntities.register(FabricDefaultAttributeRegistry::register);
		ModCustomTrades.registerCustomTrades(TradeOfferHelper::registerVillagerOffers);

		ModPotions.register();

		ModWorldGeneration.generateModWorldGen();
	}
}