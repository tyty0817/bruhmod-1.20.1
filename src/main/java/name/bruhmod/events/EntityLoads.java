package name.bruhmod.events;

import name.bruhmod.items.custom.ModItems;
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
    //Lightning type variables determined by entities on floor
    private static int lightningInABottleLightning = 0;
    private static int windGemLightning = 0;

    @Override
    public void onLoad(Entity entity, ServerWorld world) {
        if(entity instanceof LightningEntity){
            Vec3d pos = entity.getPos();
            List<Entity> list = world.getOtherEntities(null,
                    new Box(pos.getX() - 3.0, pos.getY() - 3.0, pos.getZ() - 3.0, pos.getX() + 3.0, pos.getY() + 6.0 + 3.0, pos.getZ() + 3.0));

            boolean amethystPresent = false;
            boolean cloudBottlePresent = false;

            for (int i = 0; i < list.size(); i++) {
                Entity element = list.get(i);
                if (element.getType() == EntityType.ITEM) {
                    ItemEntity item = (ItemEntity) element;
                    if(item.getStack().getItem().equals(Items.GLASS_BOTTLE)) {
                        lightningInABottleLightning = entity.getId();
                    }
                    if(item.getStack().getItem().equals(Items.AMETHYST_SHARD)) {
                        amethystPresent = true;
                    }
                    if(item.getStack().getItem().equals(ModItems.CLOUD_IN_A_BOTTLE)){
                        cloudBottlePresent = true;
                        }
                    }
                }
            if(amethystPresent && cloudBottlePresent){
                windGemLightning = entity.getId();
            }


            BlockPos blockPos = entity.getBlockPos();
            for(int i = 0; i < 4; ++i) {
                BlockPos blockPos2 = blockPos.add((int) (Math.random() * 3) - 1, (int) (Math.random() * 3) - 1, (int) (Math.random() * 3) - 1);
                if(world.getBlockState(blockPos2).getBlock() == Blocks.SAND){
                    world.setBlockState(blockPos2, Blocks.GLASS.getDefaultState());
                }
            }
        }else if(false){

        }
    }
    public static int getlightningInABottleLightningID(){
        return lightningInABottleLightning;
    }
    public static int getWindGemLightningID(){
        return windGemLightning;
    }
}