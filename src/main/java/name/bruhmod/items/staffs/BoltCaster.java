package name.bruhmod.items.staffs;

import name.bruhmod.entities.FireBolt;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class BoltCaster extends Item {
    public BoltCaster(Item.Properties settings) {
        super(settings);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use (Level world, Player user, InteractionHand hand) {
        user.getCooldowns().addCooldown(this, 50);
        float yaw = user.getXRot();
        float pitch = user.getYRot();
        double f = -Math.sin(yaw * 0.017453292F) * Math.cos(pitch * 0.017453292F);
        double g = -Math.sin((pitch) * 0.017453292F);
        double h = Math.cos(yaw * 0.017453292F) * Math.cos(pitch * 0.017453292F);
        if(!world.isClientSide()) {
            FireBolt ball = new FireBolt(world, user, f / 50, g / 50, h / 50);
            ball.setPos(user.getX(), user.getY() + 1, user.getZ());
            world.addFreshEntity(ball);
        }
        Vec3 pos = user.position();
        world.playSound(null, new BlockPos((int) pos.x, (int) pos.y, (int) pos.z), SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS);
//        user.getItemInHand(hand).damage(1, user, playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        return InteractionResultHolder.pass(user.getItemInHand(hand));
    }
}
