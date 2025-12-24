package name.bruhmod.recipe.staff;

import name.bruhmod.recipe.util.WorldRecipeInput;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;

import java.util.stream.Stream;

public class StaffRecipeInput extends WorldRecipeInput {

    public final BlockPos pos;
    public final Block[] neighbors;
    public final PotionContents potion;

    public StaffRecipeInput(Level level, BlockPos pos, PotionContents potion) {
        super(level, new AABB(pos.above()));
        this.pos = pos;
        this.neighbors = Stream.of(Direction.EAST, Direction.WEST, Direction.NORTH, Direction.SOUTH).map(direction -> level.getBlockState(pos.offset(direction.getNormal())).getBlock()).toArray(Block[]::new);
        this.potion = potion;
    }
}
