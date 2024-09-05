package de.dafuqs.florality;

import com.mojang.serialization.*;
import de.dafuqs.florality.tree_decorators.*;
import net.minecraft.registry.*;
import net.minecraft.world.gen.treedecorator.*;

public class FloralityTreeDecorators {
	
	public static final TreeDecoratorType<LeafCarpetTreeDecorator> LEAF_CARPET_DECORATOR = register("leaf_carpet", LeafCarpetTreeDecorator.CODEC);
	public static final TreeDecoratorType<HoneysuckleTreeDecorator> HONEYSUCKLE_DECORATOR = register("honeysuckle", HoneysuckleTreeDecorator.CODEC);
	
	public static void register() {
	
	}
	
	private static <P extends TreeDecorator> TreeDecoratorType<P> register(String id, MapCodec<P> codec) {
		return Registry.register(Registries.TREE_DECORATOR_TYPE, Florality.id(id), new TreeDecoratorType<P>(codec));
	}
	
}
