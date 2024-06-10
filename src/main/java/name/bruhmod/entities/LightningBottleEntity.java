package name.bruhmod.entities;

import name.bruhmod.items.custom.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class LightningBottleEntity extends ThrownItemEntity {

    // this is here because constructor lambda is bugging??
    protected static LightningBottleEntity createEntity(EntityType<LightningBottleEntity> type, World world) {
        return new LightningBottleEntity(type, world);
    }

    public LightningBottleEntity(EntityType<? extends LightningBottleEntity> entityType, World world) {
        super(entityType, world);
    }

    public LightningBottleEntity(World world, LivingEntity owner) {
        super(ModEntities.LIGHTNING_BOTTLE, owner, world);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        World world = this.getWorld();
        if (!world.isClient) {
            world.syncWorldEvent(2002, this.getBlockPos(), PotionUtil.getColor(Potions.WATER));
            LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
            lightningEntity.setPosition(hitResult.getPos());
            world.spawnEntity(lightningEntity);
            this.discard();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.LIGHTNING_IN_A_BOTTLE;
    }
}
