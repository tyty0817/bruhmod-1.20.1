package com.github.donotdoughnut.bruhmod.entities;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

import static com.github.donotdoughnut.bruhmod.Mod.MOD_ID;

@Environment(EnvType.CLIENT)
public class TestEntityRenderer extends MobEntityRenderer {

    public static final EntityModelLayer MODEL = new EntityModelLayer(new Identifier(MOD_ID, "test_entity"), "main");

    public static void register() {
        EntityRendererRegistry.INSTANCE.register(TestEntity.TYPE, TestEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(MODEL, TestEntityModel::getTexturedModelData);
    }

    public TestEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new TestEntityModel(context.getPart(MODEL)), 0.5f);
    }

    @Override
    public Identifier getTexture(Entity entity) {
        return new Identifier(MOD_ID, "textures/entity/fabric_cube.png");
    }
}
