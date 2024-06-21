package name.bruhmod.world;

import name.bruhmod.items.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;

public class ModCustomTrades {
    public static void registerCustomTrades(){

        // Mythril Fragment -> Mess Hall, Witch Hut, Carpenter or Wizard Tower Map (Level 1)
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 1,
                factories ->
                    factories.add((entity, random) -> new TradeOffer(new TradedItem(ModItems.MYTHRIL_FRAGMENT, 1), new ItemStack(ModItems.WITCH_HUT_MAP), 1, 25, 0.2f))
        );
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 1,
                factories ->
                        factories.add((entity, random) -> new TradeOffer(new TradedItem(ModItems.MYTHRIL_FRAGMENT, 1), new ItemStack(ModItems.WIZARD_TOWER_MAP), 1, 25, 0.2f))
        );
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 1,
                factories ->
                        factories.add((entity, random) -> new TradeOffer(new TradedItem(ModItems.MYTHRIL_FRAGMENT, 1), new ItemStack(ModItems.MESS_HALL_MAP), 1, 25, 0.2f))
        );
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 1,
                factories ->
                        factories.add((entity, random) -> new TradeOffer(new TradedItem(ModItems.MYTHRIL_FRAGMENT, 1), new ItemStack(ModItems.CARPENTER_MAP), 1, 25, 0.2f))
        );

        //Mythril -> Barracks Map (Level 5(Max))
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER,  5,
                factories ->
                        factories.add((entity, random) -> new TradeOffer(new TradedItem(ModItems.MYTHRIL, 1), new ItemStack(ModItems.BARRACKS_MAP), 1, 50, 0.2f))
        );


    }
}
