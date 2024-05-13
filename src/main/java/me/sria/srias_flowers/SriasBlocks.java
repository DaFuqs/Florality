package me.sria.srias_flowers;

import me.sria.srias_flowers.blocks.*;
import me.sria.srias_flowers.tree_decorators.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.*;
import net.fabricmc.fabric.api.item.v1.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.fabricmc.fabric.api.object.builder.v1.block.type.*;
import net.fabricmc.fabric.api.registry.*;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.*;
import net.minecraft.client.render.*;
import net.minecraft.entity.effect.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.sound.*;

import java.util.*;

public class SriasBlocks {

	public static List<SaplingSet> SAPLING_SETS = new ArrayList<>() {{
		add(new SaplingSet("dogwood", MapColor.WHITE_GRAY));
		add(new SaplingSet("red_dogwood", MapColor.DULL_PINK));
		add(new SaplingSet("blue_wisteria", MapColor.WATER_BLUE));
		add(new SaplingSet("purple_wisteria", MapColor.TERRACOTTA_PURPLE));
	}};

	
	public static List<WoodSet> WOOD_SETS = new ArrayList<>() {{
		add(new WoodSet("dogwood", MapColor.OAK_TAN, MapColor.GRAY));
	}};

	public static class SaplingSet {
		
		private final String name;
		private final MapColor mapColor;

		public Block sapling;
		public Block leaves;
		public Block leafCarpet;

		public SaplingSet(String name, MapColor mapColor) {
			this.name = name;
			this.mapColor = mapColor;
		}
		
		public void register() {
			this.leaves = registerBlockWithBlockItem(name + "_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).sounds(BlockSoundGroup.AZALEA_LEAVES).mapColor(mapColor)));
			this.leafCarpet = registerBlockWithBlockItem(name + "_carpet", new LeafCarpetBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CARPET).sounds(BlockSoundGroup.AZALEA_LEAVES).mapColor(mapColor).nonOpaque()));
			
			SaplingGenerator saplingGenerator = new SaplingGenerator("srias_" + name, Optional.empty(), Optional.of(RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, SriasFlowers.id(name + "_tree"))), Optional.empty());
			this.sapling = registerBlockWithBlockItem(name + "_sapling", new SaplingBlock(saplingGenerator, FabricBlockSettings.copyOf(Blocks.OAK_SAPLING).mapColor(mapColor)));
			
			FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
			registry.add(leaves, 30, 60);
		}
		
		public void registerClient() {
			BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), leafCarpet, sapling);
		}

		public void addEntries(ItemGroup.Entries entries) {
			entries.add(sapling);
			entries.add(leaves);
			entries.add(leafCarpet);
		}
	}

	public static class WoodSet {

		public static final BlockSetType BLOCK_SET_TYPE = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(SriasFlowers.id("wood"));

		private final String name;
		private final MapColor mapColor;
		private final MapColor topLogMapColor;

		public final WoodType woodType;

		public Block planks;
		public Block log;
		public Block strippedLog;
		public Block wood;
		public Block strippedWood;
		public Block stairs;
		public Block door;
		public Block sign;
		public Block wallSign;
		public Block hangingSign;
		public Block wallHangingSign;
		public Block pressurePlate;
		public Block fence;
		public Block trapdoor;
		public Block fenceGate;
		public Block button;
		public Block slab;

		public WoodSet(String name, MapColor mapColor, MapColor topLogMapColor) {
			this.name = name;
			this.mapColor = mapColor;
			this.topLogMapColor = topLogMapColor;

			this.woodType = new WoodTypeBuilder().register(SriasFlowers.id(name), BLOCK_SET_TYPE);
		}

		public void register() {
			planks = registerBlockWithBlockItem(name + "_planks", new Block(AbstractBlock.Settings.create().mapColor(mapColor).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
			log = registerBlockWithBlockItem(name + "_log", Blocks.createLogBlock(mapColor, topLogMapColor));
			strippedLog = registerBlockWithBlockItem("stripped_" + name + "_log", Blocks.createLogBlock(mapColor, mapColor));
			wood = registerBlockWithBlockItem(name + "_wood", new PillarBlock(AbstractBlock.Settings.create().mapColor(mapColor).instrument(Instrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()));
			strippedWood = registerBlockWithBlockItem("stripped_" + name + "_wood", new PillarBlock(AbstractBlock.Settings.create().mapColor(topLogMapColor).instrument(Instrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()));
			stairs = registerBlockWithBlockItem(name + "_stairs", createStairsBlock(planks));

			Block doorBlock = new DoorBlock(BLOCK_SET_TYPE, AbstractBlock.Settings.create().mapColor(planks.getDefaultMapColor()).instrument(Instrument.BASS).strength(3.0F).nonOpaque().burnable().pistonBehavior(PistonBehavior.DESTROY));
			Item doorItem = new TallBlockItem(doorBlock, new Item.Settings());
			door = registerBlockWithItem(name + "_door", doorBlock, doorItem);

			Block signBlock = new SignBlock(woodType, AbstractBlock.Settings.create().mapColor(mapColor).solid().instrument(Instrument.BASS).noCollision().strength(1.0F).burnable());
			Block wallSignBlock = new WallSignBlock(woodType, AbstractBlock.Settings.create().mapColor(mapColor).solid().instrument(Instrument.BASS).noCollision().strength(1.0F).dropsLike(signBlock).burnable());
			Item signItem = new SignItem((new Item.Settings()).maxCount(16), signBlock, wallSignBlock);
			sign = registerBlockWithItem(name + "_sign", signBlock, signItem);
			wallSign = registerBlock(name + "_wall_sign", wallSignBlock);

			Block hangingSignBlock = new HangingSignBlock(woodType, AbstractBlock.Settings.create().mapColor(log.getDefaultMapColor()).solid().instrument(Instrument.BASS).noCollision().strength(1.0F).burnable());
			Block hangingSignWallBlock = new WallHangingSignBlock(woodType, AbstractBlock.Settings.create().mapColor(log.getDefaultMapColor()).solid().instrument(Instrument.BASS).noCollision().strength(1.0F).burnable().dropsLike(hangingSignBlock));
			Item hangingSignItem = new HangingSignItem(hangingSignBlock, hangingSignWallBlock, new Item.Settings().maxCount(16));
			hangingSign = registerBlockWithItem(name + "_hanging_sign", hangingSignBlock, hangingSignItem);
			wallHangingSign = registerBlock(name + "_wall_hanging_sign", hangingSignWallBlock);

			pressurePlate = registerBlockWithBlockItem(name + "_pressure_plate", new PressurePlateBlock(BLOCK_SET_TYPE, AbstractBlock.Settings.create().mapColor(planks.getDefaultMapColor()).solid().instrument(Instrument.BASS).noCollision().strength(0.5F).burnable().pistonBehavior(PistonBehavior.DESTROY)));
			fence = registerBlockWithBlockItem(name + "_fence", new FenceBlock(AbstractBlock.Settings.create().mapColor(planks.getDefaultMapColor()).solid().instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
			trapdoor = registerBlockWithBlockItem(name + "_trapdoor", new TrapdoorBlock(BLOCK_SET_TYPE, AbstractBlock.Settings.create().mapColor(mapColor).instrument(Instrument.BASS).strength(3.0F).nonOpaque().allowsSpawning(Blocks::never).burnable()));
			fenceGate = registerBlockWithBlockItem(name + "_fence_gate", new FenceGateBlock(woodType, AbstractBlock.Settings.create().mapColor(planks.getDefaultMapColor()).solid().instrument(Instrument.BASS).strength(2.0F, 3.0F).burnable()));
			button = registerBlockWithBlockItem(name + "_button", Blocks.createWoodenButtonBlock(BLOCK_SET_TYPE));
			slab = registerBlockWithBlockItem(name + "_slab", new SlabBlock(AbstractBlock.Settings.create().mapColor(mapColor).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));

			StrippableBlockRegistry.register(log, strippedLog);
			StrippableBlockRegistry.register(wood, strippedWood);
			
			FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
			registry.add(planks, 5, 20);
			registry.add(slab, 5, 20);
			registry.add(fenceGate, 5, 20);
			registry.add(fence, 5, 20);
			registry.add(stairs, 5, 20);
			registry.add(log, 5, 5);
			registry.add(strippedLog, 5, 5);
			registry.add(strippedWood, 5, 5);
			registry.add(wood, 5, 5);
			
		}

		private static Block createStairsBlock(Block base) {
			return new StairsBlock(base.getDefaultState(), AbstractBlock.Settings.copy(base));
		}

		public void registerClient() {
			BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), door, trapdoor);
		}

		public void addEntries(ItemGroup.Entries entries) {
			entries.add(planks);
			entries.add(log);
			entries.add(strippedLog);
			entries.add(wood);
			entries.add(stairs);
			entries.add(sign);
			entries.add(door);
			entries.add(hangingSign);
			entries.add(pressurePlate);
			entries.add(fence);
			entries.add(trapdoor);
			entries.add(fenceGate);
			entries.add(button);
			entries.add(slab);
		}
	}
	
	public static final Block SUNRISE_DAISY = new FlowerBlock(StatusEffects.SPEED, 100, FabricBlockSettings.copyOf(Blocks.POPPY));
	public static final Block HIMALAYAN_POPPY = new FlowerBlock(StatusEffects.SPEED, 100, FabricBlockSettings.copyOf(Blocks.POPPY));
	public static final Block BLUE_HYDRANGEA = new FlowerBlock(StatusEffects.SPEED, 100, FabricBlockSettings.copyOf(Blocks.POPPY));
	public static final Block FLEABANE = new FlowerBlock(StatusEffects.SPEED, 100, FabricBlockSettings.copyOf(Blocks.POPPY));
	public static final Block DUNE_GRASS = new DuneGrassBlock(FabricBlockSettings.copyOf(Blocks.SHORT_GRASS));
	public static final Block ALOE_VERA = new AloeVeraBlock(FabricBlockSettings.copyOf(Blocks.POPPY));
	public static final Block SCARLET_FLAX = new FlowerBlock(StatusEffects.SPEED, 100, FabricBlockSettings.copyOf(Blocks.POPPY));
	public static final Block OBAMA_PLANT = new FlowerBlock(StatusEffects.SPEED, 100, FabricBlockSettings.copyOf(Blocks.POPPY));
	public static final Block CHACONIA = new TallFlowerBlock(FabricBlockSettings.copyOf(Blocks.ROSE_BUSH));
	public static final Block PERIWINKLE = new PeriwinkleBlock(FabricBlockSettings.copyOf(Blocks.POPPY).offset(AbstractBlock.OffsetType.NONE));
	public static final Block WHITE_PERIWINKLE = new PeriwinkleBlock(FabricBlockSettings.copyOf(Blocks.POPPY));
	public static final Block PURPLE_PERIWINKLE = new PeriwinkleBlock(FabricBlockSettings.copyOf(Blocks.POPPY));
	public static final HoneysuckleBlock HONEYSUCKLE = new HoneysuckleBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).replaceable().noCollision().strength(0.2F).sounds(BlockSoundGroup.GLOW_LICHEN).burnable().pistonBehavior(PistonBehavior.DESTROY));
	public static final Block FELICIA_DAISY = new FlowerBlock(StatusEffects.SPEED, 100, FabricBlockSettings.copyOf(Blocks.POPPY));
	public static final Block FLAME_ORCHID = new FlowerBlock(StatusEffects.SPEED, 100, FabricBlockSettings.copyOf(Blocks.POPPY));
	public static final Block GERBERA_DAISY = new FlowerBlock(StatusEffects.SPEED, 100, FabricBlockSettings.copyOf(Blocks.POPPY));
	public static final Block ORANGE_ZINNIA = new TallFlowerBlock(FabricBlockSettings.copyOf(Blocks.ROSE_BUSH));
	public static final Block PINK_ALLIUM = new FlowerBlock(StatusEffects.SPEED, 100, FabricBlockSettings.copyOf(Blocks.POPPY));
	public static final Block PINK_ORCHID = new FlowerBlock(StatusEffects.SPEED, 100, FabricBlockSettings.copyOf(Blocks.POPPY));
	public static final Block PINK_ZINNIA = new TallFlowerBlock(FabricBlockSettings.copyOf(Blocks.ROSE_BUSH));
	
	private static final AbstractBlock.Settings POTTED_PLANT_SETTINGS = FabricBlockSettings.copyOf(Blocks.POTTED_POPPY);
	public static final Block POTTED_SUNRISE_DAISY = new FlowerPotBlock(SUNRISE_DAISY, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_HIMALAYAN_POPPY = new FlowerPotBlock(HIMALAYAN_POPPY, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_BLUE_HYDRANGEA = new FlowerPotBlock(BLUE_HYDRANGEA, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_FLEABANE = new FlowerPotBlock(FLEABANE, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_ALOE_VERA = new FlowerPotBlock(ALOE_VERA, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_SCARLET_FLAX = new FlowerPotBlock(SCARLET_FLAX, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_OBAMA_PLANT = new FlowerPotBlock(OBAMA_PLANT, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_FELICIA_DAISY = new FlowerPotBlock(FELICIA_DAISY, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_FLAME_ORCHID = new FlowerPotBlock(FLAME_ORCHID, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_GERBERA_DAISY = new FlowerPotBlock(GERBERA_DAISY, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_PINK_ALLIUM = new FlowerPotBlock(PINK_ALLIUM, POTTED_PLANT_SETTINGS);
	public static final Block POTTED_PINK_ORCHID = new FlowerPotBlock(PINK_ORCHID, POTTED_PLANT_SETTINGS);
	
	public static void register() {
		SriasTreeDecorators.register();

		for (WoodSet set : WOOD_SETS) {
			set.register();
		}
		for (SaplingSet set : SAPLING_SETS) {
			set.register();
		}
		
		// Non-flowers
		registerBlockWithBlockItem("dune_grass", DUNE_GRASS);
		registerBlockWithBlockItem("honeysuckle", HONEYSUCKLE);
		
		// Small flowers
		registerBlockWithBlockItem("sunrise_daisy", SUNRISE_DAISY);
		registerBlockWithBlockItem("himalayan_poppy", HIMALAYAN_POPPY);
		registerBlockWithBlockItem("blue_hydrangea", BLUE_HYDRANGEA);
		registerBlockWithBlockItem("fleabane", FLEABANE);
		registerBlockWithBlockItem("aloe_vera", ALOE_VERA);
		registerBlockWithBlockItem("scarlet_flax", SCARLET_FLAX);
		registerBlockWithBlockItem("obama_plant", OBAMA_PLANT);
		registerBlockWithBlockItem("periwinkle", PERIWINKLE);
		registerBlockWithBlockItem("white_periwinkle", WHITE_PERIWINKLE);
		registerBlockWithBlockItem("purple_periwinkle", PURPLE_PERIWINKLE);
		registerBlockWithBlockItem("felicia_daisy", FELICIA_DAISY);
		registerBlockWithBlockItem("flame_orchid", FLAME_ORCHID);
		registerBlockWithBlockItem("gerbera_daisy", GERBERA_DAISY);
		registerBlockWithBlockItem("pink_allium", PINK_ALLIUM);
		registerBlockWithBlockItem("pink_orchid", PINK_ORCHID);
		
		// Tall flowers
		registerBlockWithBlockItem("chaconia", CHACONIA);
		registerBlockWithBlockItem("orange_zinnia", ORANGE_ZINNIA);
		registerBlockWithBlockItem("pink_zinnia", PINK_ZINNIA);
		
		// Potted flowers
		registerBlock("potted_sunrise_daisy", POTTED_SUNRISE_DAISY);
		registerBlock("potted_himalayan_poppy", POTTED_HIMALAYAN_POPPY);
		registerBlock("potted_blue_hydrangea", POTTED_BLUE_HYDRANGEA);
		registerBlock("potted_fleabane", POTTED_FLEABANE);
		registerBlock("potted_aloe_vera", POTTED_ALOE_VERA);
		registerBlock("potted_scarlet_flax", POTTED_SCARLET_FLAX);
		registerBlock("potted_obama_plant", POTTED_OBAMA_PLANT);
		registerBlock("potted_felicia_daisy", POTTED_FELICIA_DAISY);
		registerBlock("potted_flame_orchid", POTTED_FLAME_ORCHID);
		registerBlock("potted_gerbera_daisy", POTTED_GERBERA_DAISY);
		registerBlock("potted_pink_allium", POTTED_PINK_ALLIUM);
		registerBlock("potted_pink_orchid", POTTED_PINK_ORCHID);
	}
	
	static Block registerBlockWithBlockItem(String name, Block block) {
		Block b = Registry.register(Registries.BLOCK, SriasFlowers.id(name), block);
		Registry.register(Registries.ITEM, SriasFlowers.id(name), new BlockItem(block, new FabricItemSettings()));
		return b;
	}

	static Block registerBlockWithItem(String name, Block block, Item item) {
		Block b = Registry.register(Registries.BLOCK, SriasFlowers.id(name), block);
		Registry.register(Registries.ITEM, SriasFlowers.id(name), item);
		return b;
	}

	static Block registerBlock(String name, Block block) {
		return Registry.register(Registries.BLOCK, SriasFlowers.id(name), block);
	}
	
	@Environment(EnvType.CLIENT)
	public static void registerClient() {
		for (WoodSet set : WOOD_SETS) {
			set.registerClient();
		}
		for (SaplingSet set : SAPLING_SETS) {
			set.registerClient();
		}
		
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
				SUNRISE_DAISY, HIMALAYAN_POPPY, BLUE_HYDRANGEA,
				FLEABANE, DUNE_GRASS, ALOE_VERA, HONEYSUCKLE,
				SCARLET_FLAX, CHACONIA, PERIWINKLE, PURPLE_PERIWINKLE, WHITE_PERIWINKLE,
				FELICIA_DAISY, FLAME_ORCHID, GERBERA_DAISY, ORANGE_ZINNIA, PINK_ALLIUM, PINK_ORCHID, PINK_ZINNIA,
				
				POTTED_SUNRISE_DAISY, POTTED_HIMALAYAN_POPPY, POTTED_BLUE_HYDRANGEA,
				POTTED_FLEABANE, POTTED_ALOE_VERA, POTTED_SCARLET_FLAX,
				POTTED_FELICIA_DAISY, POTTED_FLAME_ORCHID, POTTED_GERBERA_DAISY, POTTED_PINK_ALLIUM, POTTED_PINK_ORCHID
		);
	}
	
	public static void registerFlammableBlocks() {
		FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
		
		registry.add(SUNRISE_DAISY, 60, 100);
		registry.add(HIMALAYAN_POPPY, 60, 100);
		registry.add(BLUE_HYDRANGEA, 60, 100);
		registry.add(FLEABANE, 60, 100);
		registry.add(DUNE_GRASS, 60, 100);
		registry.add(ALOE_VERA, 60, 100);
		registry.add(OBAMA_PLANT, 60, 100);
		registry.add(SCARLET_FLAX, 60, 100);
		registry.add(CHACONIA, 60, 100);
		registry.add(PERIWINKLE, 60, 100);
		registry.add(WHITE_PERIWINKLE, 60, 100);
		registry.add(PURPLE_PERIWINKLE, 60, 100);
		
		
	}
	
	
}
