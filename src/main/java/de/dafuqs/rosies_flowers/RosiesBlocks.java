package de.dafuqs.rosies_flowers;

import com.terraformersmc.terraform.sign.api.block.*;
import de.dafuqs.rosies_flowers.blocks.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.fabricmc.fabric.api.object.builder.v1.block.type.*;
import net.fabricmc.fabric.api.registry.*;
import net.minecraft.block.*;
import net.minecraft.block.enums.*;
import net.minecraft.block.piston.*;
import net.minecraft.client.render.*;
import net.minecraft.entity.effect.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.sound.*;

import java.util.*;

public class RosiesBlocks {

	public static List<SaplingSet> SAPLING_SETS = new ArrayList<>() {{
		add(new SaplingSet("dogwood", MapColor.WHITE_GRAY));
		add(new SaplingSet("red_dogwood", MapColor.DULL_PINK));
		add(new SaplingSet("blue_wisteria", MapColor.WATER_BLUE));
		add(new SaplingSet("purple_wisteria", MapColor.TERRACOTTA_PURPLE));
	}};
	
	public static List<WoodSet> WOOD_SETS = new ArrayList<>() {{
		add(new WoodSet("dogwood", MapColor.OAK_TAN, MapColor.GRAY));
	}};
	
	public static List<SmallFlowerSet> SMALL_FLOWER_SETS = new ArrayList<>() {{
		add(new SmallFlowerSet("sunrise_daisy"));
		add(new SmallFlowerSet("himalayan_poppy"));
		add(new SmallFlowerSet("blue_hydrangea"));
		add(new SmallFlowerSet("scarlet_flax"));
		add(new SmallFlowerSet("obama_plant"));
		add(new SmallFlowerSet("white_periwinkle", new PeriwinkleBlock(StatusEffects.SPEED, 100, FabricBlockSettings.copyOf(Blocks.POPPY)), false));
		add(new SmallFlowerSet("purple_periwinkle", new PeriwinkleBlock(StatusEffects.SPEED, 100, FabricBlockSettings.copyOf(Blocks.POPPY)), false));
		add(new SmallFlowerSet("felicia_daisy"));
		add(new SmallFlowerSet("flame_orchid"));
		add(new SmallFlowerSet("gerbera_daisy"));
		add(new SmallFlowerSet("pink_allium"));
		add(new SmallFlowerSet("pink_orchid"));
		add(new SmallFlowerSet("aloe_vera", new AloeVeraBlock(FabricBlockSettings.copyOf(Blocks.POPPY)), false));
	}};
	
	public static List<TallFlowerSet> TALL_FLOWER_SETS = new ArrayList<>() {{
		add(new TallFlowerSet("chaconia"));
		
		add(new TallFlowerSet("blue_sage"));
		add(new TallFlowerSet("butterfly_weed"));
		add(new TallFlowerSet("fuchsia"));
		add(new TallFlowerSet("larkspur"));
		add(new TallFlowerSet("mountain_laurel"));
		add(new TallFlowerSet("purple_hibiscus"));
	}};
	
	public static List<List<? extends BlockSet>> ALL_SETS = new ArrayList<>() {{
		add(SAPLING_SETS);
		add(WOOD_SETS);
		add(SMALL_FLOWER_SETS);
		add(TALL_FLOWER_SETS);
	}};
	
	public static abstract class BlockSet {
		public abstract void register();
		
		public abstract void registerClient();
		
		public abstract void addEntries(ItemGroup.Entries entries);
	}
	
	public static class SaplingSet extends BlockSet {
		
		private final String name;
		private final MapColor mapColor;

		public Block sapling;
		public Block pottedSapling;
		public Block leaves;
		public Block leafCarpet;

		public SaplingSet(String name, MapColor mapColor) {
			this.name = name;
			this.mapColor = mapColor;
		}
		
		public void register() {
			this.leaves = registerBlockWithBlockItem(name + "_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).sounds(BlockSoundGroup.AZALEA_LEAVES).mapColor(mapColor)));
			this.leafCarpet = registerBlockWithBlockItem(name + "_carpet", new LeafCarpetBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CARPET).sounds(BlockSoundGroup.AZALEA_LEAVES).mapColor(mapColor).nonOpaque()));
			
			SaplingGenerator saplingGenerator = new SaplingGenerator("srias_" + name, Optional.empty(), Optional.of(RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, RosiesFlowers.id(name + "_tree"))), Optional.empty());
			this.sapling = registerBlockWithBlockItem(name + "_sapling", new SaplingBlock(saplingGenerator, FabricBlockSettings.copyOf(Blocks.OAK_SAPLING).mapColor(mapColor)));
			this.pottedSapling = registerBlock("potted_" + name + "_sapling", new FlowerPotBlock(sapling, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING)));
			
			FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
			registry.add(leaves, 30, 60);
		}
		
		public void registerClient() {
			BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), leafCarpet, sapling, pottedSapling);
		}

		public void addEntries(ItemGroup.Entries entries) {
			entries.add(sapling);
			entries.add(leaves);
			entries.add(leafCarpet);
		}
		
	}
	
	public static class SmallFlowerSet extends BlockSet {
		
		private static final AbstractBlock.Settings POTTED_PLANT_SETTINGS = FabricBlockSettings.copyOf(Blocks.POTTED_POPPY);
		
		private final String name;
		private final boolean canBePotted;
		
		public Block block;
		public Block pottedBlock;
		
		public SmallFlowerSet(String name) {
			this(name, new FlowerBlock(StatusEffects.SPEED, 100, FabricBlockSettings.copyOf(Blocks.POPPY)), true);
		}
		
		public SmallFlowerSet(String name, Block block, boolean canBePotted) {
			this.name = name;
			this.block = block;
			this.canBePotted = canBePotted;
		}
		
		public void register() {
			this.block = registerBlockWithBlockItem(name, block);
			if (canBePotted) {
				this.pottedBlock = registerBlock("potted_" + name, new FlowerPotBlock(block, POTTED_PLANT_SETTINGS));
			}
			
			FlammableBlockRegistry.getDefaultInstance().add(block, 60, 100);
		}
		
		public void registerClient() {
			BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), block);
			if (canBePotted) {
				BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), block);
			}
		}
		
		public void addEntries(ItemGroup.Entries entries) {
			entries.add(block);
		}
		
	}
	
	public static class TallFlowerSet extends BlockSet {
		
		private final String name;
		public Block block;
		
		public TallFlowerSet(String name) {
			this.name = name;
		}
		
		public void register() {
			this.block = registerBlockWithBlockItem(name, new TallFlowerBlock(FabricBlockSettings.copyOf(Blocks.ROSE_BUSH)));
		}
		
		public void registerClient() {
			BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), block);
		}
		
		public void addEntries(ItemGroup.Entries entries) {
			entries.add(block);
		}
		
	}
	
	public static class WoodSet extends BlockSet {
		
		public static final BlockSetType BLOCK_SET_TYPE = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(RosiesFlowers.id("wood"));

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
		//public Block door;
		public Block sign;
		public Block wallSign;
		//public Block hangingSign;
		//public Block wallHangingSign;
		public Block pressurePlate;
		public Block fence;
		//public Block trapdoor;
		public Block fenceGate;
		public Block button;
		public Block slab;

		public WoodSet(String name, MapColor mapColor, MapColor topLogMapColor) {
			this.name = name;
			this.mapColor = mapColor;
			this.topLogMapColor = topLogMapColor;
			
			this.woodType = new WoodTypeBuilder().register(RosiesFlowers.id(name), BLOCK_SET_TYPE);
		}

		public void register() {
			planks = registerBlockWithBlockItem(name + "_planks", new Block(AbstractBlock.Settings.create().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
			log = registerBlockWithBlockItem(name + "_log", Blocks.createLogBlock(mapColor, topLogMapColor));
			strippedLog = registerBlockWithBlockItem("stripped_" + name + "_log", Blocks.createLogBlock(mapColor, mapColor));
			wood = registerBlockWithBlockItem(name + "_wood", new PillarBlock(AbstractBlock.Settings.create().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()));
			strippedWood = registerBlockWithBlockItem("stripped_" + name + "_wood", new PillarBlock(AbstractBlock.Settings.create().mapColor(topLogMapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()));
			stairs = registerBlockWithBlockItem(name + "_stairs", createStairsBlock(planks));
			
			//Block doorBlock = new DoorBlock(BLOCK_SET_TYPE, AbstractBlock.Settings.create().mapColor(planks.getDefaultMapColor()).instrument(Instrument.BASS).strength(3.0F).nonOpaque().burnable().pistonBehavior(PistonBehavior.DESTROY));
			//Item doorItem = new TallBlockItem(doorBlock, new Item.Settings());
			//door = registerBlockWithItem(name + "_door", doorBlock, doorItem);
			
			Block signBlock = new TerraformSignBlock(RosiesFlowers.id("entity/signs/dogwood"), AbstractBlock.Settings.create().mapColor(mapColor).solid().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).burnable());
			Block wallSignBlock = new TerraformWallSignBlock(RosiesFlowers.id("entity/signs/dogwood"), AbstractBlock.Settings.create().mapColor(mapColor).solid().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).dropsLike(signBlock).burnable());
			Item signItem = new SignItem((new Item.Settings()).maxCount(16), signBlock, wallSignBlock);
			sign = registerBlockWithItem(name + "_sign", signBlock, signItem);
			wallSign = registerBlock(name + "_wall_sign", wallSignBlock);
			
			//Block hangingSignBlock = new HangingSignBlock(woodType, AbstractBlock.Settings.create().mapColor(log.getDefaultMapColor()).solid().instrument(Instrument.BASS).noCollision().strength(1.0F).burnable());
			//Block hangingSignWallBlock = new WallHangingSignBlock(woodType, AbstractBlock.Settings.create().mapColor(log.getDefaultMapColor()).solid().instrument(Instrument.BASS).noCollision().strength(1.0F).burnable().dropsLike(hangingSignBlock));
			//Item hangingSignItem = new HangingSignItem(hangingSignBlock, hangingSignWallBlock, new Item.Settings().maxCount(16));
			//hangingSign = registerBlockWithItem(name + "_hanging_sign", hangingSignBlock, hangingSignItem);
			//wallHangingSign = registerBlock(name + "_wall_hanging_sign", hangingSignWallBlock);
			
			pressurePlate = registerBlockWithBlockItem(name + "_pressure_plate", new PressurePlateBlock(BLOCK_SET_TYPE, AbstractBlock.Settings.create().mapColor(planks.getDefaultMapColor()).solid().instrument(NoteBlockInstrument.BASS).noCollision().strength(0.5F).burnable().pistonBehavior(PistonBehavior.DESTROY)));
			fence = registerBlockWithBlockItem(name + "_fence", new FenceBlock(AbstractBlock.Settings.create().mapColor(planks.getDefaultMapColor()).solid().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));
			//trapdoor = registerBlockWithBlockItem(name + "_trapdoor", new TrapdoorBlock(BLOCK_SET_TYPE, AbstractBlock.Settings.create().mapColor(mapColor).instrument(Instrument.BASS).strength(3.0F).nonOpaque().allowsSpawning(Blocks::never).burnable()));
			fenceGate = registerBlockWithBlockItem(name + "_fence_gate", new FenceGateBlock(woodType, AbstractBlock.Settings.create().mapColor(planks.getDefaultMapColor()).solid().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).burnable()));
			button = registerBlockWithBlockItem(name + "_button", Blocks.createWoodenButtonBlock(BLOCK_SET_TYPE));
			slab = registerBlockWithBlockItem(name + "_slab", new SlabBlock(AbstractBlock.Settings.create().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));

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
			//BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), door, trapdoor);
		}

		public void addEntries(ItemGroup.Entries entries) {
			entries.add(planks);
			entries.add(log);
			entries.add(strippedLog);
			entries.add(wood);
			entries.add(strippedWood);
			entries.add(stairs);
			//entries.add(door);
			entries.add(sign);
			//entries.add(hangingSign);
			entries.add(pressurePlate);
			entries.add(fence);
			//entries.add(trapdoor);
			entries.add(fenceGate);
			entries.add(button);
			entries.add(slab);
		}
	}
	
	public static final Block DUNE_GRASS = new DuneGrassBlock(FabricBlockSettings.copyOf(Blocks.SHORT_GRASS));
	public static final HoneysuckleBlock HONEYSUCKLE = new HoneysuckleBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).replaceable().noCollision().strength(0.2F).sounds(BlockSoundGroup.GLOW_LICHEN).burnable().pistonBehavior(PistonBehavior.DESTROY));
	
	public static void register() {
		for (List<? extends BlockSet> set : ALL_SETS) {
			for (BlockSet entry : set) {
				entry.register();
			}
		}
		
		registerBlockWithBlockItem("dune_grass", DUNE_GRASS);
		registerBlockWithBlockItem("honeysuckle", HONEYSUCKLE);
	}
	
	static Block registerBlockWithBlockItem(String name, Block block) {
		Block b = Registry.register(Registries.BLOCK, RosiesFlowers.id(name), block);
		Registry.register(Registries.ITEM, RosiesFlowers.id(name), new BlockItem(block, new Item.Settings()));
		return b;
	}

	static Block registerBlockWithItem(String name, Block block, Item item) {
		Block b = Registry.register(Registries.BLOCK, RosiesFlowers.id(name), block);
		Registry.register(Registries.ITEM, RosiesFlowers.id(name), item);
		return b;
	}

	static Block registerBlock(String name, Block block) {
		return Registry.register(Registries.BLOCK, RosiesFlowers.id(name), block);
	}
	
	@Environment(EnvType.CLIENT)
	public static void registerClient() {
		for (List<? extends BlockSet> set : ALL_SETS) {
			for (BlockSet entry : set) {
				entry.registerClient();
			}
		}
		
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), DUNE_GRASS, HONEYSUCKLE);
	}
	
}
