package com.github.donotdoughnut.bruhmod.entities;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import static com.github.donotdoughnut.bruhmod.Mod.MOD_ID;

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
