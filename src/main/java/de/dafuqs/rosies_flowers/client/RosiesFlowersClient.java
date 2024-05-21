package de.dafuqs.rosies_flowers.client;

import de.dafuqs.rosies_flowers.*;
import net.fabricmc.api.*;

public class RosiesFlowersClient implements ClientModInitializer {
	
	@Override
	public void onInitializeClient() {
		RosiesBlocks.registerClient();
	}
	
}
