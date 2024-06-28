package name.bruhmod.datagen;

import name.bruhmod.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider implements AdvancementSubProvider {

    public static AdvancementProvider provider(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
        return new AdvancementProvider(output, future, Collections.singletonList(new ModAdvancementProvider()));
    }

    @Override
    public void generate(HolderLookup.@NotNull Provider provider, Consumer<AdvancementHolder> consumer) {
        var maelstrom = Advancement.Builder.advancement().display(ModItems.MAELSTROM, Component.literal("Parry This You Filthy Casual"), Component.literal("Obtain Maelstrom"), ResourceLocation.withDefaultNamespace("textures/gui/advancements/backgrounds/adventure.png"), AdvancementType.TASK, true, true, false).addCriterion("obtain_maelstrom", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MAELSTROM)).build(ModItems.REGISTERER.getKey(ModItems.MAELSTROM));
        consumer.accept(maelstrom);
    }
}
