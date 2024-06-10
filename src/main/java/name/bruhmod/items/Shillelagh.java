package name.bruhmod.items;

import name.bruhmod.Mod;
import name.bruhmod.items.custom.ModItems;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.GeckoLib;

import java.util.List;


public class Shillelagh extends Item {

    public Shillelagh(Settings settings) {
        super(settings);
    }


    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item." + Mod.MOD_ID + ".shillelagh.tooltip"));

        super.appendTooltip(stack, null, tooltip, context);
    }



    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(entity instanceof PlayerEntity) {
            PlayerEntity Player = (PlayerEntity) entity;
            if(Player.getStackInHand(Hand.MAIN_HAND).equals(ModItems.SHILLELAGH)){
                if(Player.fallDistance > 3.0f){
                    Player.fallDistance = 0.0f;
                }
            }

            else if(Player.getStackInHand(Hand.OFF_HAND).equals(ModItems.SHILLELAGH)){
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
        user.setVelocity(f * sash_buff, (g + 0.5) * sash_buff, h * sash_buff);
        Vec3d pos = user.getPos();
        world.playSound(null, new BlockPos((int) pos.x, (int) pos.y, (int) pos.z), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS);
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
