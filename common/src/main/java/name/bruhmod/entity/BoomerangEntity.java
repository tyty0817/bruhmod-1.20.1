package name.bruhmod.entity;

import name.bruhmod.item.BoomerangItem;
import name.bruhmod.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class BoomerangEntity extends ThrowableItemProjectile {

    private final Vec3 origin;
    private static final int MAX_BOUNCES = 2;
    boolean free = true;

    private int age;
    private int bounces;

    // figure out how to pick out enchants such as loyalty, sharpness, etc and apply them to this weapon

    public BoomerangEntity(EntityType<BoomerangEntity> entityType, Level level) {
        super(entityType, level);
        this.origin = Vec3.ZERO;
    }

    public BoomerangEntity(Level level, double x, double y, double z) {
        super(ModEntities.BOOMERANG, x, y, z, level);
        this.origin = new Vec3(x, y, z);
    }

    public BoomerangEntity(Level level, Player player) {
        super(ModEntities.BOOMERANG, player, level);
        this.setRot(player.getXRot(), player.getYRot());
        this.origin = new Vec3(player.getX(), player.getY(), player.getZ());
    }

    @Override
    public void tick() {

        this.age++;

        if (!this.tryReturn() && !free) {
            if (!this.getInBlockState().blocksMotion()) {
                free = true;
            }
        }
        super.tick();
    }

    private boolean tryReturn() {
        if (this.noPhysics || this.age >= 15 || this.bounces >= MAX_BOUNCES) {
            if (this.noPhysics && (this.getOwner() == null || this.getOwner().distanceTo(this) < 0.1f)) {
                this.drop();
            } else if (!this.noPhysics) {
                this.noPhysics = true;
                this.tryReturn();
            } else {
                this.returnToOwner();
            }
            return true;
        } else return false;
    }

    /**
     * Called when the arrow hits an entity
     */
    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        if (entity == this.getOwner()) {
            this.discard();
        } else {
            entity.hurt(this.damageSources().thrown(this, this.getOwner()), ((BoomerangItem) (this.getItem().getItem())).getDamage());
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (free) {
            this.playSound(SoundEvents.WOOD_BREAK, 10.0F, 1.5F);
            this.setDeltaMovement(this.getDeltaMovement().multiply(switch (result.getDirection()) {
                case DOWN, UP -> new Vec3(1, -1, 1);
                case NORTH, SOUTH -> new Vec3(1, 1, -1);
                case EAST, WEST -> new Vec3(-1, 1, 1);
            }));
            this.bounces++;
            free = false;
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.WOODEN_BOOMERANG;
    }

    private void returnToOwner() {
        double speed = this.getDeltaMovement().length() * 1.05;
        this.setDeltaMovement(this.getOwner().getEyePosition().subtract(this.position()).normalize().scale(speed));
    }

    private void drop() {
        if (!(this.getOwner() instanceof Player player && player.isCreative()))
            this.spawnAtLocation(this.getItem(), 0.1F);
        this.discard();
    }

    @Override
    protected double getDefaultGravity() {
        return 0.0;
    }

    public final int getAge() {
        return age;
    }
}
