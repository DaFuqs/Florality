package de.dafuqs.florality.tree_decorators;

import com.mojang.serialization.*;
import de.dafuqs.florality.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.*;
import net.minecraft.world.gen.treedecorator.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class LeafCarpetTreeDecorator extends TreeDecorator {
	
	public static final MapCodec<LeafCarpetTreeDecorator> CODEC = BlockStateProvider.TYPE_CODEC.fieldOf("block_provider").xmap(LeafCarpetTreeDecorator::new, (decorator) -> decorator.provider);
	private final BlockStateProvider provider;
	private final static int MAX_DEPTH = 10;
	
	public LeafCarpetTreeDecorator(BlockStateProvider provider) {
		this.provider = provider;
	}
	
	protected TreeDecoratorType<?> getType() {
		return FloralityTreeDecorators.LEAF_CARPET_DECORATOR;
	}
	
	public void generate(TreeDecorator.@NotNull Generator generator) {
		Random random = generator.getRandom();
		
		List<BlockPos> leavesPositions = generator.getLeavesPositions();
		if (leavesPositions.isEmpty()) {
			return;
		}
		
		BlockPos.Mutable mutablePos = new BlockPos.Mutable();
		for (BlockPos currentPos : leavesPositions) {
			int i = 1;
			do {
				mutablePos.set(currentPos.getX(), currentPos.getY() - i, currentPos.getZ());
				if (Feature.isSoil(generator.getWorld(), mutablePos)) {
					mutablePos.move(Direction.UP);
					generator.replace(mutablePos, this.provider.get(random, mutablePos));
					break;
				}
				i++;
			} while (i < MAX_DEPTH && generator.getWorld().testBlockState(mutablePos, AbstractBlock.AbstractBlockState::isAir));
		}
	}
	
}