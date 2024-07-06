package name.bruhmod.mixin;

import name.bruhmod.recipe.natural.NaturalRecipe;
import name.bruhmod.recipe.natural.NaturalSources;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.world.level.block.LayeredCauldronBlock.LEVEL;

@Mixin(ShovelItem.class)
public abstract class ShovelItemMixin {
    @Inject(method = "useOn", at = @At(value = "HEAD"))
    protected void useOnBlock(UseOnContext context, CallbackInfoReturnable<InteractionResult> info){
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        NaturalRecipe.isCraftingCauldron(world, pos).ifPresent(cauldronId -> {
            if(context.getClickedFace() == Direction.UP) {
                int craftingCount = NaturalRecipe.craftAtPosition(world, new AABB(pos), NaturalSources.ofCauldron());
                var block = world.getBlockState(pos);
                int level = block.getValue(LEVEL);
                int drop = Math.max(level - craftingCount, 0);
                if (block.getBlock() instanceof LayeredCauldronBlock) {
                    BlockState newState = drop == 0 ? Blocks.CAULDRON.defaultBlockState() : block.setValue(LEVEL, drop);
                    world.setBlockAndUpdate(pos, newState);
                    world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(newState));
                }
            }
        });
    }
}
