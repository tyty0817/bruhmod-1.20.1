package name.bruhmod.entities;

import name.bruhmod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class LightningBottleEntity extends ThrowableItemProjectile {

    // this is here because constructor lambda is bugging??
    protected static LightningBottleEntity createEntity(EntityType<LightningBottleEntity> type, Level world) {
        return new LightningBottleEntity(type, world);
    }

    public LightningBottleEntity(EntityType<? extends LightningBottleEntity> entityType, Level world) {
        super(entityType, world);
    }

    public LightningBottleEntity(Level world, LivingEntity owner) {
        super(ModEntities.LIGHTNING_BOTTLE, owner, world);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        Level world = this.level();
        if (!world.isClientSide()) {
            LightningBolt lightningEntity = new LightningBolt(EntityType.LIGHTNING_BOLT, world);
            lightningEntity.setPos(hitResult.getLocation());
            world.addFreshEntity(lightningEntity);
            this.discard();
        }
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return ModItems.LIGHTNING_IN_A_BOTTLE;
    }
}
