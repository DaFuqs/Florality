package me.sria.srias_flowers;

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
		SriasBlocks.registerFlammableBlocks();
		SriasPlacedFeatures.addBiomeModifications();
		SriasItemGroups.register();
	}
}
