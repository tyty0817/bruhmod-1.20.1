package name.bruhmod.item;

import name.bruhmod.LeMod;
import name.bruhmod.util.RegistryHelper;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {

    public static final RegistryHelper<ArmorMaterial> REGISTERER = new RegistryHelper<>(BuiltInRegistries.ARMOR_MATERIAL);

    public static final ArmorMaterial MYTHRIL_DIAMOND = register(
            "mythril_studded", 40, new int[]{ 4, 9, 7, 4, 8 }, 20,
            SoundEvents.ARMOR_EQUIP_GOLD, 2.0f, 0.15f, () -> Ingredient.of(ModItems.MYTHRIL)),
    MYTHRIL_NETHERITE = register("mythril_infused", 45, new int[]{ 5, 10, 8, 5, 9 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0f, 0.2f, () -> Ingredient.of(ModItems.MYTHRIL));

    private static ArmorMaterial register(String id, int enchantibility, int[] defense, int idk, Holder<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> ingredientSupplier) {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(ResourceLocation.withDefaultNamespace(id)));
        ArmorMaterial material = new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
            map.put(ArmorItem.Type.BOOTS, defense[3]);
            map.put(ArmorItem.Type.LEGGINGS, defense[2]);
            map.put(ArmorItem.Type.CHESTPLATE, defense[1]);
            map.put(ArmorItem.Type.HELMET, defense[0]);
            map.put(ArmorItem.Type.BODY, defense[4]);
        }), enchantibility, equipSound, ingredientSupplier, list, toughness, knockbackResistance);
        return REGISTERER.add(LeMod.idOf(id), material);
    }
}
