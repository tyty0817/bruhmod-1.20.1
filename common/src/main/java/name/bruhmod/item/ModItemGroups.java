package name.bruhmod.item;

import name.bruhmod.LeMod;
import name.bruhmod.util.RegistryHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroups {

    /**
     * The item group containing all the items in the mod.
     */
    public static void register(RegistryHelper.RegistryConsumer registerer) {
        registerer.register(BuiltInRegistries.CREATIVE_MODE_TAB, LeMod.idOf(LeMod.MOD_ID), new CreativeModeTab.Builder(null, -1)
                .title(Component.translatable("itemgroup.bruhmod.group"))
                .icon(() -> new ItemStack(ModItems.MYTHRIL))
                .displayItems((displayContext, entries) -> ModItems.REGISTERER.values.values().forEach(entries::accept))
                .build());
    }
}
