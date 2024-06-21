package name.bruhmod.potion;

import name.bruhmod.Mod;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModPotions {

//	public static final Potion POTION_OF_CORRUPTION =
//			Registry.register(Registries.POTION, Identifier.of("test", "test_potion"),
//					new Potion(new StatusEffectInstance(StatusEffects.WITHER, 200, 0)));

	public static Potion POTION_OF_CORRUPTION;
	public static Potion registerPotion(String name){
		return Registry.register(Registries.POTION, Identifier.of(Mod.MOD_ID, name),
				new Potion(new StatusEffectInstance(StatusEffects.WITHER, 400, 4), new StatusEffectInstance(StatusEffects.NAUSEA, 200, 0), new StatusEffectInstance(StatusEffects.BLINDNESS, 100, 0)));
	}

	public static void registerPotions(){
		POTION_OF_CORRUPTION = registerPotion("potion_of_corruption");
	}


	public static void registerPotionsRecipes(){
		Mod.LOGGER.info("TODO: Re-enable potion recipe!");
//		BrewingRecipeRegistry.create().registerPotionRecipe(Potions.THICK, ModItems.CORRUPTED_SLAG, ModPotions.POTION_OF_CORRUPTION);
	}
}