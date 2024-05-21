package de.dafuqs.rosies_flowers.tree_decorators;

import com.mojang.serialization.*;
import de.dafuqs.rosies_flowers.blocks.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.world.gen.stateprovider.*;
import net.minecraft.world.gen.treedecorator.*;
import org.jetbrains.annotations.*;

public class HoneysuckleTreeDecorator extends TreeDecorator {
	
	public static final Codec<HoneysuckleTreeDecorator> CODEC = BlockStateProvider.TYPE_CODEC.fieldOf("block_provider").xmap(HoneysuckleTreeDecorator::new, (decorator) -> decorator.provider).codec();
	private final BlockStateProvider provider;
	
	public HoneysuckleTreeDecorator(BlockStateProvider provider) {
		this.provider = provider;
	}
	
	protected TreeDecoratorType<?> getType() {
		return RosiesTreeDecorators.HONEYSUCKLE_DECORATOR;
	}
	
	public void generate(@NotNull Generator generator) {
		Random random = generator.getRandom();
		
		if(random.nextBoolean()) {
			return;
		}
		
		BlockPos.Mutable mutablePos = new BlockPos.Mutable();
		for (BlockPos currentPos : generator.getLogPositions()) {
			if(random.nextBoolean()) {
				continue;
			}
			
			for (Direction direction : Direction.Type.HORIZONTAL.getShuffled(random)) {
				mutablePos.set(currentPos, direction);
				if (generator.getWorld().testBlockState(mutablePos, AbstractBlock.AbstractBlockState::isAir)) {
					generator.replace(mutablePos, this.provider.get(random, mutablePos).with(HoneysuckleBlock.getProperty(direction.getOpposite()), true));
					break;
				}
			}
		}
	}
	
}