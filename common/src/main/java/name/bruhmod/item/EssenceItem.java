package name.bruhmod.item;

import name.bruhmod.entities.ModAttributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface EssenceItem {

    int essencePerUse(ItemStack item);

    default boolean tryUse(Player user, ItemStack stack) {
        if (user.isCreative())
            return true;
        double essenceUse = this.essencePerUse(stack);
        double essence = user.getAttributeBaseValue(ModAttributes.ESSENCE);
        if (essence >= essenceUse) {
            user.getAttribute(ModAttributes.ESSENCE).setBaseValue(essence - essenceUse);
            return true;
        } else return false;
    }

}
