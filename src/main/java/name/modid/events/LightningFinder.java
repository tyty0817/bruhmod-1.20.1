package name.modid.events;

import name.modid.items.ModItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class LightningFinder implements ServerEntityEvents.Load {
    private static int lightning = 0;

    @Override
    public void onLoad(Entity entity, ServerWorld world) {
        if(entity instanceof LightningEntity){
            Vec3d pos = entity.getPos();
            List<Entity> list = world.getOtherEntities(null,
                    new Box(pos.getX() - 3.0, pos.getY() - 3.0, pos.getZ() - 3.0, pos.getX() + 3.0, pos.getY() + 6.0 + 3.0, pos.getZ() + 3.0));
            for (int i = 0; i < list.size(); i++) {
                Entity element = list.get(i);
                if (element.getType() == EntityType.ITEM) {
                    ItemEntity item = (ItemEntity) element;
                    if (item.getName().getString().equals("Glass Bottle")) {
                        lightning = entity.getId();
                    }
                }
            }
            BlockPos blockPos = entity.getBlockPos();
            for(int i = 0; i < 4; ++i) {
                BlockPos blockPos2 = blockPos.add((int) (Math.random() * 3) - 1, (int) (Math.random() * 3) - 1, (int) (Math.random() * 3) - 1);
                if(world.getBlockState(blockPos2).getBlock() == Blocks.SAND){
                    world.setBlockState(blockPos2, Blocks.GLASS.getDefaultState());
                }
            }
        }
    }
    public static int getID(){
        return lightning;
    }
}
