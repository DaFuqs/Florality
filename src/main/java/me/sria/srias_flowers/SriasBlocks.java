package me.sria.srias_flowers;

import me.sria.srias_flowers.blocks.*;
import me.sria.srias_flowers.items.*;
import me.sria.srias_flowers.tree_decorators.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.*;
import net.fabricmc.fabric.api.item.v1.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.fabricmc.fabric.api.registry.*;
import net.minecraft.block.*;
import net.minecraft.block.piston.*;
import net.minecraft.client.render.*;
import net.minecraft.entity.effect.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.sound.*;

import java.util.*;

public class SriasBlocks {
	
	public static List<WoodSet> WOOD_SETS = new ArrayList<>() {{
		add(new WoodSet("dogwood", MapColor.WHITE_GRAY));
		add(new WoodSet("blue_wisteria", MapColor.WATER_BLUE));
		add(new WoodSet("purple_wisteria", MapColor.TERRACOTTA_PURPLE));
	}};
	
	public static class WoodSet {
		
		private final String name;
		private final MapColor mapColor;
		
		public Block SAPLING;
		public Block LEAVES;
		public Block LEAF_CARPET;
		
		public WoodSet(String name, MapColor mapColor) {
			this.name = name;
			this.mapColor = mapColor;
		}
		
		public void register() {
			this.LEAVES = registerBlockWithBlockItem(name + "_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).sounds(BlockSoundGroup.AZALEA_LEAVES).mapColor(mapColor)));
			this.LEAF_CARPET = registerBlockWithBlockItem(name + "_carpet", new LeafCarpetBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CARPET).sounds(BlockSoundGroup.AZALEA_LEAVES).mapColor(mapColor).nonOpaque()));
			
			SaplingGenerator saplingGenerator = new SaplingGenerator("srias_" + name, Optional.empty(), Optional.of(RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, SriasFlowers.id(name + "_tree"))), Optional.empty());
			this.SAPLING = registerBlockWithBlockItem(name + "_sapling", new SaplingBlock(saplingGenerator, FabricBlockSettings.copyOf(Blocks.OAK_SAPLING).mapColor(mapColor)));
		}
		
		public void registerClient() {
			BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), LEAF_CARPET, SAPLING);
		}
	}
	
	private static final AbstractBlock.Settings FLOWER_SETTINGS = FabricBlockSettings.copyOf(Blocks.POPPY);
	public static final Block SUNRISE_DAISY = new FlowerBlock(StatusEffects.SPEED, 100, FLOWER_SETTINGS);
	public static final Block HIMALAYAN_POPPY = new FlowerBlock(StatusEffects.SPEED, 100, FLOWER_SETTINGS);
	public static final Block BLUE_HYDRANGEA = new FlowerBlock(StatusEffects.SPEED, 100, FLOWER_SETTINGS);
	public static final Block SEEDING_DANDELION = new FlowerBlock(StatusEffects.SPEED, 100, FLOWER_SETTINGS);
	public static final Block FLEABANE = new FlowerBlock(StatusEffects.SPEED, 100, FLOWER_SETTINGS);
	public static final Block DUNE_GRASS = new DuneGrassBlock(FabricBlockSettings.copyOf(Blocks.SHORT_GRASS));
	public static final Block ALOE_VERA = new AloeVeraBlock(FLOWER_SETTINGS);
	public static final Block OBAMA_PLANT = new FlowerBlock(StatusEffects.SPEED, 100, FLOWER_SETTINGS);
	public static final HoneysuckleBlock HONEYSUCKLE = new HoneysuckleBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).replaceable().noCollision().strength(0.2F).sounds(BlockSoundGroup.GLOW_LICHEN).burnable().pistonBehavior(PistonBehavior.DESTROY));
	
	private static final AbstractBlock.Settings POTTED_PLANT_SETTINGS = FabricBlockSettings.copyOf(Blocks.POTTED_POPPY);
	public static final Block POTTED_SUNRISE_DAISY = new FlowerPotBlock(SUNRISE_DAISY, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_HIMALAYAN_POPPY = new FlowerPotBlock(HIMALAYAN_POPPY, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_BLUE_HYDRANGEA = new FlowerPotBlock(BLUE_HYDRANGEA, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_SEEDING_DANDELION = new FlowerPotBlock(SEEDING_DANDELION, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_FLEABANE = new FlowerPotBlock(FLEABANE, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_ALOE_VERA = new FlowerPotBlock(ALOE_VERA, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_OBAMA_PLANT = new FlowerPotBlock(OBAMA_PLANT, POTTED_PLANT_SETTINGS);
	
	public static void register() {
		SriasTreeDecorators.register();
		
		for(WoodSet woodSet : WOOD_SETS) {
			woodSet.register();
		}
		
		registerBlockWithBlockItem("sunrise_daisy", SUNRISE_DAISY);
		registerBlockWithBlockItem("himalayan_poppy", HIMALAYAN_POPPY);
		registerBlockWithBlockItem("blue_hydrangea", BLUE_HYDRANGEA);
		registerBlockWithBlockItem("seeding_dandelion", SEEDING_DANDELION, new SeedingDandelionItem(SEEDING_DANDELION, new FabricItemSettings()));
		registerBlockWithBlockItem("fleabane", FLEABANE);
		registerBlockWithBlockItem("dune_grass", DUNE_GRASS);
		registerBlockWithBlockItem("aloe_vera", ALOE_VERA);
		registerBlockWithBlockItem("obama_plant", OBAMA_PLANT);
		registerBlockWithBlockItem("honeysuckle", HONEYSUCKLE);
		
		registerBlock("potted_sunrise_daisy", POTTED_SUNRISE_DAISY);
		registerBlock("potted_himalayan_poppy", POTTED_HIMALAYAN_POPPY);
		registerBlock("potted_blue_hydrangea", POTTED_BLUE_HYDRANGEA);
		registerBlock("potted_seeding_dandelion", POTTED_SEEDING_DANDELION);
		registerBlock("potted_fleabane", POTTED_FLEABANE);
		registerBlock("potted_aloe_vera", POTTED_ALOE_VERA);
		registerBlock("potted_obama_plant", POTTED_OBAMA_PLANT);
	}
	
	static Block registerBlockWithBlockItem(String name, Block block) {
		Block b = Registry.register(Registries.BLOCK, SriasFlowers.id(name), block);
		Registry.register(Registries.ITEM, SriasFlowers.id(name), new BlockItem(block, new FabricItemSettings()));
		return b;
	}
	
	static Block registerBlockWithBlockItem(String name, Block block, BlockItem blockItem) {
		Block b = Registry.register(Registries.BLOCK, SriasFlowers.id(name), block);
		Registry.register(Registries.ITEM, SriasFlowers.id(name), blockItem);
		return b;
	}
	
	static void registerBlock(String name, Block block) {
		Registry.register(Registries.BLOCK, SriasFlowers.id(name), block);
	}
	
	@Environment(EnvType.CLIENT)
	public static void registerClient() {
		for(WoodSet woodSet : WOOD_SETS) {
			woodSet.registerClient();
		}
		
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
				SUNRISE_DAISY, HIMALAYAN_POPPY,
				BLUE_HYDRANGEA, SEEDING_DANDELION,
				FLEABANE, DUNE_GRASS, ALOE_VERA, HONEYSUCKLE,
				
				POTTED_SUNRISE_DAISY, POTTED_HIMALAYAN_POPPY,
				POTTED_BLUE_HYDRANGEA, POTTED_SEEDING_DANDELION,
				POTTED_FLEABANE, POTTED_ALOE_VERA
		);
	}
	
	public static void registerFlammableBlocks() {
		FlammableBlockRegistry.getDefaultInstance().add(SUNRISE_DAISY, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(HIMALAYAN_POPPY, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(BLUE_HYDRANGEA, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(SEEDING_DANDELION, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(FLEABANE, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(DUNE_GRASS, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(ALOE_VERA, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(OBAMA_PLANT, 60, 100);
	}
	
	
}
