package name.bruhmod.datagen;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Encoder;
import com.mojang.serialization.JsonOps;
import name.bruhmod.LeMod;
import name.bruhmod.worldgen.dimension.ModDimensions;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.RegistryPatchGenerator;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import java.util.*;

public class ModWorldGenProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, ModWorldGenProvider::bootstrapType)
            .add(Registries.JUKEBOX_SONG, ModJukeboxSongProvider::bootstrap)
            .add(Registries.LEVEL_STEM, ModWorldGenProvider::bootstrapStem);

    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        context.register(ModDimensions.JUTINDIM_TYPE, new DimensionType(
                OptionalLong.of(12000),
                true,
                true,
                false,
                false,
                1.0,
                false,
                true,
                0,
                256,
                256,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.END_EFFECTS,
                1.0f,
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)
        ));
    }

    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<StructureSet> structureSetRegistry = context.lookup(Registries.STRUCTURE_SET);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);

        FlatLevelGeneratorSettings generatorSettings =
                new FlatLevelGeneratorSettings(
                        Optional.of(HolderSet.direct(structureSetRegistry.getOrThrow(BuiltinStructureSets.MINESHAFTS))),
                        biomeRegistry.getOrThrow(Biomes.COLD_OCEAN),
                        List.of()
                );

        FlatLevelSource source = new FlatLevelSource(generatorSettings);

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.JUTINDIM_TYPE), source);

        context.register(ModDimensions.JUTINDIM_KEY, stem);
    }


}
