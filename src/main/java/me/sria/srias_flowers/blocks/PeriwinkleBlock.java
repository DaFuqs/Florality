package me.sria.srias_flowers.blocks;

import com.mojang.serialization.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;

public class PeriwinkleBlock extends PlantBlock {
	
	public static final MapCodec<PeriwinkleBlock> CODEC = createCodec(PeriwinkleBlock::new);
	
	protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);
	
	public PeriwinkleBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	protected MapCodec<? extends PlantBlock> getCodec() {
		return CODEC;
	}
	
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}
	
}
