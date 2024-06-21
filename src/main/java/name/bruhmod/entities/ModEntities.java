package name.bruhmod.entities;

import name.bruhmod.Mod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import static name.bruhmod.Mod.MOD_ID;

public class ModEntities {

    public static EntityType<LightningBottleEntity> LIGHTNING_BOTTLE = registerEntity(
            "lightning_bottle",
            EntityType.Builder.of(LightningBottleEntity::createEntity, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build()
    );

    public static final EntityType<FireBolt> FIRE_BOLT = registerEntity("dice_projectile",
            EntityType.Builder.of(FireBolt::createEntity, MobCategory.MISC)
                    .sized(0.25f, 0.25f).build());

    public static EntityType<BossEntity> BOSS = registerEntity(
            BossEntity.ID,
            EntityType.Builder.of(BossEntity::new, MobCategory.CREATURE).sized(0.8f, 2.75f).build()
    );

    public static void register() {
        Mod.LOGGER.info("Registering Entities for " + MOD_ID);
        FabricDefaultAttributeRegistry.register(BOSS, BossEntity.createMobAttributes());
    }

    private static <T extends Entity> EntityType<T> registerEntity(String id, EntityType<T> type) {
        return Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                Mod.idOf(id),
                type
        );
    }

}