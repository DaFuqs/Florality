package me.sria.srias_flowers;

import me.sria.srias_flowers.tree_decorators.*;
import net.fabricmc.api.*;
import net.minecraft.util.*;

public class SriasFlowers implements ModInitializer {
	
	public static final String MOD_ID = "srias_flowers";
	
	public static Identifier id(String name) {
		return new Identifier(MOD_ID, name);
	}
	
	
	@Override
	public void onInitialize() {
		SriasBlocks.register();
		SriasItems.register();
		SriasTreeDecorators.register();
		SriasPlacedFeatures.addBiomeModifications();
		SriasItemGroups.register();
	}
}
