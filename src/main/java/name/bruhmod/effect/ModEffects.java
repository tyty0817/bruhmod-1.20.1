package name.bruhmod.effect;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import static name.bruhmod.Mod.MOD_ID;

public class ModEffects {
    public static StatusEffect CONFUSE;

    public static StatusEffect registerStatusEffect(String name){
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(MOD_ID, name),
                new ConfuseEffect(StatusEffectCategory.HARMFUL, 3124687));
    }

    public static void registerEffects() {
        CONFUSE = registerStatusEffect("confuse");
    }
}
