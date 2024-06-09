package name.bruhmod.entities;

import name.bruhmod.Mod;

public class ModEntities {
    public static void registerModEntities() {
        BossEntity.register();
        Mod.LOGGER.info("Registering Entities for " + Mod.MOD_ID);
    }

}