package de.dafuqs.florality.client;

import de.dafuqs.florality.*;
import net.fabricmc.api.*;

public class FloralityClient implements ClientModInitializer {
	
	@Override
	public void onInitializeClient() {
		FloralityBlocks.registerClient();
	}
	
}
