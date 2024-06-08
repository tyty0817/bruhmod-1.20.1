package name.modid.items;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FireStaff extends Item {
    public FireStaff(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand) {
        user.getItemCooldownManager().set(this, 50);
        if(!world.isClient) {
            double yaw = user.getYaw();
            double pitch = user.getPitch();
            double yVelocity = (-pitch / 90) * 5;
            double zVelocity = -(5 - Math.abs(yVelocity)) * ((Math.abs(yaw) - 90) / 90);
            double xVelocity = -Math.abs(yaw) / yaw * (5 - Math.abs(yVelocity) - Math.abs(zVelocity));

            FireballEntity ball = new FireballEntity(world, user, xVelocity, yVelocity, zVelocity, 1);
            ball.setPosition(user.getX(), user.getY() + 1, user.getZ());
            world.spawnEntity(ball);
        }

        user.getStackInHand(hand).damage(1, user, playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
