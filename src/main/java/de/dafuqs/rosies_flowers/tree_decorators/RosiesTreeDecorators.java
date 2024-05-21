package de.dafuqs.rosies_flowers.tree_decorators;

import com.mojang.serialization.*;
import de.dafuqs.rosies_flowers.*;
import net.minecraft.registry.*;
import net.minecraft.world.gen.treedecorator.*;

public class RosiesTreeDecorators {
	
	static TreeDecoratorType<LeafCarpetTreeDecorator> LEAF_CARPET_DECORATOR = register("leaf_carpet", LeafCarpetTreeDecorator.CODEC);
	static TreeDecoratorType<HoneysuckleTreeDecorator> HONEYSUCKLE_DECORATOR = register("honeysuckle", HoneysuckleTreeDecorator.CODEC);
	
	public static void register() {

	}
	
	private static <P extends TreeDecorator> TreeDecoratorType<P> register(String id, Codec<P> codec) {
		return Registry.register(Registries.TREE_DECORATOR_TYPE, RosiesFlowers.id(id), new TreeDecoratorType<>(codec));
	}
	
}
