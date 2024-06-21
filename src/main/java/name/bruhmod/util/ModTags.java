package name.bruhmod.util;

import name.bruhmod.Mod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {

    public class ModItemTags {

        public static final TagKey<Item> CUSTOM_MAPS = TagKey.of(RegistryKeys.ITEM, Identifier.of("bruhmod", "custom_maps"));

    }
}
