package name.bruhmod.entity;

import name.bruhmod.LeMod;
import name.bruhmod.util.RegistryHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {

    public static final RegistryHelper<EntityType<?>> ENTITY_TYPES = new RegistryHelper<>();

    public static EntityType<LightningBottleEntity> LIGHTNING_BOTTLE = registerEntity(
            "lightning_bottle",
            EntityType.Builder.<LightningBottleEntity>of(LightningBottleEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10)
    );

    public static final EntityType<FireBolt> FIRE_BOLT = registerEntity("dice_projectile",
            EntityType.Builder.<FireBolt>of(FireBolt::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f));

    public static final EntityType<SeedEntity> SEED = registerEntity("seed", EntityType.Builder.<SeedEntity>of(SeedEntity::new, MobCategory.MISC).sized(0.1f, 0.1f));

    public static final EntityType<BoomerangEntity> BOOMERANG = registerEntity("boomerang", EntityType.Builder.<BoomerangEntity>of(BoomerangEntity::new, MobCategory.MISC).sized(0.4f, 0.2f));

    public static EntityType<BossEntity> BOSS = registerEntity(
            BossEntity.ID,
            EntityType.Builder.of(BossEntity::new, MobCategory.CREATURE).sized(0.8f, 2.75f)
    );

    public static final EntityType<StaffRecipeTicker> STAFF_RECIPE = registerEntity("staff_recipe", EntityType.Builder.of(StaffRecipeTicker::new, MobCategory.MISC));

    private static <T extends Entity> EntityType<T> registerEntity(String name, EntityType.Builder<T> typeBuilder) {
        var id = LeMod.idOf(name);
        var type = typeBuilder.build(id.toString());
        return ENTITY_TYPES.add(id, type);
    }

}