package de.dafuqs.flower_finesse;

import com.mojang.serialization.*;
import de.dafuqs.flower_finesse.tree_decorators.*;
import net.minecraft.registry.*;
import net.minecraft.world.gen.treedecorator.*;

public class FFTreeDecorators {
	
	public static final TreeDecoratorType<LeafCarpetTreeDecorator> LEAF_CARPET_DECORATOR = register("leaf_carpet", LeafCarpetTreeDecorator.CODEC);
	public static final TreeDecoratorType<HoneysuckleTreeDecorator> HONEYSUCKLE_DECORATOR = register("honeysuckle", HoneysuckleTreeDecorator.CODEC);
	
	public static void register() {
	
	}
	
	private static <P extends TreeDecorator> TreeDecoratorType<P> register(String id, MapCodec<P> codec) {
		return Registry.register(Registries.TREE_DECORATOR_TYPE, FlowerFinesse.id(id), new TreeDecoratorType<P>(codec));
	}
	
}
