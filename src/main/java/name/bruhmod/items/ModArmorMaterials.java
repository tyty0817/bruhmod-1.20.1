package name.bruhmod.items;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

import static name.bruhmod.Mod.MOD_ID;

public class ModArmorMaterials {

    public static final RegistryEntry<ArmorMaterial> MYTHRIL_DIAMOND = register(
            "mythril_studded", 40, new int[]{ 4, 9, 7, 4, 8 }, 20,
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.0f, 0.15f, () -> Ingredient.ofItems(ModItems.MYTHRIL)),
    MYTHRIL_NETHERITE = register("mythril_infused", 45, new int[]{ 5, 10, 8, 5, 9 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0f, 0.2f, () -> Ingredient.ofItems(ModItems.MYTHRIL));

    public static void register() {

    }

    private static RegistryEntry<ArmorMaterial> register(String id, int enchantibility, int[] defense, int idk, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> ingredientSupplier) {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(Identifier.ofVanilla(id)));
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(MOD_ID, id), new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
            map.put(ArmorItem.Type.BOOTS, defense[3]);
            map.put(ArmorItem.Type.LEGGINGS, defense[2]);
            map.put(ArmorItem.Type.CHESTPLATE, defense[1]);
            map.put(ArmorItem.Type.HELMET, defense[0]);
            map.put(ArmorItem.Type.BODY, defense[4]);
        }), enchantibility, equipSound, ingredientSupplier, list, toughness, knockbackResistance));
    }
}
