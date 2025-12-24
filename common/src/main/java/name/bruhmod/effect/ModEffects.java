package name.bruhmod.effect;

import name.bruhmod.LeMod;
import name.bruhmod.util.RegistryHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import java.util.function.BiConsumer;

public class ModEffects {
    public static final MobEffect CONFUSE = new ConfuseEffect(MobEffectCategory.HARMFUL, 3124687);

    public static void registerEffects(BiConsumer<ResourceLocation, MobEffect> registerer) {
        registerer.accept(LeMod.idOf("confuse"), CONFUSE);
    }
}
