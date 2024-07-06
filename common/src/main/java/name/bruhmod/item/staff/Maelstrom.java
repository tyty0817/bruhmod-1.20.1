package name.bruhmod.item.staff;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class Maelstrom extends StaffItem {

    public Maelstrom() {
        super(new Properties().rarity(Rarity.RARE));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use (Level world, Player user, InteractionHand hand) {
        var stack = user.getItemInHand(hand);
        if (!tryUse(user, user.getInventory(), stack)) {
            return InteractionResultHolder.fail(stack);
        }
        super.use(world, user, hand);
        HitResult hit = user.pick(128, 1, true);
        if(hit.getType() != HitResult.Type.MISS) {
            LightningBolt lightningEntity = new LightningBolt(EntityType.LIGHTNING_BOLT, world);
            lightningEntity.setPos(hit.getLocation());
            world.addFreshEntity(lightningEntity);
        }
//        user.getItemInHand(hand).damage(1, user);
        return InteractionResultHolder.pass(user.getItemInHand(hand));
    }

    @Override
    public int essencePerUse(ItemStack item) {
        return 2;
    }
}
