package name.bruhmod;

import name.bruhmod.datagen.ModDataGenerator;
import name.bruhmod.entities.ModAttributes;
import name.bruhmod.util.RegistryHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

//@EventBusSubscriber(Mod)
public class ModBusEvents {

    @SubscribeEvent
    public static void registerSetup(final RegisterEvent event) {
        LeMod.initializeRegistries(new RegistryHelper.Provider() {
            @Override
            public <T> Holder<T> register(Registry<T> registry, ResourceLocation id, T entry) {
                event.register(registry.key(), r -> r.register(id, entry));
                return registry.getHolderOrThrow(ResourceKey.create(registry.key(), id));
            }
        });
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
    }

}
