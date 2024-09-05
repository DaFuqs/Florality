package de.dafuqs.flower_finesse.client;

import de.dafuqs.flower_finesse.*;
import net.fabricmc.api.*;

public class FlowerFinesseClient implements ClientModInitializer {
	
	@Override
	public void onInitializeClient() {
		FFBlocks.registerClient();
	}
	
}
