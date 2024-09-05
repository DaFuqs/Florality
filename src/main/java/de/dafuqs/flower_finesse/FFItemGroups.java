package de.dafuqs.flower_finesse;

import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.minecraft.item.*;

import java.util.*;

public class FFItemGroups {
	
	public static void register() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
			for (List<? extends FFBlocks.BlockSet> set : FFBlocks.ALL_SETS) {
				for (FFBlocks.BlockSet entry : set) {
					entry.addEntries(entries);
				}
			}
			
			entries.add(FFBlocks.DUNE_GRASS);
			entries.add(FFBlocks.HONEYSUCKLE);
		});
	}
	
}