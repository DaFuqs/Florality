package me.sria.srias_flowers;

import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.text.*;

public class SriasItemGroups {
	
	public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder().entries((displayContext, entries) -> {
		entries.add(SriasBlocks.SUNRISE_DAISY);
		entries.add(SriasBlocks.HIMALAYAN_POPPY);
		entries.add(SriasBlocks.BLUE_HYDRANGEA);
		entries.add(SriasBlocks.SEEDING_DANDELION);
		entries.add(SriasBlocks.FLEABANE);
		entries.add(SriasBlocks.DUNE_GRASS);
		entries.add(SriasBlocks.ALOE_VERA);
		entries.add(SriasBlocks.HONEYSUCKLE);
		entries.add(SriasBlocks.OBAMA_PLANT);
		
		for(SriasBlocks.WoodSet woodSet : SriasBlocks.WOOD_SETS) {
			entries.add(woodSet.SAPLING);
			entries.add(woodSet.LEAVES);
			entries.add(woodSet.LEAF_CARPET);
		}
	}).displayName(Text.translatable("itemGroup.srias_flowers.srias_flowers")).icon(() -> new ItemStack(SriasBlocks.BLUE_HYDRANGEA)).build();
	
	public static void register() {
		Registry.register(Registries.ITEM_GROUP, SriasFlowers.id("srias_flowers"), ITEM_GROUP);
	}
	
}