package name.bruhmod.item;

import name.bruhmod.LeMod;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.BiConsumer;

public class ModItemGroups {

    /**
     * The item group containing all the items in the mod.
     */
    public static void register(BiConsumer<ResourceLocation, CreativeModeTab> registerer) {
        registerer.accept(LeMod.idOf(LeMod.MOD_ID), new CreativeModeTab.Builder(null, -1)
                .title(Component.translatable("itemgroup.bruhmod.group"))
                .icon(() -> new ItemStack(ModItems.MYTHRIL))
                .displayItems((displayContext, entries) -> ModItems.ITEMS.values.values().forEach(entries::accept))
                .build());
    }
}
