package name.bruhmod.entities;

import name.bruhmod.Mod;
import name.bruhmod.entities.FireBolt;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static name.bruhmod.Mod.MOD_ID;

public class ModEntities {

    public static EntityType<LightningBottleEntity> LIGHTNING_BOTTLE = registerEntity(
            "lightning_bottle",
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, LightningBottleEntity::createEntity).dimensions(new EntityDimensions(0.25F, 0.25F, true)).trackRangeChunks(4).trackedUpdateRate(10)
    );

    public static final EntityType<FireBolt> FIRE_BOLT = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MOD_ID, "dice_projectile"),
            FabricEntityTypeBuilder.<FireBolt>create(SpawnGroup.MISC, FireBolt::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());

    public static EntityType<BossEntity> BOSS = registerEntity(
            BossEntity.ID,
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BossEntity::new).dimensions(EntityDimensions.fixed(0.8f, 2.75f))
    );

    public static void register() {
        Mod.LOGGER.info("Registering Entities for " + MOD_ID);
        FabricDefaultAttributeRegistry.register(BOSS, BossEntity.createMobAttributes());
    }

    private static <T extends Entity> EntityType<T> registerEntity(String id, FabricEntityTypeBuilder<T> builder) {
        return Registry.register(
                Registries.ENTITY_TYPE,
                new Identifier(MOD_ID, id),
                builder.build()
        );
    }

}