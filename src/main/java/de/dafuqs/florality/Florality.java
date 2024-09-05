package de.dafuqs.florality;

import net.fabricmc.api.*;
import net.minecraft.util.*;

public class Florality implements ModInitializer {
	
	public static final String MOD_ID = "florality";
	
	public static Identifier id(String name) {
		return Identifier.of(MOD_ID, name);
	}
	
	@Override
	public void onInitialize() {
		FloralityBlocks.register();
		FloralityItems.register();
		FloralityTreeDecorators.register();
		FloralityPlacedFeatures.addBiomeModifications();
		FloralityItemGroups.register();
	}
	
}
