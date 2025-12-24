package name.bruhmod;

import name.bruhmod.blocks.ModBlocks;
import name.bruhmod.datagen.ModDataGenerator;
import name.bruhmod.datagen.ModWorldGenProvider;
import name.bruhmod.effect.ModEffects;
import name.bruhmod.entity.ModAttributes;
import name.bruhmod.entity.ModEntities;
import name.bruhmod.item.ModArmorMaterials;
import name.bruhmod.item.ModDataComponents;
import name.bruhmod.item.ModItemGroups;
import name.bruhmod.item.ModItems;
import name.bruhmod.potion.ModPotions;
import name.bruhmod.recipe.ModRecipes;
import name.bruhmod.sound.ModSounds;
import name.bruhmod.util.RegistryHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.Set;
import java.util.function.BiConsumer;

//@EventBusSubscriber(Mod)
public class ModBusEvents {

    private static <T> BiConsumer<ResourceLocation, T> registry(final RegisterEvent event, ResourceKey<Registry<T>> registry) {
        return (id, t) -> event.register(registry, id, () -> t);
    }

    @SubscribeEvent
    public static void registerSetup(final RegisterEvent event) {
        ModRecipes.register((id, pair) -> {
            event.register(Registries.RECIPE_TYPE, id, pair::getFirst);
            event.register(Registries.RECIPE_SERIALIZER, id, pair::getSecond);
        });
        ModBlocks.BLOCKS.registerAll(registry(event, Registries.BLOCK));
        ModItems.ITEMS.registerAll(registry(event, Registries.ITEM));
        ModDataComponents.register(registry(event, Registries.DATA_COMPONENT_TYPE));
        ModEntities.ENTITY_TYPES.registerAll(registry(event, Registries.ENTITY_TYPE));
        ModArmorMaterials.REGISTERER.registerAll(registry(event, Registries.ARMOR_MATERIAL));
        ModPotions.register(registry(event, Registries.POTION));
        ModEffects.registerEffects(registry(event, Registries.MOB_EFFECT));
        ModSounds.SOUNDS.registerAll(registry(event, Registries.SOUND_EVENT));
        ModItemGroups.register(registry(event, Registries.CREATIVE_MODE_TAB));
    }

    @SubscribeEvent
    private static void entityAttributeSetup(final EntityAttributeCreationEvent event) {
        ModAttributes.registerAttributes((type, builder) -> event.put(type, builder.build()));
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        LeMod.LOGGER.info("Running datagen for " + LeMod.MOD_ID + "!");
        var generator = event.getGenerator();
        var future = event.getLookupProvider();
        ModDataGenerator.generate(new ModDataGenerator.AddProvider() {
            @Override
            public <T extends DataProvider> T addProvider(DataProvider.Factory<T> factory) {
                return generator.addProvider(true, factory);
            }
        }, future);
//        generator.addProvider(true, new DatapackBuiltinEntriesProvider(generator.getPackOutput(), future, ModWorldGenProvider.BUILDER, Set.of(LeMod.MOD_ID)));
    }

}
