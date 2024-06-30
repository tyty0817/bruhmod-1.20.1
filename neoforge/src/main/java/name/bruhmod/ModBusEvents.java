package name.bruhmod;

import name.bruhmod.datagen.ModDataGenerator;
import name.bruhmod.entities.ModEntities;
import name.bruhmod.util.RegistryHelper;
import net.minecraft.core.Registry;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

//@EventBusSubscriber(Mod)
public class ModBusEvents {

    @SubscribeEvent
    public static void registerSetup(final RegisterEvent event) {
        LeMod.initializeRegistries(new RegistryHelper.RegistryConsumer() {
            @Override
            public <T> void register(Registry<T> registry, Consumer<BiConsumer<ResourceLocation, T>> consumer) {
                event.register(registry.key(), r -> consumer.accept(r::register));
            }
        });
    }

    @SubscribeEvent
    private static void entityAttributeSetup(final EntityAttributeCreationEvent event) {
        ModEntities.registerAttributes((type, builder) -> event.put(type, builder.build()));
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
    }

}
