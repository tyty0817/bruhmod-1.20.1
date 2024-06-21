package name.bruhmod.world;

import name.bruhmod.items.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

public class ModCustomTrades {
    public static void registerCustomTrades(){

        // Mythril Fragment -> Mess Hall, Witch Hut, Carpenter or Wizard Tower Map (Level 1)
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 1,
                factories -> {
                    factories.add((entity, random) -> new MerchantOffer(new ItemCost(ModItems.MYTHRIL_FRAGMENT, 1), new ItemStack(ModItems.WITCH_HUT_MAP), 1, 25, 0.2f));
                    factories.add((entity, random) -> new MerchantOffer(new ItemCost(ModItems.MYTHRIL_FRAGMENT, 1), new ItemStack(ModItems.WIZARD_TOWER_MAP), 1, 25, 0.2f));
                    factories.add((entity, random) -> new MerchantOffer(new ItemCost(ModItems.MYTHRIL_FRAGMENT, 1), new ItemStack(ModItems.MESS_HALL_MAP), 1, 25, 0.2f));
                    factories.add((entity, random) -> new MerchantOffer(new ItemCost(ModItems.MYTHRIL_FRAGMENT, 1), new ItemStack(ModItems.CARPENTER_MAP), 1, 25, 0.2f));
                });

        //Mythril -> Barracks Map (Level 5(Max))
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER,  5,
                factories ->
                        factories.add((entity, random) -> new MerchantOffer(new ItemCost(ModItems.MYTHRIL, 1), new ItemStack(ModItems.BARRACKS_MAP), 1, 50, 0.2f))
        );


    }
}
