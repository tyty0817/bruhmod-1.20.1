package name.bruhmod.effect;

import name.bruhmod.Mod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ModEffects {
    public static MobEffect CONFUSE;

    public static MobEffect registerMobEffect(String name){
        return Registry.register(BuiltInRegistries.MOB_EFFECT, Mod.idOf(name),
                new ConfuseEffect(MobEffectCategory.HARMFUL, 3124687));
    }

    public static void registerEffects() {
        CONFUSE = registerMobEffect("confuse");
    }
}
