package name.bruhmod.entities;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class FireBolt extends Projectile {
    double powerX, powerY, powerZ;
    int life;
    MobEffectInstance[] effectList = {new MobEffectInstance(MobEffects.WITHER, 200),
    new MobEffectInstance(MobEffects.POISON, 200, 1),
    new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1),
    new MobEffectInstance(MobEffects.WEAKNESS, 200, 1),
    new MobEffectInstance(MobEffects.BLINDNESS, 200)};

    public FireBolt(EntityType<FireBolt> fireBoltEntityType, Level world) {
        super(fireBoltEntityType, world);
    }

    public FireBolt(Level world, Player user, double x, double y, double z) {
        super(EntityType.FIREBALL, world);
        powerX = x;
        powerY = y;
        powerZ = z;
        this.noCulling = true;
        this.setOwner(user);
    }

    protected static FireBolt createEntity(EntityType<FireBolt> type, Level world) {
        return new FireBolt(type, world);
    }

//    protected void onHit(HitResult hitResult) {
//        super.onHit(hitResult);
//    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        if (!this.level().isClientSide()) {
            if (entityHitResult.getEntity() instanceof LivingEntity target) {
                int effect = (int) (Math.random() * 5);
                target.addEffect(effectList[effect], this.getOwner());
            }
            this.discard();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    public void tick() {
        Entity entity = this.getOwner();
        Level world = this.level();
        if (world.isClientSide() || (entity == null || !entity.isRemoved()) && world.isLoaded(this.blockPosition())) {
            HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
            if (hitResult.getType() != HitResult.Type.MISS) {
                this.onHit(hitResult);
            }

            this.checkInsideBlocks();
            Vec3 vec3d = this.getDeltaMovement();
            double d = this.getX() + vec3d.x;
            double e = this.getY() + vec3d.y;
            double f = this.getZ() + vec3d.z;
            ProjectileUtil.rotateTowardsMovement(this, 0.2F);

            this.setPos(vec3d.add(this.powerX, this.powerY, this.powerZ).scale(0.95F));
            world.addParticle(ParticleTypes.FLAME, d, e + 0.5, f, 0.0, 0.0, 0.0);
            this.setPos(d, e, f);
        } else {
            this.discard();
        }
        if(life < 100) {
            life++;
        }else{
            this.discard();
        }
    }
}