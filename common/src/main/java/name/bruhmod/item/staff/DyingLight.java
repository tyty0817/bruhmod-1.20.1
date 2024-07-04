package name.bruhmod.item.staff;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class DyingLight extends StaffItem {

    public DyingLight() {
        super(new Properties().durability(256));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        var stack = user.getItemInHand(hand);
        if (!tryUse(user, stack)) {
            return InteractionResultHolder.fail(stack);
        }
        super.use(world, user, hand);
        float yaw = user.getXRot();
        float pitch = user.getYRot();
        double f = -Math.sin(yaw * 0.017453292F) * Math.cos(pitch * 0.017453292F);
        double g = -Math.sin((pitch) * 0.017453292F);
        double h = Math.cos(yaw * 0.017453292F) * Math.cos(pitch * 0.017453292F);
        if(!world.isClientSide()) {
            LargeFireball ball = new LargeFireball(world, user, new Vec3(f * 2, g * 2, h * 2), 1);
            ball.setPos(user.getX(), user.getY() + 1, user.getZ());
            world.addFreshEntity(ball);
        }
        Vec3 pos = user.position();
        world.playSound(null, new BlockPos((int) pos.x, (int) pos.y, (int) pos.z), SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS);
//        user.getItemInHand(hand).damage(1, user, playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        return InteractionResultHolder.pass(user.getItemInHand(hand));
    }

    @Override
    public int essencePerUse(ItemStack item) {
        return 2;
    }
}
