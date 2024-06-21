package name.bruhmod.sound;

import name.bruhmod.Mod;
import net.minecraft.registry.*;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static name.bruhmod.Mod.MOD_ID;

public class ModSounds {

    public static RegistryKey<SoundEvent> register(String id) {
        Identifier i = Identifier.of(MOD_ID, id);
        Registry.register(Registries.SOUND_EVENT, i, SoundEvent.of(i));
        return RegistryKey.of(RegistryKeys.SOUND_EVENT, i);
    }



    public static RegistryKey<SoundEvent>
            FALLOUT = register("fallout"),
            BLACK_MOON = register("black_moon"),
            DARK_WOODS = register("dark_woods"),
            NIGHT_OWL = register("night_owl"),
            OLD_KING = register("old_king"),
            THE_RANGER = register("the_ranger");


    public static void registerSounds() {
        Mod.LOGGER.info("Registering Sounds for " + MOD_ID);
    }
}