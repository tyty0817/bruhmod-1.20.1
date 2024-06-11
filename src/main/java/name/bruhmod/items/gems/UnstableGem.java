package name.bruhmod.items.gems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class UnstableGem extends Item{

    public UnstableGem(Settings settings) {
        super(settings);
    }

    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
