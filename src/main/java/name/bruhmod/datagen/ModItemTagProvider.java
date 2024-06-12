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
    public void configure(RegistryWrapper.WrapperLookup arg){

        //-------------------------------------------------------------------------Armor-------------------------------------------------------------------------//

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.MYTHRIL_STUDDED_HELMET, ModItems.MYTHRIL_STUDDED_CHESTPLATE, ModItems.MYTHRIL_STUDDED_LEGGINGS, ModItems.MYTHRIL_STUDDED_BOOTS,
                        ModItems.MYTHRIL_INFUSED_HELMET, ModItems.MYTHRIL_INFUSED_CHESTPLATE, ModItems.MYTHRIL_INFUSED_LEGGINGS, ModItems.MYTHRIL_INFUSED_BOOTS);

        //----------------------------------------------------------------------Music Discs----------------------------------------------------------------------//

        getOrCreateTagBuilder(ItemTags.MUSIC_DISCS)
                .add(ModItems.FALLOUT_MUSIC_DISC);
        getOrCreateTagBuilder(ItemTags.CREEPER_DROP_MUSIC_DISCS)
            .add(ModItems.FALLOUT_MUSIC_DISC);



        getOrCreateTagBuilder(ItemTags.MUSIC_DISCS)
                .add(ModItems.BLACK_MOON_MUSIC_DISC);
        getOrCreateTagBuilder(ItemTags.CREEPER_DROP_MUSIC_DISCS)
                .add(ModItems.BLACK_MOON_MUSIC_DISC);

        getOrCreateTagBuilder(ItemTags.MUSIC_DISCS)
                .add(ModItems.DARK_WOODS_MUSIC_DISC);
        getOrCreateTagBuilder(ItemTags.CREEPER_DROP_MUSIC_DISCS)
                .add(ModItems.DARK_WOODS_MUSIC_DISC);

        getOrCreateTagBuilder(ItemTags.MUSIC_DISCS)
                .add(ModItems.NIGHT_OWL_MUSIC_DISC);
        getOrCreateTagBuilder(ItemTags.CREEPER_DROP_MUSIC_DISCS)
                .add(ModItems.NIGHT_OWL_MUSIC_DISC);

        getOrCreateTagBuilder(ItemTags.MUSIC_DISCS)
                .add(ModItems.OLD_KING_MUSIC_DISC);
        getOrCreateTagBuilder(ItemTags.CREEPER_DROP_MUSIC_DISCS)
                .add(ModItems.OLD_KING_MUSIC_DISC);

        getOrCreateTagBuilder(ItemTags.MUSIC_DISCS)
                .add(ModItems.THE_RANGER_MUSIC_DISC);
        getOrCreateTagBuilder(ItemTags.CREEPER_DROP_MUSIC_DISCS)
                .add(ModItems.THE_RANGER_MUSIC_DISC);

    }
}