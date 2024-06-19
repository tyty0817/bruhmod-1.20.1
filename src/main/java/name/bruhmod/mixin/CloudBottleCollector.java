package name.bruhmod.mixin;

import name.bruhmod.items.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.item.GlassBottleItem;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GlassBottleItem.class)
public abstract class CloudBottleCollector {
    @Inject(method = "use", at = @At(value = "RETURN", ordinal = 1))
    protected void onUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable info){
        if(user.getPos().y > 190 && user.getPos().y < 198){
            ItemStack itemStack = user.getStackInHand(hand);
            user.incrementStat(Stats.USED.getOrCreateStat(Items.GLASS_BOTTLE));
            user.setStackInHand(hand, ItemUsage.exchangeStack(itemStack, user, new ItemStack(ModItems.CLOUD_IN_A_BOTTLE)));
            world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        }
    }

    /*
    if(user.getPos().y > 192 && user.getPos().y < 197){
        System.out.println("check");
    }
    */
}
