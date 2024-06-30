package name.bruhmod.datagen;

import name.bruhmod.LeMod;
import name.bruhmod.blocks.ModBlocks;
import name.bruhmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class ModBlockLootProvider extends BlockLootSubProvider implements DataProvider {

    private final PackOutput packOutput;

    protected ModBlockLootProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags(), provider.join());
        this.packOutput = packOutput;
    }

    @Override
    public void generate() {
        this.dropSelf(ModBlocks.MYTHRIL_BLOCK);
        this.add(ModBlocks.MYTHRIL_ORE, this.createOreDrop(ModBlocks.MYTHRIL_ORE, ModItems.MYTHRIL_DUST));
        this.add(ModBlocks.DEEPSLATE_MYTHRIL_ORE, this.createOreDrop(ModBlocks.DEEPSLATE_MYTHRIL_ORE, ModItems.MYTHRIL_DUST));
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        this.generate();

        var pathProvider = packOutput.createPathProvider(PackOutput.Target.DATA_PACK, "loot_table/blocks");

        List<CompletableFuture<?>> output = new ArrayList<>();
        for (Map.Entry<ResourceKey<LootTable>, LootTable.Builder> e : this.map.entrySet()) {
            Path path = pathProvider.json(e.getKey().location());
            output.add(DataProvider.saveStable(cache, this.registries, LootTable.DIRECT_CODEC, e.getValue().build(), path));
        }

        return CompletableFuture.allOf(output.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return LeMod.MOD_ID + " Block Loot";
    }

}