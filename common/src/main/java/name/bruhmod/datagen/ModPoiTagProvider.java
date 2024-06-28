package name.bruhmod.datagen;

import name.bruhmod.LeMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.tags.PoiTypeTags;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModPoiTagProvider extends PoiTypeTagsProvider {

    public ModPoiTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(packOutput, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider lookup) {
        this.getOrCreateRawBuilder(PoiTypeTags.ACQUIRABLE_JOB_SITE)
                .addOptionalTag(LeMod.idOf("soundpoi"));
    }
}