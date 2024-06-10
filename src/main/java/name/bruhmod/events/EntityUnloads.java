package name.bruhmod.events;

import name.bruhmod.items.custom.ModItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class EntityUnloads implements ServerEntityEvents.Unload {

    @Override
    public void onUnload(Entity entity, ServerWorld world) {
        if(entity instanceof LightningEntity && EntityLoads.getlightningInABottleLightningID() == entity.getId()){
            Vec3d pos = entity.getPos();
            world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, new ItemStack(ModItems.LIGHTNING_IN_A_BOTTLE)));
        }
        if(entity instanceof LightningEntity && EntityLoads.getWindGemLightningID() == entity.getId()){
            Vec3d pos = entity.getPos();
            world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, new ItemStack(ModItems.WIND_GEM)));
        }
    }
}