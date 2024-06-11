package name.bruhmod.items.gems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class JewelOfCorruption extends Item{

    public JewelOfCorruption(Item.Settings settings) {
        super(settings);
    }

    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
