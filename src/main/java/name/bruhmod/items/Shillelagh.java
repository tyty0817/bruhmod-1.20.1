package name.bruhmod.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
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


public class Shillelagh extends Item {

    public Shillelagh(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(entity instanceof PlayerEntity) {
            PlayerEntity Player = (PlayerEntity) entity;
            if(Player.getStackInHand(Hand.MAIN_HAND).getItem().toString().compareTo("shillelagh") == 0){
                if(Player.fallDistance > 3.0f){
                    Player.fallDistance = 0.0f;
                }
            }
            else if(Player.getStackInHand(Hand.OFF_HAND).getItem().toString().compareTo("shillelagh") == 0){
                if(Player.fallDistance > 3.0f){
                    Player.fallDistance = 0.0f;
                }
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand) {
        user.getItemCooldownManager().set(this, 8);
        double sash_buff = 1.5;
        float yaw = user.getYaw();
        float pitch = user.getPitch();
        float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        float g = -MathHelper.sin((pitch) * 0.017453292F);
        float h = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        Vec3d velocity = user.getVelocity();
        velocity.add(0, -velocity.y, 0);
        user.setVelocity(velocity);
        System.out.println(user.getVelocity().y);
        user.addVelocity(f * sash_buff, (g + 0.5) * sash_buff, h * sash_buff);
        Vec3d pos = user.getPos();
        world.playSound(null, new BlockPos((int) pos.x, (int) pos.y, (int) pos.z), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS);
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
