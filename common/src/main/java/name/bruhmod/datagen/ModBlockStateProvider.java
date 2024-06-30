package name.bruhmod.datagen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import name.bruhmod.LeMod;
import name.bruhmod.blocks.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModBlockStateProvider extends BlockModelGenerators implements DataProvider {

    private final PackOutput packOutput;

    protected final List<BlockStateGenerator> blockstates;
    protected final Map<ResourceLocation, Supplier<JsonElement>> models;

    public ModBlockStateProvider(PackOutput output) {
        this(output, new ArrayList<>(), new HashMap<>());
    }

    private ModBlockStateProvider(PackOutput output, List<BlockStateGenerator> blockstates, Map<ResourceLocation, Supplier<JsonElement>> models) {
        super(blockstates::add, models::put, (item) -> {});
        this.packOutput = output;
        this.blockstates = blockstates;
        this.models = models;
    }

    public void generate() {
        this.createTrivialCube(ModBlocks.MYTHRIL_ORE);
        this.createTrivialCube(ModBlocks.DEEPSLATE_MYTHRIL_ORE);
        this.createTrivialCube(ModBlocks.MYTHRIL_BLOCK);
    }

    @Override
    public void createTrivialCube(@NotNull Block block) {
        super.createTrivialCube(block);
        JsonObject model = new JsonObject();
        model.add("parent", new JsonPrimitive(ModelLocationUtils.getModelLocation(block).toString()));
        models.put(ModelLocationUtils.getModelLocation(block.asItem()), () -> model);
    }

    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput cache) {
        generate();

        PackOutput.PathProvider blockstatePathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "blockstates");
        PackOutput.PathProvider modelPathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models");

        List<CompletableFuture<?>> output = new ArrayList<>();

        for (BlockStateGenerator state : blockstates) {
            ResourceLocation id = BuiltInRegistries.BLOCK.getKey(state.getBlock());
            Path path = blockstatePathProvider.json(id);
            output.add(DataProvider.saveStable(cache, state.get(), path));
        }

        models.forEach((modelId, json) -> {
            Path path = modelPathProvider.json(modelId);
            output.add(DataProvider.saveStable(cache, json.get(), path));
        });

        return CompletableFuture.allOf(output.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return LeMod.MOD_ID + " Block States and Models";
    }


}
