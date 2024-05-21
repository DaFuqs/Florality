package de.dafuqs.rosies_flowers;

import de.dafuqs.rosies_flowers.tree_decorators.*;
import net.fabricmc.api.*;
import net.minecraft.util.*;

public class RosiesFlowers implements ModInitializer {
	
	public static final String MOD_ID = "rosies_flowers";
	
	public static Identifier id(String name) {
		return new Identifier(MOD_ID, name);
	}
	
	@Override
	public void onInitialize() {
		RosiesBlocks.register();
		RosiesItems.register();
		RosiesTreeDecorators.register();
		RosiesPlacedFeatures.addBiomeModifications();
		RosiesItemGroups.register();
	}
	
}
