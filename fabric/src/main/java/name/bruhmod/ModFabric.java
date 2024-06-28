package name.bruhmod;

import name.bruhmod.blocks.ModBlocks;
import name.bruhmod.potion.ModPotions;
import name.bruhmod.entities.*;
import name.bruhmod.util.RegistryHelper;
import name.bruhmod.world.ModCustomTrades;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_ORES;

public class ModFabric implements ModInitializer {

	@Override
	public void onInitialize() {

		// initialize shared stuff
		LeMod.initializeRegistries(new RegistryHelper.RegistryConsumer() {
			@Override
			public <T> void register(Registry<T> registry, Consumer<BiConsumer<ResourceLocation, T>> consumer) {
				consumer.accept((id, item) -> Registry.register(registry, id, item));
			}
		});

		// use provided builders
		ModEntities.registerAttributes(FabricDefaultAttributeRegistry::register);
		ModCustomTrades.registerCustomTrades(TradeOfferHelper::registerVillagerOffers);
		FabricBrewingRecipeRegistryBuilder.BUILD.register(ModPotions::registerRecipes);

		// no cross compatibility for this, its fucked
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
				UNDERGROUND_ORES, ResourceKey.create(Registries.PLACED_FEATURE, ModBlocks.REGISTERER.getKey(ModBlocks.MYTHRIL_ORE)));

	}
}