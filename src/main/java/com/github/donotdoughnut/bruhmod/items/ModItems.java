package com.github.donotdoughnut.bruhmod.items;

import com.github.donotdoughnut.bruhmod.Mod;
import com.github.donotdoughnut.bruhmod.blocks.ModBlocks;
import com.github.donotdoughnut.bruhmod.entities.BossEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.*;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

import static com.github.donotdoughnut.bruhmod.Mod.MOD_ID;

public class ModItems {

    /*
     * Items
     */
    public static final Item MYTHRIL = registerItem("mythril");
    public static final Item MYTHRIL_DUST = registerItem("mythril_dust");
    public static final Item MYTHRIL_FRAGMENT = registerItem("mythril_fragment");
    public static final Item MYTHRIL_UPGRADE = registerItem("mythril_upgrade_smithing_template");
    public static final Item BARRACKS_MAP = registerItem("barracks_map", new BarracksMapItem());

    /*
     * Blocks
     */
    public static final BlockItem
            MYTHRIL_ORE = registerBlock(ModBlocks.MYTHRIL_ORE),
            DEEPSLATE_MYTHRIL_ORE = registerBlock(ModBlocks.DEEPSLATE_MYTHRIL_ORE),
            MYTHRIL_BLOCK = registerBlock(ModBlocks.MYTHRIL_BLOCK);
    /*
     * Armor
     */
    public static final ArmorSet
            MYTHRIL_STUDDED_ARMOR = registerArmor("mythril_studded", ModArmorMaterials.MYTHRIL_DIAMOND, new FabricItemSettings()),
            MYTHRIL_INFUSED_ARMOR = registerArmor("mythril_infused", ModArmorMaterials.MYTHRIL_NETHERITE, new FabricItemSettings());

    /*
     * Spawn Eggs
     */
    public static final SpawnEggItem
        BRITISH_MAN = registerEntity(BossEntity.TYPE, 0xd59890, 0xd7b4ae);

    public static void register() {
        Mod.LOGGER.info("Registering Mod Items for " + MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries ->
            entries.addAfter(Items.NETHERITE_INGOT, MYTHRIL)
        );
    }

    /**
     * Register an item with default parameters
     * @param name The ID of the item.
     * @return The item, registered
     */
    private static Item registerItem(String name) {
        return registerItem(name, new Item(new Item.Settings()));
    }

    /**
     * Register an item with custom parameters, for example if you were making armor or special items
     * @param name The ID of the item.
     * @return The item, registered
     */
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(MOD_ID, name), item);
    }

    /**
     * Register a block as an item.
     * @param block The block to be registered
     * @return The Block item, registered
     */
    private static BlockItem registerBlock(Block block) {
        return Registry.register(Registries.ITEM, Registries.BLOCK.getId(block), new BlockItem(block, new Item.Settings()));
    }

    /**
     * Register an entity as a spawn egg.
     */
    private static SpawnEggItem registerEntity(EntityType<? extends MobEntity> entity, int primaryColor, int secondaryColor) {
        return Registry.register(Registries.ITEM, new Identifier(MOD_ID, Registries.ENTITY_TYPE.getId(entity).getPath() + "_spawn_egg"), new SpawnEggItem(entity, primaryColor, secondaryColor, new Item.Settings()));
    }

    /** Collection of armor pieces */
    public record ArmorSet(ArmorItem helmet, ArmorItem chestplate, ArmorItem leggings, ArmorItem boots) {

        public void apply(Consumer<ArmorItem> consumer) {
            consumer.accept(helmet);
            consumer.accept(chestplate);
            consumer.accept(leggings);
            consumer.accept(boots);
        }

    }

    /**
     * Register a set of armor
     * @param prefix The name of the armor
     * @param material The armor material
     * @param settings The settings of the armor items
     * @return The registered set of armor
     */
    private static ArmorSet registerArmor(String prefix, ArmorMaterial material, Item.Settings settings) {
        return new ArmorSet(
                (ArmorItem) registerItem(prefix + "_helmet", new ArmorItem(material, ArmorItem.Type.HELMET, settings)),
                (ArmorItem) registerItem(prefix + "_chestplate", new ArmorItem(material, ArmorItem.Type.CHESTPLATE, settings)),
                (ArmorItem) registerItem(prefix + "_leggings", new ArmorItem(material, ArmorItem.Type.LEGGINGS, settings)),
                (ArmorItem) registerItem(prefix + "_boots", new ArmorItem(material, ArmorItem.Type.BOOTS, settings))
        );
    }

}
