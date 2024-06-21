package name.bruhmod.items.staffs;

import name.bruhmod.Mod;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Maelstrom extends Item {
    public Maelstrom(Item.Properties settings) {
    super(settings);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        tooltip.add(Component.translatable("item." + Mod.MOD_ID + ".maelstrom.tooltip"));

        super.appendHoverText(stack, context, tooltip, type);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use (Level world, Player user, InteractionHand hand) {
        user.getCooldowns().addCooldown(this, 50);
        HitResult hit = user.pick(128, 1, true);
        if(hit.getType() != HitResult.Type.MISS) {
            LightningBolt lightningEntity = new LightningBolt(EntityType.LIGHTNING_BOLT, world);
            lightningEntity.setPos(hit.getLocation());
            world.addFreshEntity(lightningEntity);
        }
//        user.getItemInHand(hand).damage(1, user);
        return InteractionResultHolder.pass(user.getItemInHand(hand));
    }
}
