package name.bruhmod.potion;

import name.bruhmod.LeMod;
import name.bruhmod.item.ModItems;
import name.bruhmod.util.RegistryHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;

public class ModPotions {

    public static final RegistryHelper<Potion> REGISTERER = new RegistryHelper<>(BuiltInRegistries.POTION);

    public static final Potion POTION_OF_CORRUPTION = registerPotion("potion_of_corruption", new Potion(new MobEffectInstance(MobEffects.WITHER, 400, 4), new MobEffectInstance(MobEffects.CONFUSION, 200, 0), new MobEffectInstance(MobEffects.BLINDNESS, 100, 0)));

    public static Potion registerPotion(String name, Potion potion) {
        return REGISTERER.add(LeMod.idOf(name), potion);
    }

    public static void registerRecipes(PotionBrewing.Builder builder) {
        builder.addMix(Potions.THICK, ModItems.CORRUPTED_SLAG, Holder.direct(ModPotions.POTION_OF_CORRUPTION));
    }
}