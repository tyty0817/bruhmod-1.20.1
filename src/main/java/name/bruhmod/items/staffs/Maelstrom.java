package name.bruhmod.items.staffs;

import name.bruhmod.Mod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

import java.util.List;

public class Maelstrom extends Item {
    public Maelstrom(Item.Settings settings) {
    super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item." + Mod.MOD_ID + ".maelstrom.tooltip"));

        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand) {
        user.getItemCooldownManager().set(this, 50);
        HitResult hit = user.raycast(128, 1, true);
        if(hit.getType() != HitResult.Type.MISS) {
            LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
            lightningEntity.setPosition(hit.getPos());
            world.spawnEntity(lightningEntity);
        }
//        user.getStackInHand(hand).damage(1, user);
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
