package name.bruhmod.client.render.entity;

import name.bruhmod.entity.ModEntities;
import name.bruhmod.entity.SeedEntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ModEntityRenderers {

    public interface EntityRendererConsumer {
        <E extends Entity> void register(EntityType<? extends E> entityType,
                                       EntityRendererProvider<E> entityRendererFactory);
    }

    public static void registerRenderers(EntityRendererConsumer renderers) {
        renderers.register(ModEntities.BOSS, BossEntityRenderer::new);
        renderers.register(ModEntities.LIGHTNING_BOTTLE, ThrownItemRenderer::new);
        renderers.register(ModEntities.SEED, SeedEntityRenderer::new);
        renderers.register(ModEntities.BOOMERANG, BoomerangEntityRenderer::new);
    }

    public static void registerModels(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> models) {
        models.accept(SeedEntityModel.LOCATION, SeedEntityModel::createLayer);
    }

}
