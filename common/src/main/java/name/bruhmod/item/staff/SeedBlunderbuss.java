package name.bruhmod.item.staff;

import name.bruhmod.entity.SeedEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class SeedBlunderbuss extends StaffItem {
    public SeedBlunderbuss() {
        super(new Item.Properties().rarity(Rarity.RARE));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        var stack = user.getItemInHand(hand);
        if (!tryUse(user, user.getInventory(), stack)) {
            return InteractionResultHolder.fail(stack);
        }

        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ROOTED_DIRT_BREAK, SoundSource.PLAYERS, 1.3f, 0.2f);

        if (!world.isClientSide) {
            for (int count = user.getRandom().nextInt(24, 32); count > 0; --count) {
                SeedEntity seed = new SeedEntity(world, user, stack);
                seed.shootFromRotation(user, user.getXRot(), user.getYRot(), 0.0f, 1.5f, 30.0f);
                world.addFreshEntity(seed);
            }
        }

        return super.use(world, user, hand);
    }

    @Override
    public int essencePerUse(ItemStack item) {
        return 0;
    }

}
