package name.bruhmod.entities;

import name.bruhmod.LeMod;
import name.bruhmod.util.RegistryHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class ModAttributes {

    public static final String ESSENCE_ID = "player.essence";

    /**
     * Insanely stupid fix, the game needs direct access to this attribute during bootstrap (in order to add the attribute to the player),
     * but it then later needs a reference access to this in order to get the key when saving the world when a player has this attribute.
     * So we initialize it in a hacky way
     */
    public static final Holder<Attribute> ESSENCE = Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, LeMod.idOf(ESSENCE_ID), new RangedAttribute("attribute.name." +LeMod.MOD_ID + "." + ESSENCE_ID, 0.0, 0.0, 512.0).setSyncable(true));

    public static void register(RegistryHelper.Provider registerer) {
//        ESSENCE = registerer.register(BuiltInRegistries.ATTRIBUTE, LeMod.idOf(ESSENCE_ID), ESSENCE.value());
    }

    public interface EntityAttributeConsumer {
        void register(EntityType<? extends LivingEntity> type, AttributeSupplier.Builder builder);
//        void register(EntityType<? extends LivingEntity> type, AttributeSupplier container);
    }

    public static void registerAttributes(EntityAttributeConsumer registry) {
        registry.register(ModEntities.BOSS, BossEntity.createMobAttributes());
    }

}
