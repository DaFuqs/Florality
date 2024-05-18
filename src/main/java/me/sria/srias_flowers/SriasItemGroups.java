package me.sria.srias_flowers;

import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.minecraft.item.*;

import java.util.*;

public class SriasItemGroups {
	
	public static void register() {
		
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
			for (List<? extends SriasBlocks.BlockSet> set : SriasBlocks.ALL_SETS) {
				for (SriasBlocks.BlockSet entry : set) {
					entry.addEntries(entries);
				}
			}
			
			entries.add(SriasBlocks.DUNE_GRASS);
			entries.add(SriasBlocks.HONEYSUCKLE);
		});
		
		
		
	}
	
}