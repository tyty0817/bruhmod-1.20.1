package name.bruhmod.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FireBolt extends ProjectileEntity {
    double powerX, powerY, powerZ;
    int life;
    StatusEffectInstance[] effectList = {new StatusEffectInstance(StatusEffects.WITHER, 200),
    new StatusEffectInstance(StatusEffects.POISON, 200, 1),
    new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 1),
    new StatusEffectInstance(StatusEffects.WEAKNESS, 200, 1),
    new StatusEffectInstance(StatusEffects.BLINDNESS, 200)};

    public FireBolt(EntityType<FireBolt> fireBoltEntityType, World world) {
        super(fireBoltEntityType, world);
    }

    public FireBolt(World world, PlayerEntity user, double x, double y, double z) {
        super(EntityType.FIREBALL, world);
        powerX = x;
        powerY = y;
        powerZ = z;
        this.noClip = true;
        this.setOwner(user);
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (!this.getWorld().isClient) {
            if (entityHitResult.getEntity() instanceof LivingEntity target) {
                int effect = (int) (Math.random() * 5);
                target.addStatusEffect(effectList[effect], this.getOwner());
            }
            this.discard();
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    public void tick() {
        Entity entity = this.getOwner();
        if (this.getWorld().isClient || (entity == null || !entity.isRemoved()) && this.getWorld().isChunkLoaded(this.getBlockPos())) {
            HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
            if (hitResult.getType() != HitResult.Type.MISS) {
                this.onCollision(hitResult);
            }

            this.checkBlockCollision();
            Vec3d vec3d = this.getVelocity();
            double d = this.getX() + vec3d.x;
            double e = this.getY() + vec3d.y;
            double f = this.getZ() + vec3d.z;
            ProjectileUtil.setRotationFromVelocity(this, 0.2F);

            this.setVelocity(vec3d.add(this.powerX, this.powerY, this.powerZ).multiply(0.95F));
            this.getWorld().addParticle(ParticleTypes.FLAME, d, e + 0.5, f, 0.0, 0.0, 0.0);
            this.setPosition(d, e, f);
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