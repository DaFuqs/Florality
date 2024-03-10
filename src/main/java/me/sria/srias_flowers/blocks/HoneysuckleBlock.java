package me.sria.srias_flowers.blocks;

import com.mojang.serialization.*;
import me.sria.srias_flowers.*;
import net.minecraft.block.*;
import net.minecraft.fluid.*;
import net.minecraft.item.*;
import net.minecraft.server.world.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.world.*;

public class HoneysuckleBlock extends MultifaceGrowthBlock implements Fertilizable, Waterloggable {
	
	public static final MapCodec<HoneysuckleBlock> CODEC = createCodec(HoneysuckleBlock::new);
	
	private static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
	private final LichenGrower grower = new LichenGrower(this);
	
	public HoneysuckleBlock(AbstractBlock.Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false));
	}
	
	@Override
	protected MapCodec<? extends MultifaceGrowthBlock> getCodec() {
		return CODEC;
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(WATERLOGGED);
	}
	
	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (state.get(WATERLOGGED)) {
			world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}
		
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}
	
	@Override
	public boolean canReplace(BlockState state, ItemPlacementContext context) {
		return !context.getStack().isOf(SriasBlocks.HONEYSUCKLE.asItem()) || super.canReplace(state, context);
	}
	
	@Override
	public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
		return Direction.stream().anyMatch((direction) -> this.grower.canGrow(state, world, pos, direction.getOpposite()));
	}
	
	@Override
	public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
		return true;
	}
	
	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
		this.grower.grow(state, world, pos, random);
	}
	
	@Override
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}
	
	@Override
	public boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
		return state.getFluidState().isEmpty();
	}
	
	@Override
	public LichenGrower getGrower() {
		return this.grower;
	}
	
}
