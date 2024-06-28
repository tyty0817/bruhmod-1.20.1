package name.bruhmod;

import name.bruhmod.blocks.ModBlocks;
import name.bruhmod.effect.ModEffects;
import name.bruhmod.entities.ModEntities;
import name.bruhmod.item.ModArmorMaterials;
import name.bruhmod.item.ModItemGroups;
import name.bruhmod.item.ModItems;
import name.bruhmod.potion.ModPotions;
import name.bruhmod.recipe.natural.NaturalRecipe;
import name.bruhmod.sound.ModSounds;
import name.bruhmod.util.RegistryHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class LeMod {
    public static final String MOD_ID = "bruhmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static ResourceLocation idOf(String path) {
    return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
}

    public static <T> void initializeRegistries(RegistryHelper.RegistryConsumer registerer) {
        NaturalRecipe.register(registerer);
        ModBlocks.REGISTERER.registerAll(registerer);
        ModItems.REGISTERER.registerAll(registerer);
        ModEntities.REGISTERER.registerAll(registerer);
        ModArmorMaterials.REGISTERER.registerAll(registerer);
        ModPotions.REGISTERER.registerAll(registerer);
        ModEffects.registerEffects(registerer);
        ModSounds.REGISTERER.registerAll(registerer);
        ModItemGroups.register(registerer);
    }

}
