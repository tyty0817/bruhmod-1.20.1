package name.bruhmod.events;

import name.bruhmod.items.ModItems;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class EntityDeathDetector implements ServerLivingEntityEvents.AfterDeath{
    @Override
    public void afterDeath(LivingEntity entity, DamageSource damageSource) {
        if(entity instanceof CowEntity){
            World world = entity.getWorld();
            Vec3d pos = entity.getPos();
            List<Entity> list = world.getOtherEntities(null,
                    new Box(pos.getX() - 3.0, pos.getY() - 1, pos.getZ() - 3.0, pos.getX() + 3.0, pos.getY() + 1.0, pos.getZ() + 3.0));
            for (int i = 0; i < list.size(); i++) {
                Entity element = list.get(i);
                if (element.getType() == EntityType.ITEM) {
                    ItemEntity item = (ItemEntity) element;
                    if(item.getStack().getItem().equals(Items.GLASS_BOTTLE)) {
                        pos = item.getPos();
                        item.discard();
                        world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, new ItemStack(ModItems.LIGHTNING_IN_A_BOTTLE)));
                    }
                }
            }
        }
    }
}