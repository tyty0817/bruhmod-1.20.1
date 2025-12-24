package name.bruhmod.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

public class SeedEntity extends ThrowableProjectile {

    private int life;
    @Nullable
    private ItemStack firedFromWeapon = null;

//    private static f

    public SeedEntity(EntityType<SeedEntity> seedEntityEntityType, Level level) {
        super(seedEntityEntityType, level);
    }

    public SeedEntity(Level world, LivingEntity owner, @Nullable ItemStack firedFromWeapon) {
        super(ModEntities.SEED, owner, world);
        if (firedFromWeapon != null && world instanceof ServerLevel level) {
            if (firedFromWeapon.isEmpty()) {
                throw new IllegalArgumentException("Invalid weapon firing a seed!");
            }

            this.firedFromWeapon = firedFromWeapon.copy();

            // todo enchants
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        // TODO fire seeds by tag and use them to place blocks
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        DamageSource source = new DamageSource(this.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ModDamageTypes.PROJECTILE_SPRAY), this, this.getOwner());
        entity.hurt(source, 1.0f);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        Level level = this.level();
        BlockState block = level.getBlockState(result.getBlockPos());
        BlockPos up = result.getBlockPos().above();
        if (block.is(Blocks.FARMLAND) && level.isEmptyBlock(up)) {
            level.setBlockAndUpdate(up, Blocks.WHEAT.defaultBlockState());
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, Items.WHEAT_SEEDS.getDefaultInstance()), this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }
}
