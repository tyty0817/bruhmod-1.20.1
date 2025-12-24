package name.bruhmod.blocks;

import name.bruhmod.worldgen.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.BlockHitResult;

public class JutinPortalBlock extends Block {

    public JutinPortalBlock() {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        
        if (!level.isClientSide) {
            ResourceKey<Level> nextDimKey = level.dimension() == ModDimensions.JUTINDIM ? Level.OVERWORLD : ModDimensions.JUTINDIM;

            ServerLevel nextDim = level.getServer().getLevel(nextDimKey);

            if (nextDim != null && !player.isPassenger()) {
                player.changeDimension(new DimensionTransition(nextDim, player, DimensionTransition.PLACE_PORTAL_TICKET));
            }
        }
            
        return InteractionResult.SUCCESS;
    }

}
