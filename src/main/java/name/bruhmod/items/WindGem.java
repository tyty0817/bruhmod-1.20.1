package name.bruhmod.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class WindGem extends Item{

    public WindGem(Item.Settings settings) {
        super(settings);
    }

    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
