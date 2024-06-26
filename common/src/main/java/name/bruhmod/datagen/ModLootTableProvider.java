package name.bruhmod.datagen;

import name.bruhmod.item.ModItems;
import name.bruhmod.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends LootTableProvider {

    public ModLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        super(output, Collections.emptySet(), Collections.singletonList(new SubProviderEntry(ModBlockLootSubProvider::new, LootContextParamSets.BLOCK)), future);
    }


    public static class ModBlockLootSubProvider extends BlockLootSubProvider {

        protected ModBlockLootSubProvider(HolderLookup.Provider provider) {
            super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags(), provider);
        }

        @Override
        public void generate() {
            this.dropSelf(ModBlocks.MYTHRIL_BLOCK);
            this.add(ModBlocks.MYTHRIL_ORE, this.createOreDrop(ModBlocks.MYTHRIL_ORE, ModItems.MYTHRIL_DUST));
            this.add(ModBlocks.DEEPSLATE_MYTHRIL_ORE, this.createOreDrop(ModBlocks.DEEPSLATE_MYTHRIL_ORE, ModItems.MYTHRIL_DUST));
        }
    }

}