package name.bruhmod;

import name.bruhmod.blocks.ModBlocks;
import name.bruhmod.effect.ModEffects;
import name.bruhmod.item.ModArmorMaterials;
import name.bruhmod.item.ModDataComponents;
import name.bruhmod.item.ModItemGroups;
import name.bruhmod.item.ModItems;
import name.bruhmod.potion.ModPotions;
import name.bruhmod.entity.*;
import name.bruhmod.recipe.ModRecipes;
import name.bruhmod.sound.ModSounds;
import name.bruhmod.villager.ModCustomTrades;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.EnchantmentEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.function.BiConsumer;

import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_ORES;

public class ModFabric implements ModInitializer {

	@Override
	public void onInitialize() {

		ModRecipes.register((id, pair) -> {
			Registry.register(BuiltInRegistries.RECIPE_TYPE, id, pair.getFirst());
			Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, id, pair.getSecond());
		});
		ModBlocks.BLOCKS.registerAll(registry(BuiltInRegistries.BLOCK));
		ModItems.ITEMS.registerAll(registry(BuiltInRegistries.ITEM));
		ModDataComponents.register(registry(BuiltInRegistries.DATA_COMPONENT_TYPE));
		ModEntities.ENTITY_TYPES.registerAll(registry(BuiltInRegistries.ENTITY_TYPE));
		ModArmorMaterials.REGISTERER.registerAll(registry(BuiltInRegistries.ARMOR_MATERIAL));
		ModPotions.register(registry(BuiltInRegistries.POTION));
		ModEffects.registerEffects(registry(BuiltInRegistries.MOB_EFFECT));
		ModSounds.SOUNDS.registerAll(registry(BuiltInRegistries.SOUND_EVENT));
		ModItemGroups.register(registry(BuiltInRegistries.CREATIVE_MODE_TAB));


		// use provided builders
		ModAttributes.registerAttributes(FabricDefaultAttributeRegistry::register);
		ModCustomTrades.registerCustomTrades(TradeOfferHelper::registerVillagerOffers);
		FabricBrewingRecipeRegistryBuilder.BUILD.register(ModPotions::registerRecipes);

		// no cross compatibility for this, its fucked
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
				UNDERGROUND_ORES, ResourceKey.create(Registries.PLACED_FEATURE, ModBlocks.BLOCKS.getKey(ModBlocks.MYTHRIL_ORE)));

		EnchantmentEvents.MODIFY.register((key, b, c) -> {
			if (Enchantments.EFFICIENCY.equals(key)) {
				
			}
		});

	}

	private static <T> BiConsumer<ResourceLocation, T> registry(Registry<? super T> registry) {
		return (id, t) -> Registry.register(registry, id, t);
	}
}