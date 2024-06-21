package name.bruhmod.world.gen;

import name.bruhmod.datagen.ModWorldGenerator;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;

import static net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_ORES;

public class ModOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                UNDERGROUND_ORES, ModWorldGenerator.MYTHRIL_ORE_KEYS.getSecond());
    }
}