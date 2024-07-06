package name.bruhmod;

import name.bruhmod.client.gui.ModGUI;
import name.bruhmod.client.render.entity.ModEntityRenderers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

@Environment(EnvType.CLIENT)
public class ModFabricClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ModEntityRenderers.register(EntityRendererRegistry::register);
		HudRenderCallback.EVENT.register(ModGUI::drawEssenceOverlay);
		ItemTooltipCallback.EVENT.register(ModGUI::onTooltipEvent);
	}
}