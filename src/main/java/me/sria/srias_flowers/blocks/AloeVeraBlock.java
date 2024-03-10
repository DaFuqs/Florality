package me.sria.srias_flowers.blocks;

import com.mojang.serialization.*;
import net.minecraft.block.*;
import net.minecraft.registry.tag.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class AloeVeraBlock extends PlantBlock {
	
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
	
}
