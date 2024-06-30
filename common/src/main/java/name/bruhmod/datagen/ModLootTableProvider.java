package name.bruhmod.datagen;

import name.bruhmod.item.ModItems;
import name.bruhmod.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider {
//    extends
// LootTableProvider {
//
//    public ModLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
//        super(output, required(), Collections.singletonList(new SubProviderEntry(ModBlockLootSubProvider::new, LootContextParamSets.BLOCK)), future);
//    }
//
//    static Set<ResourceKey<LootTable>> required() {
//        HashSet<ResourceKey<LootTable>> sets = new HashSet<>(BuiltInLootTables.all());
//        sets.add(ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.withDefaultNamespace("blocks/stone")));
//        return sets;
//    }
//
//
//
}