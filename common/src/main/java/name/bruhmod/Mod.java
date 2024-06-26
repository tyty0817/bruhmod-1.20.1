package name.bruhmod;

import name.bruhmod.blocks.ModBlocks;
import name.bruhmod.effect.ModEffects;
import name.bruhmod.item.ModArmorMaterials;
import name.bruhmod.item.ModItemGroups;
import name.bruhmod.item.ModItems;
import name.bruhmod.recipe.natural.NaturalRecipe;
import name.bruhmod.sound.ModSounds;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mod {
    public static final String MOD_ID = "bruhmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static ResourceLocation idOf(String path) {
    return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
}

    public static void initialize() {
        NaturalRecipe.register();
        ModItems.register();
        ModBlocks.register();
        ModArmorMaterials.register();
        ModEffects.registerEffects();
        ModSounds.registerSounds();
        ModItemGroups.register();
    }

}
