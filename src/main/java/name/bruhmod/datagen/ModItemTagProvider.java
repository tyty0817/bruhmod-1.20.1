package name.bruhmod.datagen;

import name.bruhmod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    public void configure(RegistryWrapper.WrapperLookup arg) {

        //-------------------------------------------------------------------------Armor-------------------------------------------------------------------------//

        var builder = getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR);
        ModItems.MYTHRIL_STUDDED_ARMOR.values().forEach(builder::add);
        ModItems.MYTHRIL_INFUSED_ARMOR.values().forEach(builder::add);

        //----------------------------------------------------------------------Music Discs----------------------------------------------------------------------//

        getOrCreateTagBuilder(ItemTags.CREEPER_DROP_MUSIC_DISCS).add(
                ModItems.MUSIC_DISC_FALLOUT,
                ModItems.MUSIC_DISC_BLACK_MOON,
                ModItems.MUSIC_DISC_DARK_WOODS,
                ModItems.MUSIC_DISC_NIGHT_OWL,
                ModItems.MUSIC_DISC_OLD_KING,
                ModItems.MUSIC_DISC_THE_RANGER
        );

    }
}