package name.bruhmod.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

public class ModAttributes {

    public interface EntityAttributeConsumer {
        void register(EntityType<? extends LivingEntity> type, AttributeSupplier.Builder builder);
    }

    public static void registerAttributes(EntityAttributeConsumer registry) {
        registry.register(ModEntities.BOSS, BossEntity.createMobAttributes());
    }

}
