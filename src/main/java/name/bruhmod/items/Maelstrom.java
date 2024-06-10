package name.bruhmod.items;

import name.bruhmod.Mod;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Maelstrom extends Item {
    public Maelstrom(Item.Settings settings) {
    super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item." + Mod.MOD_ID + ".maelstrom.tooltip"));

        super.appendTooltip(stack, null, tooltip, context);
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
        user.getStackInHand(hand).damage(1, user, playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
