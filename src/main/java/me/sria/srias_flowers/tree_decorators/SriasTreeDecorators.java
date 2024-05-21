package me.sria.srias_flowers.tree_decorators;

import com.mojang.serialization.*;
import me.sria.srias_flowers.*;
import net.minecraft.registry.*;
import net.minecraft.world.gen.treedecorator.*;

public class SriasTreeDecorators {
	
	static TreeDecoratorType<LeafCarpetTreeDecorator> LEAF_CARPET_DECORATOR = register("leaf_carpet", LeafCarpetTreeDecorator.CODEC);
	static TreeDecoratorType<HoneysuckleTreeDecorator> HONEYSUCKLE_DECORATOR = register("honeysuckle", HoneysuckleTreeDecorator.CODEC);
	
	public static void register() {
	
	}
	
	private static <P extends TreeDecorator> TreeDecoratorType<P> register(String id, MapCodec<P> codec) {
		return Registry.register(Registries.TREE_DECORATOR_TYPE, SriasFlowers.id(id), new TreeDecoratorType<P>(codec));
	}
	
}
