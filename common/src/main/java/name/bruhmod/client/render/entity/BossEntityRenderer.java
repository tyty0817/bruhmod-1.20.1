package name.bruhmod.client.render.entity;

import name.bruhmod.LeMod;
import name.bruhmod.entities.BossEntity;
import name.bruhmod.entities.BossEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BossEntityRenderer extends GeoEntityRenderer<BossEntity> {

    public BossEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new BossEntityModel());
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull BossEntity entity) {
        return LeMod.idOf("textures/entity/"+BossEntity.ID+".png");
    }


}