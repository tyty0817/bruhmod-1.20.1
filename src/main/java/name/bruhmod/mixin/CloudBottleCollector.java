package name.bruhmod.mixin;

import name.bruhmod.items.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BottleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BottleItem.class)
public abstract class CloudBottleCollector {
    @Inject(method = "use", at = @At(value = "RETURN", ordinal = 1))
    protected void onUse(Level world, Player user, InteractionHand hand, CallbackInfoReturnable<Void> info){
        if(user.position().y > 190 && user.position().y < 198){
            ItemStack itemStack = user.getItemInHand(hand);
            user.awardStat(Stats.ITEM_USED.get(Items.GLASS_BOTTLE));
            user.setItemInHand(hand, new ItemStack(ModItems.CLOUD_IN_A_BOTTLE)); // previously used exchange stack, cannot find it
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BOTTLE_FILL_DRAGONBREATH, SoundSource.NEUTRAL, 1.0F, 1.0F);
        }
    }

    /*
    if(user.getPos().y > 192 && user.getPos().y < 197){
        System.out.println("check");
    }
    */
}
