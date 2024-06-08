package name.modid.events;

import name.modid.items.ModItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class LightningFinder implements ServerEntityEvents.Load {
    private static Entity lightning;

    @Override
    public void onLoad(Entity entity, ServerWorld world) {
        if(entity instanceof LightningEntity){
            Vec3d pos = entity.getPos();
            List<Entity> list = world.getOtherEntities(null,
                    new Box(pos.getX() - 3.0, pos.getY() - 3.0, pos.getZ() - 3.0, pos.getX() + 3.0, pos.getY() + 6.0 + 3.0, pos.getZ() + 3.0));
            for (int i = 0; i < list.size(); i++) {
                Entity element = list.get(i);
                if(element.getType() == EntityType.ITEM) {
                    ItemEntity item = (ItemEntity) element;
                    if(item.getName().getString().equals("Glass Bottle")){
                        lightning = entity;
                    }
                }
            }
        }
    }
    public static Entity getBolt(){
        return lightning;
    }
}
