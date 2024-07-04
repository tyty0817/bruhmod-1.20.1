package name.bruhmod.item.staff;

import name.bruhmod.item.EssenceItem;
import name.bruhmod.item.ModItems;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public abstract class StaffItem extends Item implements EssenceItem {

    public StaffItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.add(Component.translatable(Util.makeDescriptionId("item", ModItems.REGISTERER.getKey(this)) + ".tooltip"));

        super.appendHoverText(stack, context, tooltip, type);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        if (!user.isCreative())
            user.getCooldowns().addCooldown(this, 50);
        return super.use(world, user, hand);
    }

}
