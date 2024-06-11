package name.bruhmod.items.maps;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.map.MapIcon;
import net.minecraft.item.map.MapState;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static name.bruhmod.Mod.LOGGER;
import static name.bruhmod.Mod.MOD_ID;

public class MessHallMapItem extends Item{

    public MessHallMapItem() {
        super(new Item.Settings());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            ServerWorld serverWorld = (ServerWorld) world;

            var entry = serverWorld.getRegistryManager().get(RegistryKeys.STRUCTURE).getEntry(RegistryKey.of(RegistryKeys.STRUCTURE, new Identifier(MOD_ID, "mess_hall")));

            if (entry.isEmpty()) {
                LOGGER.warn("Failed to get barracks structure!");
                return TypedActionResult.fail(user.getStackInHand(hand));
            }

            BlockPos blockPos = serverWorld.getChunkManager().getChunkGenerator().locateStructure(serverWorld, RegistryEntryList.of(entry.get()), user.getBlockPos(), 100, true).getFirst();

            if (blockPos != null) {
                ItemStack itemStack = FilledMapItem.createMap(serverWorld, blockPos.getX(), blockPos.getZ(), (byte)4, true, true);
                FilledMapItem.fillExplorationMap(serverWorld, itemStack);
                MapState.addDecorationsNbt(itemStack, blockPos, "+", MapIcon.Type.BANNER_BLACK);
                itemStack.setCustomName(Text.translatable("item."+MOD_ID+".mess_hall_map"));
                return TypedActionResult.pass(itemStack);
            } else {
                return TypedActionResult.fail(user.getStackInHand(hand));
            }

        } else {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }
    }
}
