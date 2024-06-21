package name.bruhmod.items.staffs;

import name.bruhmod.Mod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;


public class MonksCudgel extends Item {

    public MonksCudgel(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item." + Mod.MOD_ID + ".monks_cudgel.tooltip"));

        super.appendTooltip(stack, context, tooltip, type);
    }

    // add slow on selection
//
//    @Override
//    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
//        if(entity instanceof PlayerEntity) {
//            PlayerEntity Player = (PlayerEntity) entity;
//            if(Player.getStackInHand(Hand.MAIN_HAND).equals(ModItems.MONKS_CUDGEL)){
//                if(Player.fallDistance > 3.0f){
//                    Player.fallDistance = 0.0f;
//                }
//            }
//            else if(Player.getStackInHand(Hand.OFF_HAND).equals(ModItems.MONKS_CUDGEL)){
//                if(Player.fallDistance > 3.0f){
//                    Player.fallDistance = 0.0f;
//                }
//            }
//        }
//    }

    @Override
    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand) {
//        user.getItemCooldownManager().set(this, 15);
//        float yaw = user.getYaw();
//        float pitch = user.getPitch();
//        float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
//        float g = -MathHelper.sin((pitch) * 0.017453292F);
//        float h = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
//        user.setVelocity(f, g + 0.5, h);
//        Vec3d pos = user.getPos();
//        world.playSound(null, new BlockPos((int) pos.x, (int) pos.y, (int) pos.z), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS);
//        return TypedActionResult.pass(user.getStackInHand(hand));
        user.getItemCooldownManager().set(this, 45);
        final double maxDistance = 3.0;
        Vec3d looking = user.getRotationVec(0.f).multiply(maxDistance);
        Box box = Box.of(user.getPos().add(new Vec3d(looking.x, maxDistance / 2.0, looking.z)), maxDistance, maxDistance, maxDistance);
        for (Entity e : world.getOtherEntities(user, box, entity -> entity instanceof MobEntity)) {
            MobEntity entity = (MobEntity) e;
            // fix this math, add damage
            Vec3d offset = entity.getPos().subtract(user.getPos());
            double xsign = offset.x / Math.abs(offset.x), zsign = offset.z / Math.abs(offset.z);
            double x = maxDistance - Math.abs(offset.x), z = maxDistance - Math.abs(offset.z);
            entity.addVelocity(new Vec3d(x * xsign / 2.0, Math.log(maxDistance - Math.abs(offset.y) + 1) / 2.0, z * zsign / 2.0));

            entity.setOnFireFor(5);
//            entity.damage()
        }
        for (int x = 0; x < maxDistance; x++) {
            for (int z = 0; z < maxDistance; z++) {
                world.addParticle(ParticleTypes.FLAME, x + box.minX, box.minY, box.minZ + z, 0.0, 0.2, 0.0);
            }
        }
        world.playSound(null, user.getBlockPos(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS);
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}

