package me.sria.srias_flowers;

import net.minecraft.item.*;
import net.minecraft.util.registry.*;

public class SriasItems {

	public static void register() {
	
	}
	
	static void registerItem(String name, Item item) {
		Registry.register(Registry.ITEM, SriasFlowers.id(name), item);
	}
	
}
