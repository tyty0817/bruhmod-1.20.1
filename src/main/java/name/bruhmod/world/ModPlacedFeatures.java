package name.bruhmod.world;

import name.bruhmod.Mod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> MYTHRIL_ORE_PLACED_KEY = registerKey("mythril_ore_placed");

    public static void boostrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatureHolderLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(MYTHRIL_ORE_PLACED_KEY, new PlacedFeature(
                configuredFeatureHolderLookup.getOrThrow(ModConfiguredFeatures.MYTHRIL_ORE_KEY),
                ModOrePlacement.modifiersWithCount(4, // Veins per Chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80)))));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Mod.idOf(name));
    }
}