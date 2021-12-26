//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ninni.badgers.world.gen.feature;

import com.ninni.badgers.Badgers;
import com.ninni.badgers.block.BadgersBlocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class BadgersConfiguredFeatures {
    public static final ConfiguredFeature<RandomPatchFeatureConfig, ?> PATCH_BERRIES = register("patch_berries", Feature.RANDOM_PATCH.configure(randomPatch(BlockStateProvider.of(BadgersBlocks.SOUR_BERRY_BUSH.getDefaultState().getBlock()), 15)));

    private static RandomPatchFeatureConfig randomPatch(BlockStateProvider block, int tries) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(block)).withInAirFilter());
    }

    public static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Badgers.MOD_ID, id), configuredFeature);
    }
}
