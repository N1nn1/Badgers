package com.ninni.badgers.world.gen.feature;

import com.google.common.collect.ImmutableList;
import com.ninni.badgers.Badgers;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.minecraft.world.gen.feature.VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER;

public class BadgersPlacedFeatures {
    public static final PlacedFeature PATCH_BERRIES = register("patch_berries", BadgersConfiguredFeatures.PATCH_BERRIES.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()));

    static {
        BuiltinRegistries.PLACED_FEATURE.getKey(PATCH_BERRIES).ifPresent(key -> {
            BiomeModifications.addFeature(ctx -> ctx.getBiomeKey() == BiomeKeys.MEADOW, GenerationStep.Feature.VEGETAL_DECORATION, key);
        });
    }

    public static List<PlacementModifier> modifiers(int count) {
        return List.of(CountPlacementModifier.of(count), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithChance(int chance, @Nullable PlacementModifier modifier) {
        ImmutableList.Builder<PlacementModifier> builder = ImmutableList.builder();
        if (modifier != null) builder.add(modifier);
        if (chance != 0) builder.add(RarityFilterPlacementModifier.of(chance));

        builder.add(SquarePlacementModifier.of());
        builder.add(PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP);
        builder.add(BiomePlacementModifier.of());

        return builder.build();
    }

    private static ImmutableList.Builder<Object> modifiersBuilder(PlacementModifier countModifier) {
        return ImmutableList.builder().add(countModifier).add(SquarePlacementModifier.of()).add(NOT_IN_SURFACE_WATER_MODIFIER).add(PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP).add(BiomePlacementModifier.of());
    }

    public static ImmutableList<Object> modifiers(PlacementModifier modifier) {
        return modifiersBuilder(modifier).build();
    }

    public static ImmutableList<Object> modifiersWithWouldSurvive(PlacementModifier modifier, Block block) {
        return modifiersBuilder(modifier).add(BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(block.getDefaultState(), BlockPos.ORIGIN))).build();
    }

    public static PlacedFeature register(String id, PlacedFeature feature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Badgers.MOD_ID, id), feature);
    }
}
