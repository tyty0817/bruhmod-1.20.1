package name.bruhmod.item;

import com.mojang.datafixers.util.Pair;
import name.bruhmod.Mod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ModMapItem extends Item {

    private final String structureId;
    private final Holder<MapDecorationType> decorationType;

    public ModMapItem(String structureId, Holder<MapDecorationType> decorationType) {
        super(new Properties());
        this.structureId = structureId;
        this.decorationType = decorationType;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, @NotNull Player user, @NotNull InteractionHand hand) {
        if (!world.isClientSide()) {
            ServerLevel serverWorld = (ServerLevel) world;

            ResourceKey<Structure> key = ResourceKey.create(Registries.STRUCTURE, Mod.idOf(structureId));
            Optional<Holder.Reference<Structure>> entry = serverWorld.registryAccess().registryOrThrow(Registries.STRUCTURE).getHolder(key);

            if (entry.isEmpty()) {
                Mod.LOGGER.error("Failed to get structure with key: {}!", key);
                return InteractionResultHolder.fail(user.getItemInHand(hand));
            }


            Pair<BlockPos, ?> pair = serverWorld.getChunkSource().getGenerator().findNearestMapStructure(serverWorld, HolderSet.direct(entry.get()), user.blockPosition(), 100, true);

            if (pair == null) {
                return InteractionResultHolder.fail(user.getItemInHand(hand));
            }

            BlockPos pos = pair.getFirst();

            if (pos != null) {
                ItemStack itemStack = MapItem.create(serverWorld, pos.getX(), pos.getZ(), (byte)4, true, true);
                MapItem.renderBiomePreviewMap(serverWorld, itemStack);
                MapItemSavedData.addTargetDecoration(itemStack, pos, "+", decorationType);
                itemStack.set(DataComponents.ITEM_NAME, Component.translatable("item."+ Mod.MOD_ID+"."+ structureId +"_map"));
                return InteractionResultHolder.pass(itemStack);
            } else {
                return InteractionResultHolder.fail(user.getItemInHand(hand));
            }

        } else {
            return InteractionResultHolder.pass(user.getItemInHand(hand));
        }
    }
}
