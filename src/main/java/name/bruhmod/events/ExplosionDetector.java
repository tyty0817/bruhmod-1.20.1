package name.bruhmod.events;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.ExplosionEvent;
import name.bruhmod.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.List;

import static net.minecraft.world.explosion.Explosion.getExposure;

public class ExplosionDetector implements ExplosionEvent.Pre {
    public static int unstableGemExplosion = 0;

    @Override
    public EventResult explode(World world, Explosion explosion) {
        int empty_gem = 0, explosive_jelly = 0, gunpowder = 0;
        if(explosion.entity.isTouchingWater()){
            return EventResult.pass();
        }
        assert explosion.entity != null;
        Vec3d pos = explosion.entity.getPos();
        double range = explosion.power / 2;
        List<Entity> list = world.getOtherEntities(null,
                new Box(pos.getX() - range, pos.getY() - range, pos.getZ() - range, pos.getX() + range, pos.getY() + range, pos.getZ() + range));
        for (int i = 0; i < list.size(); i++) {
            Entity element = list.get(i);
            if (element.getType() == EntityType.ITEM) {
                ItemEntity item = (ItemEntity) element;
                if(item.getStack().getItem().equals(ModItems.EMPTY_GEM)) {
                    empty_gem = item.getStack().getCount();
                }else if(item.getStack().getItem().equals(ModItems.EXPLOSIVE_JELLY)) {
                    explosive_jelly = item.getStack().getCount();
                }else if(item.getStack().getItem().equals(Items.GUNPOWDER)) {
                    gunpowder = item.getStack().getCount();
                }
            }
        }
        while(empty_gem > 0 && explosive_jelly > 0 && gunpowder > 1) {
            unstableGemExplosion++;
            empty_gem--;
            explosive_jelly--;
            gunpowder -= 2;
        }
        return EventResult.pass();
    }
}
