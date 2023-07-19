package me.sria.srias_flowers;

import net.fabricmc.fabric.api.biome.v1.*;
import net.fabricmc.fabric.api.tag.convention.v1.*;
import net.minecraft.tag.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.*;

public class SriasFeatures {
	
	public static RegistryKey<PlacedFeature> SUNRISE_DAISY = RegistryKey.of(Registry.PLACED_FEATURE_KEY, SriasFlowers.id("sunrise_daisy"));
	public static RegistryKey<PlacedFeature> HIMALAYAN_POPPY = RegistryKey.of(Registry.PLACED_FEATURE_KEY, SriasFlowers.id("himalayan_poppy"));
	public static RegistryKey<PlacedFeature> BLUE_HYDRANGEA = RegistryKey.of(Registry.PLACED_FEATURE_KEY, SriasFlowers.id("blue_hydrangea"));
	public static RegistryKey<PlacedFeature> SEEDING_DANDELION = RegistryKey.of(Registry.PLACED_FEATURE_KEY, SriasFlowers.id("seeding_dandelion"));
	public static RegistryKey<PlacedFeature> FLEABANE = RegistryKey.of(Registry.PLACED_FEATURE_KEY, SriasFlowers.id("fleabane"));
	public static RegistryKey<PlacedFeature> DUNE_GRASS = RegistryKey.of(Registry.PLACED_FEATURE_KEY, SriasFlowers.id("dune_grass"));
	public static RegistryKey<PlacedFeature> ALOE_VERA = RegistryKey.of(Registry.PLACED_FEATURE_KEY, SriasFlowers.id("aloe_vera"));
	
	public static RegistryKey<PlacedFeature> DOGWOOD_TREES = RegistryKey.of(Registry.PLACED_FEATURE_KEY, SriasFlowers.id("dogwood_trees"));
	public static RegistryKey<PlacedFeature> BLUE_WISTERIA_TREES = RegistryKey.of(Registry.PLACED_FEATURE_KEY, SriasFlowers.id("blue_wisteria_trees"));
	public static RegistryKey<PlacedFeature> PURPLE_WISTERIA_TREES = RegistryKey.of(Registry.PLACED_FEATURE_KEY, SriasFlowers.id("purple_wisteria_trees"));
	
	static void addBiomeModifications() {
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, SUNRISE_DAISY);
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_HILL), GenerationStep.Feature.VEGETAL_DECORATION, HIMALAYAN_POPPY);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.PLAINS), GenerationStep.Feature.VEGETAL_DECORATION, FLEABANE);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, FLEABANE);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.DESERT), GenerationStep.Feature.VEGETAL_DECORATION, DUNE_GRASS);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, BLUE_HYDRANGEA);
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, SEEDING_DANDELION);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.DESERT), GenerationStep.Feature.VEGETAL_DECORATION, ALOE_VERA);
		
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, DOGWOOD_TREES);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, BLUE_WISTERIA_TREES);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, PURPLE_WISTERIA_TREES);
	}
	
}
