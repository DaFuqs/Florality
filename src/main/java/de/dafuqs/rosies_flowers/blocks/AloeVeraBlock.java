package de.dafuqs.rosies_flowers.blocks;

import com.mojang.serialization.*;
import net.minecraft.block.*;
import net.minecraft.registry.tag.*;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;

public class AloeVeraBlock extends PlantBlock {
	
	protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 14.0, 12.0);
	public static final MapCodec<AloeVeraBlock> CODEC = createCodec(AloeVeraBlock::new);
	
	public AloeVeraBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	protected MapCodec<? extends PlantBlock> getCodec() {
		return CODEC;
	}
	
	@Override
	protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
		return floor.isIn(BlockTags.SAND) || floor.isOf(Blocks.GRAVEL);
	}
	
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		Vec3d vec3d = state.getModelOffset(world, pos);
		return SHAPE.offset(vec3d.x, vec3d.y, vec3d.z);
	}
	
}
