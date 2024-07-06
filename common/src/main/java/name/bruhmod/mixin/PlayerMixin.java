package name.bruhmod.mixin;

import name.bruhmod.item.EssenceCollector;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "killedEntity", at = @At("HEAD"))
    public void onKilledEntity(ServerLevel level, LivingEntity entity, CallbackInfoReturnable<Boolean> info) {
        EssenceCollector.onEntityKill((Player) ((LivingEntity) this), entity);
    }
}
