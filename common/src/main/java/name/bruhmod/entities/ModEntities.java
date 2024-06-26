package name.bruhmod.entities;

import name.bruhmod.Mod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import static name.bruhmod.Mod.LOGGER;
import static name.bruhmod.Mod.MOD_ID;

public class ModEntities {

    public interface EntityAttributeConsumer {
        void register(EntityType<? extends LivingEntity> type, AttributeSupplier.Builder builder);
    }

    public static void register(EntityAttributeConsumer registry) {
        LOGGER.info("Registering Entities for " + MOD_ID);
        registry.register(BOSS, BossEntity.createMobAttributes());
    }

    public static EntityType<LightningBottleEntity> LIGHTNING_BOTTLE = registerEntity(
            "lightning_bottle",
            EntityType.Builder.of(LightningBottleEntity::createEntity, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10)
    );

    public static final EntityType<FireBolt> FIRE_BOLT = registerEntity("dice_projectile",
            EntityType.Builder.of(FireBolt::createEntity, MobCategory.MISC)
                    .sized(0.25f, 0.25f));

    public static EntityType<BossEntity> BOSS = registerEntity(
            BossEntity.ID,
            EntityType.Builder.of(BossEntity::new, MobCategory.CREATURE).sized(0.8f, 2.75f)
    );

    private static <T extends Entity> EntityType<T> registerEntity(String name, EntityType.Builder<T> type) {
        var id = Mod.idOf(name);
        return Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                id,
                type.build(id.toString())
        );
    }

}