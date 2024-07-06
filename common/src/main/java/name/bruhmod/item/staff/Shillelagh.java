package name.bruhmod.item.staff;

import name.bruhmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;


public class Shillelagh extends StaffItem {

    public Shillelagh() {
        super(new Item.Properties().rarity(Rarity.EPIC));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if (entity instanceof Player) {
            Player player = (Player) entity;
            if (player.getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.SHILLELAGH)) { //comparing an Item with and ItemStack but it works?
                if (player.fallDistance > 3.0f) {
                    player.fallDistance = 0.0f;
                }
            } else if (player.getItemInHand(InteractionHand.OFF_HAND).is(ModItems.SHILLELAGH)) { //comparing an Item with and ItemStack but it works?
                if (player.fallDistance > 3.0f) {
                    player.fallDistance = 0.0f;
                }
            }
        }
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        var stack = user.getItemInHand(hand);
        if (!tryUse(user, user.getInventory(), stack)) {
            return InteractionResultHolder.fail(stack);
        }
        super.use(world, user, hand);
        double sash_buff = 1.5;
        float yaw = user.getXRot();
        float pitch = user.getYRot();
        double f = -Math.sin(yaw * 0.017453292F) * Math.cos(pitch * 0.017453292F);
        double g = -Math.sin((pitch) * 0.017453292F);
        double h = Math.cos(yaw * 0.017453292F) * Math.cos(pitch * 0.017453292F);
        user.setDeltaMovement(f * sash_buff, (g + 0.5) * sash_buff, h * sash_buff);
        Vec3 pos = user.position();
        world.playSound(null, new BlockPos((int) pos.x, (int) pos.y, (int) pos.z), SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS);
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public int essencePerUse(ItemStack item) {
        return 10;
    }
}
