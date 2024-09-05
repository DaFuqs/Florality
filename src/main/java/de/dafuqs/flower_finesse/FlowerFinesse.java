package de.dafuqs.flower_finesse;

import net.fabricmc.api.*;
import net.minecraft.util.*;

public class FlowerFinesse implements ModInitializer {
	
	public static final String MOD_ID = "flower_finesse";
	
	public static Identifier id(String name) {
		return Identifier.of(MOD_ID, name);
	}
	
	@Override
	public void onInitialize() {
		FFBlocks.register();
		FFItems.register();
		FFTreeDecorators.register();
		FFPlacedFeatures.addBiomeModifications();
		FFItemGroups.register();
	}
	
}
