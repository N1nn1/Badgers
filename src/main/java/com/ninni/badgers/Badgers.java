package com.ninni.badgers;

import com.google.common.reflect.Reflection;
import com.ninni.badgers.block.BadgersBlocks;
import com.ninni.badgers.entity.BadgersEntities;
import com.ninni.badgers.item.BadgersItems;
import com.ninni.badgers.sound.BadgersSoundEvents;
import com.ninni.badgers.wold.gen.BadgersWorldGenFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class Badgers implements ModInitializer {
	public static final String MOD_ID = "badgers";
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(Badgers.MOD_ID, "item_group"), () -> new ItemStack(BadgersItems.BADGERS));

	@SuppressWarnings("UnstableApiUsage")
	@Override
	public void onInitialize() {
		Reflection.initialize(
			BadgersEntities.class,
			BadgersItems.class,
			BadgersSoundEvents.class,
			BadgersBlocks.class
		);

		BadgersWorldGenFeatures.PATCH_SOUR_BERRIES_PLACED.getKey().ifPresent(placedFeatureRegistryKey -> BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW), GenerationStep.Feature.VEGETAL_DECORATION, placedFeatureRegistryKey));
	}
}
