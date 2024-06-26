package name.bruhmod.client.render.entity;

import name.bruhmod.entities.ModEntities;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public class ModEntityRenderers {

    public interface EntityRendererConsumer {
        <E extends Entity> void register(EntityType<? extends E> entityType,
                                       EntityRendererProvider<E> entityRendererFactory);
    }

    public static void register(EntityRendererConsumer registry) {
        registry.register(ModEntities.BOSS, BossEntityRenderer::new);
        registry.register(ModEntities.LIGHTNING_BOTTLE, ThrownItemRenderer::new);
    }

}
