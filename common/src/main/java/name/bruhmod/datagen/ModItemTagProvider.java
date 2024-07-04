package name.bruhmod.datagen;

import name.bruhmod.item.ModItems;
import name.bruhmod.item.EssenceCollector;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, CompletableFuture<TagLookup<Block>> blockLookup) {
        super(output, lookup, blockLookup);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider wrapperLookup) {

        //-------------------------------------------------------------------------Armor-------------------------------------------------------------------------//

        var builder = this.tag(ItemTags.TRIMMABLE_ARMOR);
        ModItems.MYTHRIL_STUDDED_ARMOR.values().forEach(builder::add);
        ModItems.MYTHRIL_INFUSED_ARMOR.values().forEach(builder::add);

        var essence = this.tag(EssenceCollector.COLLECTOR_TAG).add(ModItems.ESSENCE_COLLECTOR);

        //----------------------------------------------------------------------Music Discs----------------------------------------------------------------------//

        this.tag(ItemTags.CREEPER_DROP_MUSIC_DISCS).add(
                ModItems.MUSIC_DISC_FALLOUT,
                ModItems.MUSIC_DISC_BLACK_MOON,
                ModItems.MUSIC_DISC_DARK_WOODS,
                ModItems.MUSIC_DISC_NIGHT_OWL,
                ModItems.MUSIC_DISC_OLD_KING,
                ModItems.MUSIC_DISC_THE_RANGER
        );

    }
}