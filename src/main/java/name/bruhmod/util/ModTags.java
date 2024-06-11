package name.bruhmod.util;

import name.bruhmod.Mod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(Mod.MOD_ID, name));
        }
    }


    public static class Items {
        public static final TagKey<Item> CUSTOM_MAPS = createTag("custom_maps");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(Mod.MOD_ID, name));
        }
    }

}
