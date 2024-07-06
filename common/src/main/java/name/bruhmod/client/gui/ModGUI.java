package name.bruhmod.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import name.bruhmod.LeMod;
import name.bruhmod.item.EssenceCollector;
import name.bruhmod.item.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public final class ModGUI {

    public static void onTooltipEvent(ItemStack stack, Item.TooltipContext tooltipContext, TooltipFlag ctx, List<Component> tooltip) {
        if (stack.has(ModDataComponents.ESSENCE)) {
            tooltip.add(Component.translatable("item."+LeMod.MOD_ID+".essence.tooltip").append(": " + stack.get(ModDataComponents.ESSENCE)).withStyle(ChatFormatting.ITALIC, ChatFormatting.DARK_GREEN));
        }
    }

    public static void drawEssenceOverlay(GuiGraphics gui, DeltaTracker delta) {
        PoseStack stack = gui.pose();
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.options.hideGui)
            return;

        ProfilerFiller profiler = minecraft.getProfiler();

        profiler.push(LeMod.MOD_ID + "-hud");

        ItemStack mainHand = minecraft.player.getMainHandItem(), otherHand = minecraft.player.getOffhandItem();

        if (mainHand.is(EssenceCollector.HAS_ESSENCE)) {
            int essence = mainHand.getOrDefault(ModDataComponents.ESSENCE, 0);
            int max = EssenceCollector.totalEssence(minecraft.player.getInventory());

            // 182 is hotbar width, 1 pixel padding
            int minX = (minecraft.getWindow().getGuiScaledWidth() + 182) / 2 + 1;
            // start at top of hotbar
            int minY = minecraft.getWindow().getGuiScaledHeight() - 20;

            // get max essence string, so the width can be counted for the fraction line
            String maxEssence = "" + max;

            final int color = ChatFormatting.DARK_GREEN.getColor();

//            FormattedCharSequence.forward("" + essence, Style.EMPTY.applyFormat(ChatFormatting.ITALIC));

            gui.hLine(minX, minX + minecraft.font.width(maxEssence) - 2, minY + minecraft.font.lineHeight - 1, -1);
            gui.drawString(minecraft.font, "" + essence, minX, minY, color);
            gui.drawString(minecraft.font, maxEssence, minX, minY + minecraft.font.lineHeight + 1, color);
        }

        profiler.pop();
    }
}
