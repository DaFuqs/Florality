package de.dafuqs.rosies_flowers.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.effect.*;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;

public class PeriwinkleBlock extends FlowerBlock {
	
	protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);
	
	public PeriwinkleBlock(StatusEffect stewEffect, int duration, Settings settings) {
		super(stewEffect, duration, settings);
	}
	
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}
	
}
