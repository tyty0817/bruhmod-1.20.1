package com.github.donotdoughnut.bruhmod.util;

import com.github.donotdoughnut.bruhmod.items.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

public class ModCustomTrades {
    public static void registerCustomTrades(){
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 1,
                factories ->
                    factories.add((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 12), new ItemStack(Items.COMPASS), new ItemStack(ModItems.BARRACKS_MAP), 12, 25, 0.2f))


        );

    }
}
