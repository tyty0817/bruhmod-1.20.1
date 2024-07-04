package name.bruhmod;

import name.bruhmod.blocks.ModBlocks;
import name.bruhmod.potion.ModPotions;
import name.bruhmod.entities.*;
import name.bruhmod.world.ModCustomTrades;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;

import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_ORES;

public class ModFabric implements ModInitializer {

	@Override
	public void onInitialize() {

		// initialize shared stuff
		LeMod.initializeRegistries(Registry::registerForHolder);

		// use provided builders
		ModAttributes.registerAttributes(FabricDefaultAttributeRegistry::register);
		ModCustomTrades.registerCustomTrades(TradeOfferHelper::registerVillagerOffers);
		FabricBrewingRecipeRegistryBuilder.BUILD.register(ModPotions::registerRecipes);

		// no cross compatibility for this, its fucked
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
				UNDERGROUND_ORES, ResourceKey.create(Registries.PLACED_FEATURE, ModBlocks.REGISTERER.getKey(ModBlocks.MYTHRIL_ORE)));

	}
}