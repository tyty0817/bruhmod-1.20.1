package name.bruhmod.potion;

import name.bruhmod.Mod;
import name.bruhmod.items.ModItems;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;

public class ModPotions {

//	public static final Potion POTION_OF_CORRUPTION =
//			Registry.register(Registries.POTION, ResourceLocation.of("test", "test_potion"),
//					new Potion(new MobEffectInstance(MobEffects.WITHER, 200, 0)));

	public static Holder<Potion> POTION_OF_CORRUPTION;
	public static Holder<Potion> registerPotion(String name){
		return Registry.registerForHolder(BuiltInRegistries.POTION, Mod.idOf(name),
				new Potion(new MobEffectInstance(MobEffects.WITHER, 400, 4), new MobEffectInstance(MobEffects.CONFUSION, 200, 0), new MobEffectInstance(MobEffects.BLINDNESS, 100, 0)));
	}

	public static void registerPotions(){
		POTION_OF_CORRUPTION = registerPotion("potion_of_corruption");
	}


	public static void registerPotionsRecipes(){
		FabricBrewingRecipeRegistryBuilder.BUILD.register((callback) -> {
			callback.registerPotionRecipe(Potions.THICK, Ingredient.of(ModItems.CORRUPTED_SLAG), ModPotions.POTION_OF_CORRUPTION);
		});
	}
}