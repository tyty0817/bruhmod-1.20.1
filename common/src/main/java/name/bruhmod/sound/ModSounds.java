package name.bruhmod.sound;

import name.bruhmod.LeMod;
import name.bruhmod.util.RegistryHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {

    public static final RegistryHelper<SoundEvent> REGISTERER = new RegistryHelper<>(BuiltInRegistries.SOUND_EVENT);

    public static Holder<SoundEvent>
            FALLOUT = register("fallout"),
            BLACK_MOON = register("black_moon"),
            DARK_WOODS = register("dark_woods"),
            NIGHT_OWL = register("night_owl"),
            OLD_KING = register("old_king"),
            THE_RANGER = register("the_ranger");

    public static Holder<SoundEvent> register(String name) {
        var i = LeMod.idOf(name);
        return Holder.direct(REGISTERER.add(i, SoundEvent.createVariableRangeEvent(i)));
    }
}