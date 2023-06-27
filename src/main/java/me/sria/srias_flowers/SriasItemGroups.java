package me.sria.srias_flowers;

import net.fabricmc.fabric.api.client.itemgroup.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;

import java.util.*;

public class SriasItemGroups {
	
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(SriasFlowers.id("srias_flowers"), () -> new ItemStack(SriasBlocks.BLUE_HYDRANGEA));
	
}