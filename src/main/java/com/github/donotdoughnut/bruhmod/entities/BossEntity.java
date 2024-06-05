package com.github.donotdoughnut.bruhmod.entities;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import static com.github.donotdoughnut.bruhmod.Mod.MOD_ID;

public class BossEntity extends HostileEntity implements GeoEntity {

    public static final String ID = "boss";

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public static final EntityType<BossEntity> TYPE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(MOD_ID, ID),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BossEntity::new).dimensions(EntityDimensions.fixed(0.8f, 2.75f)).build()
    );

    protected BossEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static void register() {
        FabricDefaultAttributeRegistry.register(TYPE, BossEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 80.0D)
                .add(EntityAttributes.GENERIC_ARMOR, 20.0f)
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 10.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 5.0f)
        );
    }

    @Override
    public void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.add(3, new WanderAroundGoal(this, 0.6f));
        this.goalSelector.add(4, new LookAroundGoal(this));

        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, HostileEntity.class, true));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<BossEntity> state) {
//        state.getController().setAnimation(RawAnimation.begin().then("animation.boss.idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
