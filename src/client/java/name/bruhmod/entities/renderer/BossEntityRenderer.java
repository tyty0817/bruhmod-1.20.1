package name.bruhmod.entities.renderer;

import name.bruhmod.Mod;
import name.bruhmod.entities.BossEntity;
import name.bruhmod.entities.BossEntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


@Environment(EnvType.CLIENT)
public class BossEntityRenderer extends GeoEntityRenderer<BossEntity> {

    public BossEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new BossEntityModel());
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(BossEntity entity) {
        return Mod.idOf("textures/entity/"+BossEntity.ID+".png");
    }


}
