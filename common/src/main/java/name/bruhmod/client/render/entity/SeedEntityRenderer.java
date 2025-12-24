package name.bruhmod.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import name.bruhmod.LeMod;
import name.bruhmod.entity.SeedEntity;
import name.bruhmod.entity.SeedEntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class SeedEntityRenderer extends EntityRenderer<SeedEntity> {

    private static final ResourceLocation SEED_TEXTURE_LOCATION = LeMod.idOf("textures/entity/seed.png");
    private final SeedEntityModel model;

    protected SeedEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new SeedEntityModel(context.bakeLayer(SeedEntityModel.LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(SeedEntity seedEntity) {
        return SEED_TEXTURE_LOCATION;
    }

    @Override
    public void render(SeedEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        VertexConsumer buf  = buffer.getBuffer(this.model.renderType(SEED_TEXTURE_LOCATION));
        this.model.renderToBuffer(poseStack, buf, packedLight, OverlayTexture.NO_OVERLAY);

        poseStack.popPose();

        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
    }
}
