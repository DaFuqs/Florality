package de.dafuqs.florality;

import net.fabricmc.fabric.api.biome.v1.*;
import net.fabricmc.fabric.api.tag.convention.v2.*;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.*;

public class FloralityPlacedFeatures {
	
	public static RegistryKey<PlacedFeature> CALLA_LILY = of("calla_lily");
	public static RegistryKey<PlacedFeature> FLAME_ORCHID = of("flame_orchid");
	public static RegistryKey<PlacedFeature> PINK_ORCHID = of("pink_orchid");
	public static RegistryKey<PlacedFeature> WHITE_ORCHID = of("white_orchid");
	public static RegistryKey<PlacedFeature> HELLEBORE = of("hellebore");
	public static RegistryKey<PlacedFeature> PETUNIAS = of("petunias");
	public static RegistryKey<PlacedFeature> HYDRANGEA = of("hydrangea");
	public static RegistryKey<PlacedFeature> PURPLE_HYDRANGEA = of("purple_hydrangea");
	public static RegistryKey<PlacedFeature> PINK_SWEETROCKET = of("pink_sweetrocket");
	
	
	public static RegistryKey<PlacedFeature> SUNRISE_DAISY = of("sunrise_daisy");
	public static RegistryKey<PlacedFeature> ALOE_VERA = of("aloe_vera");
	public static RegistryKey<PlacedFeature> BLUE_ORCHID = of("blue_orchid");
	public static RegistryKey<PlacedFeature> FUCHSIA = of("fuchsia");
	public static RegistryKey<PlacedFeature> FUCHSIA_JUNGLE = of("fuchsia_jungle");
	
	public static RegistryKey<PlacedFeature> of(String id) {
		return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Florality.id(id));
	}
	
	static void addBiomeModifications() {
		// Mod Ones
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.IS_DESERT), GenerationStep.Feature.VEGETAL_DECORATION, ALOE_VERA);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.JUNGLE), GenerationStep.Feature.VEGETAL_DECORATION, FLAME_ORCHID);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.JUNGLE), GenerationStep.Feature.VEGETAL_DECORATION, PINK_ORCHID);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.IS_BIRCH_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, SUNRISE_DAISY);
		
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.JUNGLE), GenerationStep.Feature.VEGETAL_DECORATION, FUCHSIA_JUNGLE);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.IS_FLOWER_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, FUCHSIA);
		
		// More vanilla stuff
		//BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.IS_BIRCH_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, PINK_TULIP);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FLOWER_CHERRY);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.JUNGLE), GenerationStep.Feature.VEGETAL_DECORATION, TreePlacedFeatures.MANGROVE_CHECKED);
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE), GenerationStep.Feature.VEGETAL_DECORATION, BLUE_ORCHID);
	}
	
}
