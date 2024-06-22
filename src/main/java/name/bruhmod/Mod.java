package name.bruhmod;

import name.bruhmod.items.ModArmorMaterials;
import name.bruhmod.items.ModItemGroups;
import name.bruhmod.items.ModItems;
import name.bruhmod.recipe.natural.NaturalRecipe;
import name.bruhmod.potion.ModPotions;
import name.bruhmod.sound.ModSounds;
import name.bruhmod.blocks.ModBlocks;
import name.bruhmod.effect.ModEffects;
import name.bruhmod.entities.*;
import name.bruhmod.world.ModCustomTrades;
import name.bruhmod.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mod implements ModInitializer {
	public static final String MOD_ID = "bruhmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static ResourceLocation idOf(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		NaturalRecipe.register();
		ModBlocks.register();
		ModItems.register();
		ModArmorMaterials.register();
		ModEntities.register();
		ModEffects.registerEffects();
		ModSounds.registerSounds();
		ModCustomTrades.registerCustomTrades();

		ModPotions.registerPotions();
		ModPotions.registerPotionsRecipes();

		ModWorldGeneration.generateModWorldGen();
	}
}