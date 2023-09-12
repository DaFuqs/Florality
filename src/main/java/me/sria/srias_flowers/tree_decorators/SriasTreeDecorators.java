package me.sria.srias_flowers.tree_decorators;

import com.mojang.serialization.*;
import me.sria.srias_flowers.*;
import net.minecraft.registry.*;
import net.minecraft.world.gen.treedecorator.*;

public class SriasTreeDecorators {
	
	static TreeDecoratorType<LeafCarpetTreeDecorator> LEAF_CARPET_DECORATOR;
	static TreeDecoratorType<LeafCarpetTreeDecorator> HONEYSUCKLE_DECORATOR;
	
	public static void register() {
		register("leaf_carpet", LeafCarpetTreeDecorator.CODEC);
		register("honeysuckle", HoneysuckleTreeDecorator.CODEC);
	}
	
	private static <P extends TreeDecorator> TreeDecoratorType<P> register(String id, Codec<P> codec) {
		return Registry.register(Registries.TREE_DECORATOR_TYPE, SriasFlowers.id(id), new TreeDecoratorType(codec));
	}
	
}
