package name.bruhmod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class ConfuseEffect extends StatusEffect {
    public ConfuseEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity pLivingEntity, int pAmplifier) {
        double x = pLivingEntity.getX();
        double y = pLivingEntity.getY();
        double z = pLivingEntity.getZ();

        //pLivingEntity.teleport(x, y, z);
        pLivingEntity.setVelocity(pLivingEntity.getVelocity().add(((Math.random() * pAmplifier) - (pAmplifier / 2.0)), 0, (Math.random() * pAmplifier) - (pAmplifier / 2.0)));

        return super.applyUpdateEffect(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int pDuration, int pAmplifier) {
        return true;
    }
}
