package name.bruhmod.mixin;

import name.bruhmod.recipe.natural.NaturalRecipe;
import name.bruhmod.recipe.natural.NaturalSources;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AreaEffectCloud.class)
public abstract class AreaEffectCloudMixin extends Entity {
    @Shadow private PotionContents potionContents;

    public AreaEffectCloudMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo info) {
        var world = this.level();
        if (this.tickCount % 5 == 0 && !world.isClientSide) {
            NaturalRecipe.craftAtPosition(world, this.getBoundingBox(), new NaturalSources(NonNullList.create(), this.potionContents.potion()));
        }
    }
}
