package name.bruhmod;

import name.bruhmod.entities.renderer.ModEntityRenderers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class ModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ModEntityRenderers.register();
	}
}