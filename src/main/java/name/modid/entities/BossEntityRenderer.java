package name.modid.entities;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import static name.modid.Mod.MOD_ID;

@Environment(EnvType.CLIENT)
public class BossEntityRenderer extends GeoEntityRenderer<BossEntity> {

    public static void register() {
        EntityRendererRegistry.INSTANCE.register(BossEntity.TYPE, BossEntityRenderer::new);
    }

    public BossEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new BossEntityModel());
    }

    @Override
    public Identifier getTexture(BossEntity entity) {
        return new Identifier(MOD_ID, "textures/entity/"+BossEntity.ID+".png");
    }


}
