package me.sria.srias_flowers.client;

import me.sria.srias_flowers.*;
import net.fabricmc.api.*;

public class SriasFlowersClient implements ClientModInitializer {
	
	@Override
	public void onInitializeClient() {
		SriasBlocks.registerClient();
	}
	
}
