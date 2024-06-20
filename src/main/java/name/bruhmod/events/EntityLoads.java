package name.bruhmod.events;

import name.bruhmod.items.ModItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

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