package name.bruhmod.datagen;

import com.mojang.datafixers.util.Pair;
import name.bruhmod.Mod;
import name.bruhmod.blocks.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenerator extends FabricDynamicRegistryProvider {

    public ModWorldGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
    }

    @Override
    public @NotNull String getName() {
        return "Level Gen";
    }

    public static final Pair<ResourceKey<ConfiguredFeature<?, ?>>, ResourceKey<PlacedFeature>> MYTHRIL_ORE_KEYS = keyPair("mythril_ore");

    public static void configuredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplacables = new TagMatchTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplacables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldMythrilOres =
                List.of(OreConfiguration.target(stoneReplacables, ModBlocks.MYTHRIL_ORE.defaultBlockState()),
                        OreConfiguration.target(deepslateReplacables, ModBlocks.DEEPSLATE_MYTHRIL_ORE.defaultBlockState()));

        context.register(MYTHRIL_ORE_KEYS.getFirst(), new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(overworldMythrilOres, 12)));
    }

    public static void placedFeatures(BootstrapContext<PlacedFeature> context) {
        var configuredFeatureHolderLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(placedKey("mythril_ore"), new PlacedFeature(
                configuredFeatureHolderLookup.getOrThrow(MYTHRIL_ORE_KEYS.getFirst()),
                Placement.modifiersWithCount(4, // Veins per Chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80)))));
    }

    public static Pair<ResourceKey<ConfiguredFeature<?, ?>>, ResourceKey<PlacedFeature>> keyPair(String name) {
        return new Pair<>(configuredKey(name), placedKey(name));
    }

    public static ResourceKey<PlacedFeature> placedKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Mod.idOf(name));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> configuredKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Mod.idOf(name));
    }

    public static class Placement {
        public static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
            return List.of(countModifier, InSquarePlacement.spread(), heightModifier, BiomeFilter.biome());
        }

        public static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
            return modifiers(CountPlacement.of(count), heightModifier);
        }

        public static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
            return modifiers(RarityFilter.onAverageOnceEvery(chance), heightModifier);
        }
    }
}
