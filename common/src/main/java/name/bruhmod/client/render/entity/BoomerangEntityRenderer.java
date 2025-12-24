package name.bruhmod.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import name.bruhmod.entity.BoomerangEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;

public class BoomerangEntityRenderer extends EntityRenderer<BoomerangEntity> {

    private final ItemRenderer itemRenderer;

    protected BoomerangEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    public ResourceLocation getTextureLocation(BoomerangEntity entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }

    public void render(BoomerangEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        ItemStack item = entity.getItem();
//        this.random.setSeed((long)getSeedForItemStack(itemstack));
        BakedModel model = this.itemRenderer.getModel(item, entity.level(), null, entity.getId());

        float spin = (entity.getAge() + partialTicks) * (float) entity.getDeltaMovement().lengthSqr();

        Vec3 angle = entity.getLookAngle();

        poseStack.mulPose(new Quaternionf().rotationXYZ((float) (angle.x), (float) (angle.y), (float) angle.z + spin));

        itemRenderer.render(item, ItemDisplayContext.GROUND, false, poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, model);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
