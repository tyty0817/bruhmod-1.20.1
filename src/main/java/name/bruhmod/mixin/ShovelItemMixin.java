package name.bruhmod.mixin;

import name.bruhmod.items.ModItems;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Blocking;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ShovelItem.class)
public abstract class ShovelItemMixin {
    @Inject(method = "useOnBlock", at = @At(value = "HEAD"))
    protected void useOnBlock(ItemUsageContext context, CallbackInfoReturnable info){
        ItemStack emptyGemPresent = null;
        ItemStack cryingObsidianPresent = null;
        BlockPos pos = context.getBlockPos();
        World world = context.getWorld();
        BlockState blockState = world.getBlockState(pos);
        if(blockState.getBlock() instanceof LeveledCauldronBlock && context.getSide() == Direction.UP){
            List<Entity> list = world.getOtherEntities(null, new Box(pos));
            Vec3d spawnPos = null;
            Block fire = world.getBlockState(pos.down()).getBlock();
            if(fire instanceof CampfireBlock || fire instanceof AbstractFireBlock) {
                for (Entity element : list) {
                    if (element.getType() == EntityType.ITEM) {
                        ItemEntity item = (ItemEntity) element;
                        if (item.getStack().getItem().equals(ModItems.EMPTY_GEM)) {
                            emptyGemPresent = item.getStack();
                            spawnPos = item.getPos();
                        }
                        if (item.getStack().getItem().equals(Items.CRYING_OBSIDIAN)) {
                            cryingObsidianPresent = item.getStack();
                        }
                    }
                }
                if (cryingObsidianPresent != null && emptyGemPresent != null) {
                    cryingObsidianPresent.decrement(1);
                    emptyGemPresent.decrement(1);
                    world.spawnEntity(new ItemEntity(world, spawnPos.x, spawnPos.y, spawnPos.z, new ItemStack(ModItems.JEWEL_OF_CORRUPTION)));
                    LeveledCauldronBlock.decrementFluidLevel(world.getBlockState(pos), world, pos);
                }
            }else{

            }
        }
    }
}