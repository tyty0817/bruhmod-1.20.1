package name.bruhmod.item;

import name.bruhmod.LeMod;
import name.bruhmod.entity.BoomerangEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;

import java.util.HashMap;

public class BoomerangItem extends TieredItem implements ProjectileItem {

//    public static final HashMap<Tier, BoomerangItem> BOOMERANGS = new HashMap<>();

    public static final TagKey<Item> TAG = TagKey.create(Registries.ITEM, LeMod.idOf("boomerang"));

    public BoomerangItem(Tier tier, TieredItem.Properties properties) {
        super(tier, properties);
//        BOOMERANGS.put(tier, this);
    }

    public static ItemAttributeModifiers createAttributes(Tier tier, float projectileDamage, float projectileSpeed) {
        return ItemAttributeModifiers.builder()
//                .add(
//                        Attributes.ATTACK_DAMAGE,
//                        new AttributeModifier(
//                                BASE_ATTACK_DAMAGE_ID, (double)((float)attackDamage + tier.getAttackDamageBonus()), AttributeModifier.Operation.ADD_VALUE
//                        ),
//                        EquipmentSlotGroup.MAINHAND
//                )
//                .add(
//                        Attributes.ATTACK_SPEED,
//                        new AttributeModifier(BASE_ATTACK_SPEED_ID, (double)attackSpeed, AttributeModifier.Operation.ADD_VALUE),
//                        EquipmentSlotGroup.MAINHAND
//                )
                .build();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        ItemStack itemstack = player.getItemInHand(hand);
        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.SNOWBALL_THROW,
                SoundSource.NEUTRAL,
                0.5F,
                0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );

        if (!level.isClientSide) {
            BoomerangEntity boomerang = new BoomerangEntity(level, player);
            boomerang.setItem(itemstack);
            boomerang.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1F, 1.0F);
            level.addFreshEntity(boomerang);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        itemstack.consume(1, player);
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        BoomerangEntity boomerang = new BoomerangEntity(level, pos.x(), pos.y(), pos.z());
        boomerang.setItem(stack);
        return boomerang;
    }

    public float getDamage() {
        return 3.0f + this.getTier().getAttackDamageBonus();
    }

//    public


}
