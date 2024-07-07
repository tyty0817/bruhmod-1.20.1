package name.bruhmod.mixin;

import name.bruhmod.items.ModItems;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(AnvilBlock.class)
public abstract class AnvilBlockMixin {
    @Inject(method = "onUse", at = @At(value = "HEAD"), cancellable = true)
    protected void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> ci){
        if(player.getStackInHand(hand).getItem() instanceof ShovelItem shovel && hit.getSide() == Direction.UP){
            List<Entity> list = world.getOtherEntities(null, new Box(pos.add(0, 1, 0)));
            ItemStack iron = null, stick = null;
            for (Entity element : list) {
                if(element instanceof ItemEntity item) {
                    if (item.getStack().getItem().equals(Items.IRON_INGOT)) {
                        iron = item.getStack();
                    }
                    if (item.getStack().getItem().equals(Items.STICK)) {
                        stick = item.getStack();
                    }
                }
            }
            if(iron != null && stick != null){
                while(iron.getCount() > 1 && stick.getCount() > 0) {
                    iron.decrement(2);
                    stick.decrement(1);
                    player.getStackInHand(hand).damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
                    world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(Items.IRON_SWORD)));
                }
                ci.setReturnValue(ActionResult.SUCCESS);
            }
        }
    }
}
