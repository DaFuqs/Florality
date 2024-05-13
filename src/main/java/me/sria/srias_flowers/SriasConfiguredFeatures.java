package me.sria.srias_flowers;

import net.minecraft.registry.*;
import net.minecraft.world.gen.feature.*;

public class SriasConfiguredFeatures {
	
	public static RegistryKey<ConfiguredFeature<?, ?>> of(String id) {
		return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, SriasFlowers.id(id));
	}
	
}
