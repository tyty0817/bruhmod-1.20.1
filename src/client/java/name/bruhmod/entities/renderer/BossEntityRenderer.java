package name.bruhmod.entities.renderer;

import name.bruhmod.entities.BossEntity;
import name.bruhmod.entities.BossEntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;

import static name.bruhmod.Mod.MOD_ID;

@Environment(EnvType.CLIENT)
public class BossEntityRenderer extends GeoEntityRenderer<BossEntity> {

    public BossEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new BossEntityModel());
    }

    @Override
    public Identifier getTexture(BossEntity entity) {
        return new Identifier(MOD_ID, "textures/entity/"+BossEntity.ID+".png");
    }


}
