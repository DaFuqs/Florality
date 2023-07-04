package me.sria.srias_flowers.items;

import me.sria.srias_flowers.*;
import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.server.world.*;
import net.minecraft.sound.*;
import net.minecraft.stat.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.*;
import net.minecraft.world.gen.feature.*;

public class SeedingDandelionItem extends BlockItem {
	
	public SeedingDandelionItem(Block block, Settings settings) {
		super(block, settings);
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack itemStack = user.getStackInHand(hand);
		world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
		user.getItemCooldownManager().set(this, 20);
		
		if (world instanceof ServerWorld serverWorld) {
			ConfiguredFeature configuredFeature = world.getRegistryManager().get(Registry.CONFIGURED_FEATURE_KEY).get(SriasFlowers.id("seeding_dandelion"));
			configuredFeature.generate(serverWorld, serverWorld.getChunkManager().getChunkGenerator(),  world.random, user.getBlockPos().down());
		}
		
		user.incrementStat(Stats.USED.getOrCreateStat(this));
		if (!user.getAbilities().creativeMode) {
			itemStack.decrement(1);
		}
		
		return TypedActionResult.success(itemStack, world.isClient());
	}
	
}
