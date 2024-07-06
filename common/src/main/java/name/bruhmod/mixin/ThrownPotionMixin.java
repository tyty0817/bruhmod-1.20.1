package name.bruhmod.mixin;

import name.bruhmod.recipe.natural.NaturalRecipe;
import name.bruhmod.recipe.natural.NaturalSources;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThrownPotion.class)
public abstract class ThrownPotionMixin extends ThrowableItemProjectile {

    public ThrownPotionMixin(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(at = @At("HEAD"), method = "applySplash")
    private void applySplash(Iterable<MobEffectInstance> iterable, @Nullable Entity entity, CallbackInfo info) {
        NaturalRecipe.craftAtPosition(
                this.level(),
                this.getBoundingBox().inflate(4.0, 2.0, 4.0),
                NaturalSources.ofPotion(this.getItem().getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).potion())
        );
    }

}
