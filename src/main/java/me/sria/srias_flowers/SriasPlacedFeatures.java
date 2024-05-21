package me.sria.srias_flowers;

import net.fabricmc.fabric.api.biome.v1.*;
import net.fabricmc.fabric.api.tag.convention.v1.*;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.*;

public class SriasPlacedFeatures {
	
	public static RegistryKey<PlacedFeature> SUNRISE_DAISY = of("sunrise_daisy");
	public static RegistryKey<PlacedFeature> HIMALAYAN_POPPY = of("himalayan_poppy");
	public static RegistryKey<PlacedFeature> BLUE_HYDRANGEA = of("blue_hydrangea");
	public static RegistryKey<PlacedFeature> FLEABANE = of("fleabane");
	public static RegistryKey<PlacedFeature> DUNE_GRASS = of("dune_grass");
	public static RegistryKey<PlacedFeature> ALOE_VERA = of("aloe_vera");
	public static RegistryKey<PlacedFeature> CHACONIA = of("chaconia");
	public static RegistryKey<PlacedFeature> WHITE_PERIWINKLE = of("white_periwinkle");
	public static RegistryKey<PlacedFeature> PURPLE_PERIWINKLE = of("purple_periwinkle");
	public static RegistryKey<PlacedFeature> SCARLET_FLAX_COMMON = of("scarlet_flax_common");
	public static RegistryKey<PlacedFeature> SCARLET_FLAX_RARE = of("scarlet_flax_rare");
	public static RegistryKey<PlacedFeature> PINK_TULIP = of("pink_tulip");
	public static RegistryKey<PlacedFeature> FELICIA_DAISY = of("felicia_daisy");
	public static RegistryKey<PlacedFeature> FLAME_ORCHID = of("flame_orchid");
	public static RegistryKey<PlacedFeature> GERBERA_DAISY = of("gerbera_daisy");
	public static RegistryKey<PlacedFeature> ORANGE_ZINNIA = of("orange_zinnia");
	public static RegistryKey<PlacedFeature> PINK_ALLIUM = of("pink_allium");
	public static RegistryKey<PlacedFeature> PINK_ORCHID = of("pink_orchid");
	public static RegistryKey<PlacedFeature> PINK_ORCHID_JUNGLE = of("pink_orchid_jungle");
	public static RegistryKey<PlacedFeature> PINK_ZINNIA = of("pink_zinnia");
	public static RegistryKey<PlacedFeature> PURPLE_HIBISCUS = of("purple_hibiscus");
	public static RegistryKey<PlacedFeature> MOUNTAIN_LAUREL = of("mountain_laurel");
	public static RegistryKey<PlacedFeature> LARKSPUR = of("larkspur");
	public static RegistryKey<PlacedFeature> FUCHSIA = of("fuchsia");
	public static RegistryKey<PlacedFeature> BLUE_SAGE = of("blue_sage");
	public static RegistryKey<PlacedFeature> BUTTERFLY_WEED = of("butterfly_weed");
	
	public static RegistryKey<PlacedFeature> of(String id) {
		return RegistryKey.of(RegistryKeys.PLACED_FEATURE, SriasFlowers.id(id));
	}
	
	static void addBiomeModifications() {
		// Sierras
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_HILL), GenerationStep.Feature.VEGETAL_DECORATION, HIMALAYAN_POPPY);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.PLAINS), GenerationStep.Feature.VEGETAL_DECORATION, FLEABANE);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.DESERT), GenerationStep.Feature.VEGETAL_DECORATION, DUNE_GRASS);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.DESERT), GenerationStep.Feature.VEGETAL_DECORATION, ALOE_VERA);
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE), GenerationStep.Feature.VEGETAL_DECORATION, CHACONIA);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.PLAINS), GenerationStep.Feature.VEGETAL_DECORATION, SCARLET_FLAX_RARE);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.DARK_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, PINK_ALLIUM);
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE), GenerationStep.Feature.VEGETAL_DECORATION, FLAME_ORCHID);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.BIRCH_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, PINK_ORCHID);
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE), GenerationStep.Feature.VEGETAL_DECORATION, PINK_ORCHID_JUNGLE);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.PLAINS), GenerationStep.Feature.VEGETAL_DECORATION, FELICIA_DAISY);
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, FELICIA_DAISY);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.PLAINS), GenerationStep.Feature.VEGETAL_DECORATION, GERBERA_DAISY);
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE), GenerationStep.Feature.VEGETAL_DECORATION, PINK_ZINNIA);
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE), GenerationStep.Feature.VEGETAL_DECORATION, ORANGE_ZINNIA);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.BIRCH_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, PINK_ZINNIA);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.BIRCH_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, ORANGE_ZINNIA);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.FLOWER_FORESTS), GenerationStep.Feature.VEGETAL_DECORATION, FLEABANE);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.FLOWER_FORESTS), GenerationStep.Feature.VEGETAL_DECORATION, BLUE_HYDRANGEA);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.FLOWER_FORESTS), GenerationStep.Feature.VEGETAL_DECORATION, SCARLET_FLAX_COMMON);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.FLOWER_FORESTS), GenerationStep.Feature.VEGETAL_DECORATION, WHITE_PERIWINKLE);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.FLOWER_FORESTS), GenerationStep.Feature.VEGETAL_DECORATION, PURPLE_PERIWINKLE);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.BIRCH_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, WHITE_PERIWINKLE);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.BIRCH_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, PURPLE_PERIWINKLE);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.BIRCH_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, SUNRISE_DAISY);
		
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE), GenerationStep.Feature.VEGETAL_DECORATION, PURPLE_HIBISCUS);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.DARK_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, PURPLE_HIBISCUS);
		
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.BIRCH_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, MOUNTAIN_LAUREL);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.FLOWER_FORESTS), GenerationStep.Feature.VEGETAL_DECORATION, MOUNTAIN_LAUREL);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, BiomeKeys.OLD_GROWTH_PINE_TAIGA), GenerationStep.Feature.VEGETAL_DECORATION, MOUNTAIN_LAUREL);
		
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FOREST), GenerationStep.Feature.VEGETAL_DECORATION, LARKSPUR);
		
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE), GenerationStep.Feature.VEGETAL_DECORATION, FUCHSIA);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.FLOWER_FORESTS), GenerationStep.Feature.VEGETAL_DECORATION, FUCHSIA);
		
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, BLUE_SAGE);
		
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_JUNGLE), GenerationStep.Feature.VEGETAL_DECORATION, BUTTERFLY_WEED);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.BIRCH_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, BUTTERFLY_WEED);
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.FLOWER_FORESTS), GenerationStep.Feature.VEGETAL_DECORATION, BUTTERFLY_WEED);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.WINDSWEPT_SAVANNA), GenerationStep.Feature.VEGETAL_DECORATION, BUTTERFLY_WEED);
		
		// More vanilla flowers
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.BIRCH_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, PINK_TULIP);
	}
	
}
