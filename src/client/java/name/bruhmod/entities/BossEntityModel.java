package name.bruhmod.entities;

import name.bruhmod.Mod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;


public class BossEntityModel extends GeoModel<BossEntity> {


    @Override
    public ResourceLocation getModelResource(BossEntity animatable) {
        return Mod.idOf("geo/" + BossEntity.ID + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BossEntity animatable) {
        return Mod.idOf("textures/" + BossEntity.ID + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(BossEntity animatable) {
        return Mod.idOf("animations/" + BossEntity.ID + ".animation.json");
    }
}