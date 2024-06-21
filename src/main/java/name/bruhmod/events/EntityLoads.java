package name.bruhmod.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class EntityLoads implements ServerEntityEvents.Load {

    @Override
    public void onLoad(Entity entity, ServerWorld world) {
        if(entity instanceof LightningEntity){
            BlockPos blockPos = entity.getBlockPos();
            for(int i = 0; i < 4; ++i) {
                BlockPos blockPos2 = blockPos.add((int) (Math.random() * 3) - 1, (int) (Math.random() * 3) - 1, (int) (Math.random() * 3) - 1);
                if(world.getBlockState(blockPos2).getBlock() == Blocks.SAND){
                    world.setBlockState(blockPos2, Blocks.GLASS.getDefaultState());
                }
            }
        }
    }
}