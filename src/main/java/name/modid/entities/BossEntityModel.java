package name.modid.entities;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

import static name.modid.Mod.MOD_ID;

public class BossEntityModel extends GeoModel<BossEntity> {


    @Override
    public Identifier getModelResource(BossEntity animatable) {
        return new Identifier(MOD_ID, "geo/" + BossEntity.ID + ".geo.json");
    }

    @Override
    public Identifier getTextureResource(BossEntity animatable) {
        return new Identifier(MOD_ID, "textures/" + BossEntity.ID + ".png");
    }

    @Override
    public Identifier getAnimationResource(BossEntity animatable) {
        return new Identifier(MOD_ID, "animations/" + BossEntity.ID + ".animation.json");
    }
}