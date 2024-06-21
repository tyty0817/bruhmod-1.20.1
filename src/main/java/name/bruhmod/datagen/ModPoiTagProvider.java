package name.bruhmod.datagen;

import name.bruhmod.Mod;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.world.entity.ai.village.poi.PoiType;

import java.util.concurrent.CompletableFuture;

public class ModPoiTagProvider extends TagsProvider<PoiType> {


    protected ModPoiTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(packOutput, Registries.POINT_OF_INTEREST_TYPE, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookup) {
        this.getOrCreateRawBuilder(PoiTypeTags.ACQUIRABLE_JOB_SITE)
                .addOptionalTag(Mod.idOf("soundpoi"));
    }
}