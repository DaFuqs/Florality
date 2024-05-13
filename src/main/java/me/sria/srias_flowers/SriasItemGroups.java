package me.sria.srias_flowers;

import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.minecraft.item.*;

public class SriasItemGroups {
	
	public static void register() {
		
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
			entries.add(SriasBlocks.SUNRISE_DAISY);
			entries.add(SriasBlocks.HIMALAYAN_POPPY);
			entries.add(SriasBlocks.BLUE_HYDRANGEA);
			entries.add(SriasBlocks.FLEABANE);
			entries.add(SriasBlocks.DUNE_GRASS);
			entries.add(SriasBlocks.ALOE_VERA);
			entries.add(SriasBlocks.HONEYSUCKLE);
			entries.add(SriasBlocks.PERIWINKLE);
			entries.add(SriasBlocks.PURPLE_PERIWINKLE);
			entries.add(SriasBlocks.WHITE_PERIWINKLE);
			entries.add(SriasBlocks.CHACONIA);
			entries.add(SriasBlocks.SCARLET_FLAX);
			entries.add(SriasBlocks.OBAMA_PLANT);
			
			entries.add(SriasBlocks.FELICIA_DAISY);
			entries.add(SriasBlocks.FLAME_ORCHID);
			entries.add(SriasBlocks.GERBERA_DAISY);
			entries.add(SriasBlocks.ORANGE_ZINNIA);
			entries.add(SriasBlocks.PINK_ALLIUM);
			entries.add(SriasBlocks.PINK_ORCHID);
			entries.add(SriasBlocks.PINK_ZINNIA);
			
			for (SriasBlocks.WoodSet set : SriasBlocks.WOOD_SETS) {
				set.addEntries(entries);
			}
			for (SriasBlocks.SaplingSet set : SriasBlocks.SAPLING_SETS) {
				set.addEntries(entries);
			}
		});
		
		
		
	}
	
}