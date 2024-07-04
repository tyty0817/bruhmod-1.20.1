package name.bruhmod.effect;

import name.bruhmod.LeMod;
import name.bruhmod.util.RegistryHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ModEffects {
    public static final MobEffect CONFUSE = new ConfuseEffect(MobEffectCategory.HARMFUL, 3124687);

    public static void registerEffects(RegistryHelper.Provider registerer) {
        registerer.register(BuiltInRegistries.MOB_EFFECT, LeMod.idOf("confuse"), CONFUSE);
    }
}
