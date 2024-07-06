package name.bruhmod.item;

import name.bruhmod.LeMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Temporary class for an essence collector. Multiple items should be able to collect essence
 */
public class EssenceCollector extends Item {

    public static final TagKey<Item> HAS_ESSENCE = TagKey.create(Registries.ITEM, LeMod.idOf("has_essence"));

    public EssenceCollector() {
        super(new Item.Properties());
    }

    public static Stream<ItemStack> itemsWithEssence(Container inventory) {
        return IntStream.range(0, inventory.getContainerSize()).mapToObj(inventory::getItem).filter(p -> p.is(HAS_ESSENCE));
    }

    public static int totalEssence(Container inventory) {
        return itemsWithEssence(inventory).mapToInt(p -> p.getOrDefault(ModDataComponents.ESSENCE,0)).sum();
    }

    public static void onEntityKill(Player player, LivingEntity killed) {
//        if (player.isCreative())
//            return;

        List<ItemStack> items = itemsWithEssence(player.getInventory()).toList();

        int essence = (int) (killed.getMaxHealth() * 2);

        if (items.size() == 1) {
            ItemStack item = items.getFirst();
            int current = item.getOrDefault(ModDataComponents.ESSENCE, 0);
            item.set(ModDataComponents.ESSENCE, current + essence);
        } else if (!items.isEmpty()) {
            var random = player.getRandom();

            // randomly assign essence to items

            items = new ArrayList<>(items);

            while (essence > 0) {
                Collections.shuffle(items);
                for (ItemStack item : items) {
                    int current = item.getOrDefault(ModDataComponents.ESSENCE, 0);
                    int addition = random.nextIntBetweenInclusive(0, essence);
                    essence -= addition;
                    item.set(ModDataComponents.ESSENCE, current + addition);
                }
            }
        }
    }

}
