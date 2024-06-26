package name.bruhmod.world.gen;

import name.bruhmod.Mod;
import name.bruhmod.blocks.ModBlocks;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;

import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_ORES;

public class ModOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                UNDERGROUND_ORES, ResourceKey.create(Registries.PLACED_FEATURE, BuiltInRegistries.BLOCK.getKey(ModBlocks.MYTHRIL_ORE)));
    }
}