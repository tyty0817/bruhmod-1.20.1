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

public class LightningTracker implements ServerEntityEvents.Unload {

    @Override
    public void onUnload(Entity entity, ServerWorld world) {
        if(entity instanceof LightningEntity && LightningFinder.isStructk()){
            Vec3d pos = entity.getPos();
            world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, new ItemStack(ModItems.BOTTLE_OF_LIGHTNING)));
            LightningFinder.resetStructk();
        }
    }
}