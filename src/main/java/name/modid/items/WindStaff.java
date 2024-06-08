package name.modid.items;

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

public class WindStaff extends Item{
    public WindStaff(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand) {
        float yaw = user.getYaw();
        float pitch = user.getPitch();
        float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        float g = -MathHelper.sin((pitch) * 0.017453292F);
        float h = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        user.addVelocity(f, g + 0.5, h);
        Vec3d pos = user.getPos();
        world.playSound(null, new BlockPos((int) pos.x, (int) pos.y, (int) pos.z), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS);
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
