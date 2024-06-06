package name.modid;

import name.modid.entities.BossEntityRenderer;
import net.fabricmc.api.ClientModInitializer;


public class ModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BossEntityRenderer.register();
    }
}
