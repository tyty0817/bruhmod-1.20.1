package name.bruhmod.item;

import name.bruhmod.LeMod;
import name.bruhmod.entities.ModAttributes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EssenceCollector extends Item {

    public static final TagKey<Item> COLLECTOR_TAG = TagKey.create(Registries.ITEM, LeMod.idOf("essence_collector"));
    private static final double MULTIPLIER = 1.0 / 5.0;

    public EssenceCollector() {
        super(new Item.Properties());
    }

    public static void onEntityKill(Player player, LivingEntity killed) {
        if (player.isCreative())
            return;
        var inventory = player.getInventory();
        if (inventory.contains(COLLECTOR_TAG)) {
            var attribute = player.getAttribute(ModAttributes.ESSENCE);
            attribute.setBaseValue(attribute.getBaseValue() + killed.getMaxHealth() * MULTIPLIER);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (!level.isClientSide) {
            LeMod.LOGGER.info("Player Essence: " + player.getAttributeBaseValue(ModAttributes.ESSENCE));
        }
        return super.use(level, player, usedHand);
    }
}
