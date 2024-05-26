package com.github.donotdoughnut.bruhmod;

import com.github.donotdoughnut.bruhmod.entities.TestEntityRenderer;
import net.fabricmc.api.ClientModInitializer;

public class ModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		TestEntityRenderer.register();
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
}