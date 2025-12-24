package name.bruhmod;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeMod {
    public static final String MOD_ID = "bruhmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static ResourceLocation idOf(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

}
