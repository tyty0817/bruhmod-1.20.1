package name.bruhmod.sound;

import com.mojang.datafixers.util.Pair;
import name.bruhmod.Mod;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;

public class ModSounds {

    public static Pair<ResourceLocation, JukeboxSong>
            FALLOUT = createMusicDisc("fallout", 24, 7),
            BLACK_MOON = createMusicDisc("black_moon",203, 7),
            DARK_WOODS = createMusicDisc("dark_woods", 184, 7),
            NIGHT_OWL = createMusicDisc("night_owl", 187, 7),
            OLD_KING = createMusicDisc("old_king", 209, 7),
            THE_RANGER = createMusicDisc("the_ranger", 192, 7);

    public static void registerSounds() {
        Mod.LOGGER.info("Registering Sounds for " + Mod.MOD_ID);
    }

    public static Holder<SoundEvent> register(String name) {
        var i = Mod.idOf(name);
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, i, SoundEvent.createVariableRangeEvent(i));
    }
    
    public static Pair<ResourceLocation, JukeboxSong> createMusicDisc(String name, int length, int comparatorOutput) {
        var id = Mod.idOf(name);
        return new Pair<>(id, new JukeboxSong(
                Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id)),
                Component.translatable(Util.makeDescriptionId("jukebox_song", ResourceKey.create(Registries.JUKEBOX_SONG, id).location())),
                (float) length,
                comparatorOutput
        ));
    }
}