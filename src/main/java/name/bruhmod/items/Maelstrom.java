package name.bruhmod.items;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Maelstrom extends Item {
    public Maelstrom(Item.Settings settings) {
    super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand) {
        user.getItemCooldownManager().set(this, 50);
        if(!world.isClient) {

            HitResult hit = user.raycast(1000, 1, true);
            if(hit.getType() == HitResult.Type.BLOCK) {
                BlockHitResult blockHit = (BlockHitResult) hit;
                BlockPos blockPos = blockHit.getBlockPos();
                BlockState blockState = world.getBlockState(blockPos);

                Entity lighting_bolt = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
                Vec3d pos = blockPos.toCenterPos();
                lighting_bolt.setPos(pos.x, pos.y, pos.z);
                world.spawnEntity(lighting_bolt);
            }
        }

        user.getStackInHand(hand).damage(1, user, playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
