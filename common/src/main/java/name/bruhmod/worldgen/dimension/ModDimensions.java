package name.bruhmod.worldgen.dimension;

import name.bruhmod.LeMod;
import name.bruhmod.datagen.ModWorldGenProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;

/**
 * See {@link ModWorldGenProvider} for dimension implementation.
 */
public class ModDimensions {

    public static final ResourceKey<LevelStem> JUTINDIM_KEY = ResourceKey.create(Registries.LEVEL_STEM, LeMod.idOf("jutindim"));
    public static final ResourceKey<Level> JUTINDIM = ResourceKey.create(Registries.DIMENSION, LeMod.idOf("jutindim"));
    public static final ResourceKey<DimensionType> JUTINDIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, LeMod.idOf("jutindim"));

}
