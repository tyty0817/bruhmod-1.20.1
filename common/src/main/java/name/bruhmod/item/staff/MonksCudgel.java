package name.bruhmod.item.staff;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;


public class MonksCudgel extends StaffItem {

    public MonksCudgel() {
        super(new Item.Properties().rarity(Rarity.RARE));
    }

    // add slow on selection
//
//    @Override
//    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
//        if(entity instanceof Player) {
//            Player Player = (Player) entity;
//            if(Player.getItemInHand(InteractionHand.MAIN_HAND).equals(ModItems.MONKS_CUDGEL)){
//                if(Player.fallDistance > 3.0f){
//                    Player.fallDistance = 0.0f;
//                }
//            }
//            else if(Player.getItemInHand(InteractionHand.OFF_HAND).equals(ModItems.MONKS_CUDGEL)){
//                if(Player.fallDistance > 3.0f){
//                    Player.fallDistance = 0.0f;
//                }
//            }
//        }
//    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use (Level world, Player user, InteractionHand hand) {
        var stack = user.getItemInHand(hand);
        if (!tryUse(user, stack)) {
            return InteractionResultHolder.fail(stack);
        }
        super.use(world, user, hand);
//        float yaw = user.getXRot();
//        float pitch = user.getYRot();
//        float f = -Math.sin(yaw * 0.017453292F) * Math.cos(pitch * 0.017453292F);
//        float g = -Math.sin((pitch) * 0.017453292F);
//        float h = Math.cos(yaw * 0.017453292F) * Math.cos(pitch * 0.017453292F);
//        user.setVelocity(f, g + 0.5, h);
//        Vec3 pos = user.getPos();
//        world.playSound(null, new BlockPos((int) pos.x, (int) pos.y, (int) pos.z), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS);
//        return InteractionResultHolder.pass(user.getItemInHand(hand));
        user.getCooldowns().addCooldown(this, 45);
        final double maxDistance = 3.0;
        Vec3 looking = user.getViewVector(0.f).scale(maxDistance);
        AABB box = AABB.ofSize(user.position().add(new Vec3(looking.x, maxDistance / 2.0, looking.z)), maxDistance, maxDistance, maxDistance);
        for (Mob entity : world.getEntitiesOfClass(Mob.class, box)) {
            // fix this math, add damage
            Vec3 offset = entity.position().subtract(user.position());
            double xsign = offset.x / Math.abs(offset.x), zsign = offset.z / Math.abs(offset.z);
            double x = maxDistance - Math.abs(offset.x), z = maxDistance - Math.abs(offset.z);
            entity.addDeltaMovement(new Vec3(x * xsign / 2.0, Math.log(maxDistance - Math.abs(offset.y) + 1) / 2.0, z * zsign / 2.0));

            entity.igniteForSeconds(5);
//            entity.damage()
        }
        for (int x = 0; x < maxDistance; x++) {
            for (int z = 0; z < maxDistance; z++) {
                world.addParticle(ParticleTypes.FLAME, x + box.minX, box.minY, box.minZ + z, 0.0, 0.2, 0.0);
            }
        }
        world.playSound(null, user.blockPosition(), SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS);
        return InteractionResultHolder.pass(user.getItemInHand(hand));
    }

    @Override
    public int essencePerUse(ItemStack item) {
        return 5;
    }
}

