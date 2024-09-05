package de.dafuqs.florality;

import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.minecraft.item.*;

import java.util.*;

public class FloralityItemGroups {
	
	public static void register() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
			for (List<? extends FloralityBlocks.BlockSet> set : FloralityBlocks.ALL_SETS) {
				for (FloralityBlocks.BlockSet entry : set) {
					entry.addEntries(entries);
				}
			}
			
			entries.add(FloralityBlocks.DUNE_GRASS);
			entries.add(FloralityBlocks.HONEYSUCKLE);
		});
	}
	
}