package name.modid.util;

import name.modid.items.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

public class ModCustomTrades {
    public static void registerCustomTrades(){
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 1,
                factories ->
                    factories.add((entity, random) -> new TradeOffer(new ItemStack(ModItems.MYTHRIL, 1), new ItemStack(ModItems.BARRACKS_MAP), 12, 25, 0.2f))


        );

    }
}
