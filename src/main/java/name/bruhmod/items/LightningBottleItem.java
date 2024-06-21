package name.bruhmod.items;

import name.bruhmod.entities.LightningBottleEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class LightningBottleItem extends Item {

    public LightningBottleItem(Properties settings) {
        super(settings);
    }

    /*
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
     */

    public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        user.getCooldowns().addCooldown(this, 50);
        ItemStack itemStack = user.getItemInHand(hand);


        if (!world.isClientSide()) {
            LightningBottleEntity bottleEntity = new LightningBottleEntity(world, user);
            bottleEntity.setItem(itemStack);
            bottleEntity.shootFromRotation(user, user.getXRot(), user.getYRot(), -20.0F, 0.5F, 1.0F);
            world.addFreshEntity(bottleEntity);
        }

        user.awardStat(Stats.ITEM_USED.get(this));
        if (!user.isCreative()) {
            itemStack.shrink(1);
        }

        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.SPLASH_POTION_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

        return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
    }

}
