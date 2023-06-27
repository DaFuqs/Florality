package me.sria.srias_flowers;

import me.sria.srias_flowers.blocks.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.*;
import net.fabricmc.fabric.api.item.v1.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.fabricmc.fabric.api.registry.*;
import net.minecraft.block.*;
import net.minecraft.client.render.*;
import net.minecraft.entity.effect.*;
import net.minecraft.item.*;
import net.minecraft.util.registry.*;

public class SriasBlocks {
	
	private static final AbstractBlock.Settings FLOWER_SETTINGS = FabricBlockSettings.copyOf(Blocks.POPPY);
	public static final Block COLUMBINE = new FlowerBlock(StatusEffects.SPEED, 100, FLOWER_SETTINGS);
	public static final Block SUNRISE_DAISY = new FlowerBlock(StatusEffects.SPEED, 100, FLOWER_SETTINGS);
	public static final Block BIRD_OF_PARADISE = new TallFlowerBlock(FLOWER_SETTINGS);
	public static final Block HIMALAYAN_POPPY = new FlowerBlock(StatusEffects.SPEED, 100, FLOWER_SETTINGS);
	public static final Block BLUE_HYDRANGEA = new FlowerBlock(StatusEffects.SPEED, 100, FLOWER_SETTINGS);
	public static final Block PINK_HYDRANGEA = new FlowerBlock(StatusEffects.SPEED, 100, FLOWER_SETTINGS);
	public static final Block PURPLE_HYDRANGEA = new FlowerBlock(StatusEffects.SPEED, 100, FLOWER_SETTINGS);
	public static final Block SEEDING_DANDELION = new FlowerBlock(StatusEffects.SPEED, 100, FLOWER_SETTINGS);
	public static final Block FLEABANE = new FlowerBlock(StatusEffects.SPEED, 100, FLOWER_SETTINGS);
	public static final Block DUNE_GRASS = new DuneGrassBlock(FabricBlockSettings.copyOf(Blocks.GRASS));
	public static final Block ALOE_VERA = new AloeVeraBlock(FLOWER_SETTINGS);
	public static final Block OBAMA_PLANT = new FlowerBlock(StatusEffects.SPEED, 100, FLOWER_SETTINGS);
	
	private static final AbstractBlock.Settings POTTED_PLANT_SETTINGS = FabricBlockSettings.copyOf(Blocks.POTTED_POPPY);
	public static final Block POTTED_COLUMBINE = new FlowerPotBlock(COLUMBINE, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_SUNRISE_DAISY = new FlowerPotBlock(SUNRISE_DAISY, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_HIMALAYAN_POPPY = new FlowerPotBlock(HIMALAYAN_POPPY, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_BLUE_HYDRANGEA = new FlowerPotBlock(BLUE_HYDRANGEA, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_PINK_HYDRANGEA = new FlowerPotBlock(PINK_HYDRANGEA, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_PURPLE_HYDRANGEA = new FlowerPotBlock(PURPLE_HYDRANGEA, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_SEEDING_DANDELION = new FlowerPotBlock(SEEDING_DANDELION, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_FLEABANE = new FlowerPotBlock(FLEABANE, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_ALOE_VERA = new FlowerPotBlock(ALOE_VERA, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_OBAMA_PLANT = new FlowerPotBlock(OBAMA_PLANT, POTTED_PLANT_SETTINGS);
	
	public static void register() {
		registerBlockWithBlockItem("columbine", COLUMBINE);
		registerBlockWithBlockItem("sunrise_daisy", SUNRISE_DAISY);
		registerBlockWithBlockItem("bird_of_paradise", BIRD_OF_PARADISE);
		registerBlockWithBlockItem("himalayan_poppy", HIMALAYAN_POPPY);
		registerBlockWithBlockItem("blue_hydrangea", BLUE_HYDRANGEA);
		registerBlockWithBlockItem("pink_hydrangea", PINK_HYDRANGEA);
		registerBlockWithBlockItem("purple_hydrangea", PURPLE_HYDRANGEA);
		registerBlockWithBlockItem("seeding_dandelion", SEEDING_DANDELION);
		registerBlockWithBlockItem("fleabane", FLEABANE);
		registerBlockWithBlockItem("dune_grass", DUNE_GRASS);
		registerBlockWithBlockItem("aloe_vera", ALOE_VERA);
		registerBlockWithBlockItem("obama_plant", OBAMA_PLANT);
		
		registerBlock("potted_columbine", POTTED_COLUMBINE);
		registerBlock("potted_sunrise_daisy", POTTED_SUNRISE_DAISY);
		registerBlock("potted_himalayan_poppy", POTTED_HIMALAYAN_POPPY);
		registerBlock("potted_blue_hydrangea", POTTED_BLUE_HYDRANGEA);
		registerBlock("potted_pink_hydrangea", POTTED_PINK_HYDRANGEA);
		registerBlock("potted_purple_hydrangea", POTTED_PURPLE_HYDRANGEA);
		registerBlock("potted_seeding_dandelion", POTTED_SEEDING_DANDELION);
		registerBlock("potted_fleabane", POTTED_FLEABANE);
		registerBlock("potted_aloe_vera", POTTED_ALOE_VERA);
		registerBlock("potted_obama_plant", POTTED_OBAMA_PLANT);
	}
	
	static void registerBlockWithBlockItem(String name, Block block) {
		Registry.register(Registry.BLOCK, SriasFlowers.id(name), block);
		Registry.register(Registry.ITEM, SriasFlowers.id(name), new BlockItem(block, new FabricItemSettings().group(SriasItemGroups.ITEM_GROUP)));
	}
	
	static void registerBlock(String name, Block block) {
		Registry.register(Registry.BLOCK, SriasFlowers.id(name), block);
	}
	
	@Environment(EnvType.CLIENT)
	public static void registerClient() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
				COLUMBINE, SUNRISE_DAISY, BIRD_OF_PARADISE, HIMALAYAN_POPPY,
				BLUE_HYDRANGEA, PINK_HYDRANGEA, PURPLE_HYDRANGEA, SEEDING_DANDELION,
				FLEABANE, DUNE_GRASS, ALOE_VERA,
				
				POTTED_COLUMBINE, POTTED_SUNRISE_DAISY, POTTED_HIMALAYAN_POPPY,
				POTTED_BLUE_HYDRANGEA, POTTED_PINK_HYDRANGEA, POTTED_PURPLE_HYDRANGEA, POTTED_SEEDING_DANDELION,
				POTTED_FLEABANE, POTTED_ALOE_VERA
		);
	}
	
	public static void registerFlammableBlocks() {
		FlammableBlockRegistry.getDefaultInstance().add(COLUMBINE, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(SUNRISE_DAISY, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(BIRD_OF_PARADISE, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(HIMALAYAN_POPPY, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(BLUE_HYDRANGEA, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(PINK_HYDRANGEA, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(PURPLE_HYDRANGEA, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(SEEDING_DANDELION, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(FLEABANE, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(DUNE_GRASS, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(ALOE_VERA, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(OBAMA_PLANT, 60, 100);
	}
	
	
}
