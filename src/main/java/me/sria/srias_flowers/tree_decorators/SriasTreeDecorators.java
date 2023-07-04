package me.sria.srias_flowers.tree_decorators;

import me.sria.srias_flowers.mixin.*;
import net.minecraft.world.gen.treedecorator.*;

public class SriasTreeDecorators {
	
	static TreeDecoratorType<LeafCarpetTreeDecorator> LEAF_CARPET_DECORATOR;
	
	public static void register() {
		LEAF_CARPET_DECORATOR = TreeDecoratorTypeMixin.invokeRegister("srias_leaf_carpet", LeafCarpetTreeDecorator.CODEC);
	}
	
}
