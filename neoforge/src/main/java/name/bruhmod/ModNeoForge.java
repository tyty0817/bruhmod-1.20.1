package name.bruhmod;

import name.bruhmod.util.RegistryHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(LeMod.MOD_ID)
public class ModNeoForge {
    public ModNeoForge(IEventBus modEventBus) {;
        // init registries
        modEventBus.register(ModBusEvents.class);
    }

//    @SubscribeEvent
//    private static void commonSetup(final FMLCommonSetupEvent setupEvent) {
//        LeMod.LOGGER.info("HELLO FROM COMMON SETUP FOR " + LeMod.MOD_ID);
//    }

}