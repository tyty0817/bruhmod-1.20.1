package name.bruhmod.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.block.Blocks;

public class EntityLoads implements ServerEntityEvents.Load {

    @Override
    public void onLoad(Entity entity, ServerLevel world) {
        if(entity instanceof LightningBolt){
            BlockPos blockPos = entity.blockPosition();
            for(int i = 0; i < 4; ++i) {
                BlockPos blockPos2 = blockPos.offset((int) (Math.random() * 3) - 1, (int) (Math.random() * 3) - 1, (int) (Math.random() * 3) - 1);
                if(world.getBlockState(blockPos2).getBlock() == Blocks.SAND){
                    world.setBlockAndUpdate(blockPos2, Blocks.GLASS.defaultBlockState());
                }
            }
        }
    }
}