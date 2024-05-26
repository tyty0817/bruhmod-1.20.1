package com.github.donotdoughnut.bruhmod.entities;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import static com.github.donotdoughnut.bruhmod.Mod.MOD_ID;

public class TestEntity extends HostileEntity {

    public static EntityType<TestEntity> TYPE;

    protected TestEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static void register() {
        TYPE = Registry.register(
                Registries.ENTITY_TYPE,
                new Identifier(MOD_ID, "test_entity"),
                FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, TestEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
        );
        FabricDefaultAttributeRegistry.register(TYPE, TestEntity.createMobAttributes());
    }
}
