package name.bruhmod.entity;

import name.bruhmod.LeMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageTypes {

    public static final ResourceKey<DamageType> PROJECTILE_SPRAY = ResourceKey.create(Registries.DAMAGE_TYPE, LeMod.idOf("projectile_spray"));

}
