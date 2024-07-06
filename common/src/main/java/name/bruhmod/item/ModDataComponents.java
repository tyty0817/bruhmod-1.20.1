package name.bruhmod.item;

import name.bruhmod.LeMod;
import name.bruhmod.util.RegistryHelper;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;

public class ModDataComponents {

    public static final DataComponentType<Integer> ESSENCE = DataComponentType.<Integer>builder().persistent(ExtraCodecs.NON_NEGATIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT).build();

    public static void register(RegistryHelper.Provider registerer) {
        registerer.register(BuiltInRegistries.DATA_COMPONENT_TYPE, LeMod.idOf("essence"), ESSENCE);
    }

}
