package name.bruhmod.entities;

import name.bruhmod.LeMod;
import name.bruhmod.util.RegistryHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

public class ModEntities {

    public static final RegistryHelper<EntityType<?>> REGISTERER = new RegistryHelper<>(BuiltInRegistries.ENTITY_TYPE);

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

    public interface EntityAttributeConsumer {
        void register(EntityType<? extends LivingEntity> type, AttributeSupplier.Builder builder);
    }

    public static void registerAttributes(EntityAttributeConsumer registry) {
        registry.register(BOSS, BossEntity.createMobAttributes());
    }

    private static <T extends Entity> EntityType<T> registerEntity(String name, EntityType.Builder<T> typeBuilder) {
        var id = LeMod.idOf(name);
        var type = typeBuilder.build(id.toString());
        return REGISTERER.add(id, type);
    }

}