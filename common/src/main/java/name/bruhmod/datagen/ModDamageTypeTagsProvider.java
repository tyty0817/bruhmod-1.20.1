package name.bruhmod.datagen;

import name.bruhmod.entity.ModDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;

import java.util.concurrent.CompletableFuture;

public class ModDamageTypeTagsProvider extends DamageTypeTagsProvider {

    public ModDamageTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {


//        this.tag(DamageTypeTags.BYPASSES_COOLDOWN).add(ModDamageTypes.PROJECTILE_SPRAY);
//        this.tag(DamageTypeTags.IS_PROJECTILE).add(ModDamageTypes.PROJECTILE_SPRAY);

    }
}
