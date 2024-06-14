package name.bruhmod.sound;

import name.bruhmod.Mod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static Identifier registerId(String name) {
        return new Identifier(Mod.MOD_ID + ":" + name);
    }

    public static SoundEvent registerSound(String id) {
        Identifier identifier = registerId(id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }



    public static final Identifier FALLOUT_SOUND = new Identifier("bruhmod:fallout");
    public static SoundEvent FALLOUT = SoundEvent.of(FALLOUT_SOUND);

    public static final Identifier BLACK_MOON_SOUND = new Identifier("bruhmod:black_moon");
    public static SoundEvent BLACK_MOON = SoundEvent.of(BLACK_MOON_SOUND);

    public static final Identifier DARK_WOODS_SOUND = new Identifier("bruhmod:dark_woods");
    public static SoundEvent DARK_WOODS = SoundEvent.of(DARK_WOODS_SOUND);

    public static final Identifier NIGHT_OWL_SOUND = new Identifier("bruhmod:night_owl");
    public static SoundEvent NIGHT_OWL = SoundEvent.of(NIGHT_OWL_SOUND);

    public static final Identifier OLD_KING_SOUND = new Identifier("bruhmod:old_king");
    public static SoundEvent OLD_KING = SoundEvent.of(OLD_KING_SOUND);

    public static final Identifier THE_RANGER_SOUND = new Identifier("bruhmod:the_ranger");
    public static SoundEvent THE_RANGER = SoundEvent.of(THE_RANGER_SOUND);




    public static void registerSounds() {
        Mod.LOGGER.info("Registering Sounds for " + Mod.MOD_ID);
    }
}