package de.dafuqs.rosies_flowers;

import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.minecraft.item.*;

import java.util.*;

public class RosiesItemGroups {
	
	public static void register() {
		
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
			for (List<? extends RosiesBlocks.BlockSet> set : RosiesBlocks.ALL_SETS) {
				for (RosiesBlocks.BlockSet entry : set) {
					entry.addEntries(entries);
				}
			}
			
			entries.add(RosiesBlocks.DUNE_GRASS);
			entries.add(RosiesBlocks.HONEYSUCKLE);
		});
		
		
		
	}
	
}