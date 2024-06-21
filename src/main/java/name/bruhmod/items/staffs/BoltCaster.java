package name.bruhmod.items.staffs;

import name.bruhmod.entities.FireBolt;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BoltCaster extends Item {
    public BoltCaster(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand) {
        user.getItemCooldownManager().set(this, 50);
        float yaw = user.getYaw();
        float pitch = user.getPitch();
        float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        float g = -MathHelper.sin((pitch) * 0.017453292F);
        float h = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        if(!world.isClient) {
            FireBolt ball = new FireBolt(world, user, f / 50, g / 50, h / 50);
            ball.setPosition(user.getX(), user.getY() + 1, user.getZ());
            world.spawnEntity(ball);
        }
        Vec3d pos = user.getPos();
        world.playSound(null, new BlockPos((int) pos.x, (int) pos.y, (int) pos.z), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS);
//        user.getStackInHand(hand).damage(1, user, playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
