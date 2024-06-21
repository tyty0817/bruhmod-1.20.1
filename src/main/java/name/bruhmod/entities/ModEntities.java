package name.bruhmod.entities;

import name.bruhmod.Mod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static name.bruhmod.Mod.MOD_ID;

public class ModEntities {

    public static EntityType<LightningBottleEntity> LIGHTNING_BOTTLE = registerEntity(
            "lightning_bottle",
            EntityType.Builder.create(LightningBottleEntity::createEntity, SpawnGroup.MISC).dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10).build()
    );

    public static final EntityType<FireBolt> FIRE_BOLT = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(MOD_ID, "dice_projectile"),
            EntityType.Builder.<FireBolt>create(FireBolt::new, SpawnGroup.MISC)
                    .dimensions(0.25f, 0.25f).build());

    public static EntityType<BossEntity> BOSS = registerEntity(
            BossEntity.ID,
            EntityType.Builder.create(BossEntity::new, SpawnGroup.CREATURE).dimensions(0.8f, 2.75f).build()
    );

    public static void register() {
        Mod.LOGGER.info("Registering Entities for " + MOD_ID);
        FabricDefaultAttributeRegistry.register(BOSS, BossEntity.createMobAttributes());
    }

    private static <T extends Entity> EntityType<T> registerEntity(String id, EntityType<T> type) {
        return Registry.register(
                Registries.ENTITY_TYPE,
                Identifier.of(MOD_ID, id),
                type
        );
    }

}