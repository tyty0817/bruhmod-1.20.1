package name.bruhmod.entities;

import name.bruhmod.LeMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BossEntityModel extends GeoModel<BossEntity> {

    @Override
    public ResourceLocation getModelResource(BossEntity animatable) {
        return LeMod.idOf("geo/" + BossEntity.ID + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BossEntity animatable) {
        return LeMod.idOf("textures/" + BossEntity.ID + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(BossEntity animatable) {
        return LeMod.idOf("animations/" + BossEntity.ID + ".animation.json");
    }
}