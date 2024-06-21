package name.bruhmod.sound;

import name.bruhmod.Mod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;

import static name.bruhmod.Mod.MOD_ID;

public class ModSounds {

    public static ResourceKey<SoundEvent> register(String id) {
        var i = Mod.idOf(id);
        Registry.register(BuiltInRegistries.SOUND_EVENT, i, SoundEvent.createVariableRangeEvent(i));
        return ResourceKey.create(Registries.SOUND_EVENT, i);
    }



    public static ResourceKey<SoundEvent>
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