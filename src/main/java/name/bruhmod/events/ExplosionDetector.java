package name.bruhmod.events;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.ExplosionEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.List;

public class ExplosionDetector implements ExplosionEvent.Pre {
    public static int unstableGemExplosion = 0;

    @Override
    public EventResult explode(World world, Explosion explosion) {
        assert explosion.entity != null;
        Vec3d pos = explosion.entity.getPos();
        double range = explosion.power / 2;
        List<Entity> list = world.getOtherEntities(null,
                new Box(pos.getX() - range, pos.getY() - range, pos.getZ() - range, pos.getX() + range, pos.getY() + range, pos.getZ() + range));
        for (int i = 0; i < list.size(); i++) {
            Entity element = list.get(i);
            if (element.getType() == EntityType.ITEM) {
                ItemEntity item = (ItemEntity) element;
                if(item.getStack().getItem().equals(Items.AMETHYST_SHARD)) {
                    unstableGemExplosion = item.getStack().getCount();
                }
            }
        }
        return EventResult.pass();
    }
}
