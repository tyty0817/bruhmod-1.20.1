package name.bruhmod.mixin;

import name.bruhmod.recipe.natural.NaturalRecipe;
import name.bruhmod.recipe.natural.NaturalSources;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {

    public ItemEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Inject(method = "hurt", at = @At("HEAD"))
    void hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        NaturalRecipe.craftAtPosition(
                level(),
                AABB.ofSize(position(), 5.0, 5.0, 5.0),
                NaturalSources.ofDamage(source.typeHolder())
        );
    }

}
