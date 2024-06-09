package name.bruhmod.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BottleOfLightningEntity extends ThrownItemEntity {
    World worlds;

    public BottleOfLightningEntity(World world, PlayerEntity user) {
        super(EntityType.SNOWBALL, user, world);
        worlds = world;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        if (!this.getWorld().isClient) {
            Vec3d pos = hitResult.getPos();
            Entity lighting_bolt = new LightningEntity(EntityType.LIGHTNING_BOLT, worlds);
            lighting_bolt.setPos(pos.x, pos.y, pos.z);
            worlds.spawnEntity(lighting_bolt);
            worlds.playSound(null, new BlockPos((int) pos.x, (int) pos.y, (int) pos.z), SoundEvents.ENTITY_SPLASH_POTION_BREAK, SoundCategory.BLOCKS);
            this.discard();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return Items.SPLASH_POTION;
    }
}
