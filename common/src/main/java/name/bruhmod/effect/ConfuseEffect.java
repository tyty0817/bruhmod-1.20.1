package name.bruhmod.effect;


import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ConfuseEffect extends MobEffect {
    public ConfuseEffect(MobEffectCategory MobEffectCategory, int color) {
        super(MobEffectCategory, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        double x = pLivingEntity.getX();
        double y = pLivingEntity.getY();
        double z = pLivingEntity.getZ();

        //pLivingEntity.teleport(x, y, z);
        pLivingEntity.setDeltaMovement(pLivingEntity.getDeltaMovement().add(((Math.random() * pAmplifier) - (pAmplifier / 2.0)), 0, (Math.random() * pAmplifier) - (pAmplifier / 2.0)));

        return super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
}
