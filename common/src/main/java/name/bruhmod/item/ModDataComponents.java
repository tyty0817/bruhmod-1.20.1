package name.bruhmod.item;

import name.bruhmod.LeMod;
import name.bruhmod.util.RegistryHelper;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;

import java.util.function.BiConsumer;

public class ModDataComponents {

    public static final DataComponentType<Integer> ESSENCE = DataComponentType.<Integer>builder().persistent(ExtraCodecs.NON_NEGATIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT).build();

    public static void register(BiConsumer<ResourceLocation, DataComponentType<?>> registerer) {
        registerer.accept(LeMod.idOf("essence"), ESSENCE);
    }

}
