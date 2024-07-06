package name.bruhmod.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public interface EssenceItem {

    int essencePerUse(ItemStack item);

    default boolean tryUse(LivingEntity user, Inventory inventory, ItemStack stack) {
        if (user instanceof Player player && player.isCreative())
            return true;
        final int[] essenceUse = {this.essencePerUse(stack)};
        HashMap<Integer, Integer> essenceSlots = new HashMap<>();
        for (int slot = 0; slot < inventory.getContainerSize(); slot++) {
            ItemStack invStack = inventory.getItem(slot);
            if (invStack.has(ModDataComponents.ESSENCE)) {
                essenceSlots.put(slot, invStack.get(ModDataComponents.ESSENCE));
            }
        }
        if (essenceSlots.values().stream().mapToInt(i -> i).sum() > essenceUse[0]) {
            List<Integer> keys = new ArrayList<>(essenceSlots.keySet().stream().toList());
            var random = user.getRandom();
            while (essenceUse[0] > 0) {
                // does not use minecraft random
                Collections.shuffle(keys);
                for (int key : keys) {
                    essenceSlots.compute(key, (k, available) -> {
                        int used = random.nextInt(0, Math.min(available, essenceUse[0]));
                        essenceUse[0] -= used;
                        return available - used;
                    });
                }
            }
            essenceSlots.forEach((key, essence) -> inventory.getItem(key).set(ModDataComponents.ESSENCE, essence));
            return true;
        } else return false;
    }

}
