package name.bruhmod.datagen;

import com.mojang.datafixers.util.Pair;
import name.bruhmod.sound.ModSounds;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.JukeboxSong;

import java.util.concurrent.CompletableFuture;

public class ModMusicDiscProvider extends FabricDynamicRegistryProvider {
    public ModMusicDiscProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.JUKEBOX_SONG));
    }

    @Override
    public String getName() {
        return "Music Discs";
    }

    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
        register(context, ModSounds.FALLOUT);
        register(context, ModSounds.BLACK_MOON);
        register(context, ModSounds.DARK_WOODS);
        register(context, ModSounds.NIGHT_OWL);
        register(context, ModSounds.OLD_KING);
        register(context, ModSounds.THE_RANGER);
    }

    private static void register(BootstrapContext<JukeboxSong> context, Pair<ResourceLocation, JukeboxSong> song) {
        context.register(ResourceKey.create(Registries.JUKEBOX_SONG, song.getFirst()), song.getSecond());
    }
}
