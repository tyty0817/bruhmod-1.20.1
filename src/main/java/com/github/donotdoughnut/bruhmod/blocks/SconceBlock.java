package com.github.donotdoughnut.bruhmod.blocks;

import java.util.Random;


public class SconceBlock /* extends AbstractTorchBlock */ {
/*
	protected static final MapCodec<SimpleParticleType> PARTICLE_TYPE_CODEC;
	public static final MapCodec<SconceBlock> CODEC;
	protected final SimpleParticleType particle;
//
	public SconceBlock(SimpleParticleType particle, AbstractBlock.Settings settings) {
		super(settings);
		this.particle = particle;
	}
//
	@Override
	protected MapCodec<? extends AbstractTorchBlock> getCodec() {
		return CODEC;
	}
//
//	public static final VoxelShape BOUNDING_SHAPE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);
//
//	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
//		return BOUNDING_SHAPE;
//	}
//
//	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState,
//			WorldAccess world, BlockPos pos, BlockPos neighborPos) {
//		return direction == Direction.DOWN && !this.canPlaceAt(state, world, pos) ? Blocks.AIR.getDefaultState()
//				: super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
//	}
//
//	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
//		return sideCoversSmallSquare(world, pos.down(), Direction.UP);
//	}
//
//	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
//		double d = (double) pos.getX() + 0.5D;
//		double e = (double) pos.getY() + 0.7D;
//		double f = (double) pos.getZ() + 0.5D;
//		world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0D, 0.0D, 0.0D);
//		world.addParticle(ParticleTypes.FLAME, d, e, f, 0.0D, 0.0D, 0.0D);
//	}
//
	static {
		PARTICLE_TYPE_CODEC = Registries.PARTICLE_TYPE.getCodec().comapFlatMap((particleType) -> {
			DataResult var10000;
			if (particleType instanceof SimpleParticleType simpleParticleType) {
				var10000 = DataResult.success(simpleParticleType);
			} else {
				var10000 = DataResult.error(() -> {
					return "Not a SimpleParticleType: " + String.valueOf(particleType);
				});
			}

			return var10000;
		}, (particleType) -> {
			return (ParticleType<?>) particleType;
		}).fieldOf("particle_options");
		CODEC = RecordCodecBuilder.mapCodec((instance) -> {
			return instance.group(PARTICLE_TYPE_CODEC.forGetter((block) -> {
				return block.particle;
			}), createSettingsCodec()).apply(instance, SconceBlock::new);
		});
	}
*/
}
