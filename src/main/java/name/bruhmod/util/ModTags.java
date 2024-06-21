package name.bruhmod.util;

import name.bruhmod.Mod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {

    public static class ModItemTags {

        public static final TagKey<Item> CUSTOM_MAPS = TagKey.create(Registries.ITEM, Mod.idOf("custom_maps"));

    }
}
