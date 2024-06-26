package name.bruhmod.world;

import name.bruhmod.item.ModItems;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.List;
import java.util.function.Consumer;

public class ModCustomTrades {

    public static void registerCustomTrades(TriConsumer<VillagerProfession, Integer, Consumer<List<VillagerTrades.ItemListing>>> registry){
        registry.accept(VillagerProfession.CARTOGRAPHER, 1, factories -> {
            factories.add((entity, random) -> new MerchantOffer(new ItemCost(ModItems.MYTHRIL_FRAGMENT, 1), new ItemStack(ModItems.WITCH_HUT_MAP), 1, 25, 0.2f));
            factories.add((entity, random) -> new MerchantOffer(new ItemCost(ModItems.MYTHRIL_FRAGMENT, 1), new ItemStack(ModItems.WIZARD_TOWER_MAP), 1, 25, 0.2f));
            factories.add((entity, random) -> new MerchantOffer(new ItemCost(ModItems.MYTHRIL_FRAGMENT, 1), new ItemStack(ModItems.MESS_HALL_MAP), 1, 25, 0.2f));
            factories.add((entity, random) -> new MerchantOffer(new ItemCost(ModItems.MYTHRIL_FRAGMENT, 1), new ItemStack(ModItems.CARPENTER_MAP), 1, 25, 0.2f));
        });

        //Mythril -> Barracks Map (Level 5(Max))
        registry.accept(VillagerProfession.CARTOGRAPHER,  5,
                factories ->
                        factories.add((entity, random) -> new MerchantOffer(new ItemCost(ModItems.MYTHRIL, 1), new ItemStack(ModItems.BARRACKS_MAP), 1, 50, 0.2f))
        );


    }
}
