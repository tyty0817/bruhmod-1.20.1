package name.bruhmod.items;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.map.MapDecorationType;
import net.minecraft.item.map.MapState;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.Structure;

import static name.bruhmod.Mod.LOGGER;
import static name.bruhmod.Mod.MOD_ID;

public class ModMapItem extends Item {

    private final String structureId;
    private final RegistryEntry<MapDecorationType> decorationType;

    public ModMapItem(String structureId, RegistryEntry<MapDecorationType> decorationType) {
        super(new Settings());
        this.structureId = structureId;
        this.decorationType = decorationType;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            ServerWorld serverWorld = (ServerWorld) world;

            RegistryKey<Structure> key = RegistryKey.of(RegistryKeys.STRUCTURE, Identifier.of(MOD_ID, structureId));
            var entry = serverWorld.getRegistryManager().get(RegistryKeys.STRUCTURE).getEntry(key);

            if (entry.isEmpty()) {
                LOGGER.error("Failed to get structure with key: {}!", key);
                return TypedActionResult.fail(user.getStackInHand(hand));
            }

            BlockPos blockPos = serverWorld.getChunkManager().getChunkGenerator().locateStructure(serverWorld, RegistryEntryList.of(entry.get()), user.getBlockPos(), 100, true).getFirst();

            if (blockPos != null) {
                ItemStack itemStack = FilledMapItem.createMap(serverWorld, blockPos.getX(), blockPos.getZ(), (byte)4, true, true);
                FilledMapItem.fillExplorationMap(serverWorld, itemStack);
                MapState.addDecorationsNbt(itemStack, blockPos, "+", decorationType);
                itemStack.set(DataComponentTypes.ITEM_NAME, Text.translatable("item."+MOD_ID+"."+ structureId +"_map"));
                return TypedActionResult.pass(itemStack);
            } else {
                return TypedActionResult.fail(user.getStackInHand(hand));
            }

        } else {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }
    }
}
