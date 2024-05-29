package com.github.donotdoughnut.bruhmod.util;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.map.MapIcon;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

import static com.github.donotdoughnut.bruhmod.Mod.MOD_ID;

public class ModCustomTrades {
    public static void registerCustomTrades(){
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffers.SellMapFactory(1,
                            TagKey.of(RegistryKeys.STRUCTURE, new Identifier(MOD_ID, "barracks")),
                            "bruhmod.filled_map.barracks", MapIcon.Type.BANNER_BLACK,
                            12, 25).create(entity, random));

        });

    }
}
