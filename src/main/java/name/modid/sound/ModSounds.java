package name.modid.sound;

import name.modid.Mod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent GERIATRIC_TAVERN = registerSoundEvent("geriatric_tavern");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(Mod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        Mod.LOGGER.info("Registering Sounds for " + Mod.MOD_ID);
    }
}
