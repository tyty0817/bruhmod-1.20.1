package name.bruhmod;

import name.bruhmod.potion.ModPotions;
import name.bruhmod.world.ModCustomTrades;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.util.ArrayList;

@EventBusSubscriber(modid = LeMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class GameBusEvents {

    @SubscribeEvent
    private static void customTradesSetup(final VillagerTradesEvent event) {
        ModCustomTrades.registerCustomTrades((profession, l, registry) -> {
            int level = l;
            if (event.getType().equals(profession)) {
                registry.accept(event.getTrades().getOrDefault(level, new ArrayList<>()));
            }
        });
    }

    @SubscribeEvent
    private static void potionBrewingSetup(final RegisterBrewingRecipesEvent event) {
        ModPotions.registerRecipes(event.getBuilder());
    }


}
