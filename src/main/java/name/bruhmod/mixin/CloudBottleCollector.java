package name.bruhmod.mixin;

import name.bruhmod.items.ModItems;
import name.bruhmod.util.ICloud;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.item.GlassBottleItem;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GlassBottleItem.class)
public abstract class CloudBottleCollector implements ICloud {
    @Inject(method = "use", at = @At("HEAD"))
    protected void onUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable info){
        if(user.getPos().y > 192 && user.getPos().y < 198){
            ItemStack itemStack = user.getStackInHand(hand);
            user.incrementStat(Stats.USED.getOrCreateStat(Items.GLASS_BOTTLE));
            if (!user.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            System.out.println("check1");
            ItemEntity dropItem = new ItemEntity(world, user.getPos().x, user.getPos().y, user.getPos().z, new ItemStack(ModItems.CLOUD_IN_A_BOTTLE));
            world.spawnEntity(dropItem);
        }
    }

    /*
    if(user.getPos().y > 192 && user.getPos().y < 197){
        System.out.println("check");
    }
    */
}
