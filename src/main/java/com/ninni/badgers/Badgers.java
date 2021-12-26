package com.ninni.badgers;

import com.google.common.reflect.Reflection;
import com.ninni.badgers.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

import static com.ninni.badgers.init.BadgersPlacedFeatures.PATCH_BERRIES;

public class Badgers implements ModInitializer {
	public static final String MOD_ID = "badgers";
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(Badgers.MOD_ID, "item_group"), () -> new ItemStack(BadgersItems.LOGO));

	@SuppressWarnings("UnstableApiUsage")
	@Override
	public void onInitialize() {
		Reflection.initialize(
			BadgersEntities.class,
			BadgersItems.class,
			BadgersSoundEvents.class,
			BadgersConfiguredFeatures.class,
			BadgersPlacedFeatures.class,
			BadgersBlocks.class
		);

		if (BuiltinRegistries.PLACED_FEATURE.getKey(PATCH_BERRIES).isPresent()) {
			BiomeModifications.addFeature(context -> (context.getBiomeKey().equals(BiomeKeys.MEADOW)), GenerationStep.Feature.VEGETAL_DECORATION,
				BuiltinRegistries.PLACED_FEATURE.getKey(PATCH_BERRIES).get());
		}
	}
}
