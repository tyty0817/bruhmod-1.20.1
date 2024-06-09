package name.bruhmod;

import name.bruhmod.entities.BossEntityRenderer;
import net.fabricmc.api.ClientModInitializer;

public class ModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BossEntityRenderer.register();
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
}