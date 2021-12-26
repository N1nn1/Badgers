//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ninni.badgers.init;

import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class BadgersConfiguredFeatures {

    public static final ConfiguredFeature<RandomPatchFeatureConfig, ?> PATCH_BERRIES;


    public BadgersConfiguredFeatures() {
    }

    private static RandomPatchFeatureConfig createRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(block)).withInAirFilter());
    }


    static {
        PATCH_BERRIES = ConfiguredFeatures.register("patch_berries", Feature.RANDOM_PATCH.configure(createRandomPatchFeatureConfig(BlockStateProvider.of(BadgersBlocks.SOUR_BERRY_BUSH.getDefaultState().getBlock()), 15)));
    }
}
