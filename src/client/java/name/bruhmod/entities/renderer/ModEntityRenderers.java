package name.bruhmod.entities.renderer;

import name.bruhmod.entities.ModEntities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

@Environment(EnvType.CLIENT)
public class ModEntityRenderers {

    public static void register() {
        EntityRendererRegistry.register(ModEntities.BOSS, BossEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.LIGHTNING_BOTTLE, ThrownItemRenderer::new);
    }

}
