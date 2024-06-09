package name.bruhmod.events;

import dev.architectury.event.events.common.ExplosionEvent;
import name.bruhmod.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.List;

public class ExplosionCrafter implements ExplosionEvent.Detonate {
    @Override
    public void explode(World world, Explosion explosion, List<Entity> affectedEntities) {
        System.out.println("end");
        while(ExplosionDetector.boomCharms > 0){
            Vec3d pos = explosion.getEntity().getPos();
            world.spawnEntity(new ItemEntity(world, pos.x, pos.y, pos.z, new ItemStack(ModItems.LIGHTNING_IN_A_BOTTLE)));
            ExplosionDetector.boomCharms--;
        }
    }
}
