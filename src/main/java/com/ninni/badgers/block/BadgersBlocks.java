package com.ninni.badgers.block;

import com.ninni.badgers.Badgers;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Function;

public class BadgersBlocks {
    public static final Block SOUR_BERRY_BUSH = register(
        "sour_berry_bush", new SourBerryBushBlock(FabricBlockSettings.copyOf(Blocks.SWEET_BERRY_BUSH)),
        "sour_berries", block -> new BlockItem(block, new FabricItemSettings()
            .group(Badgers.ITEM_GROUP)
            .food(
                new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 6 * 20), 0.5f)
                                           .hunger(3).saturationModifier(0.3F)
                                           .build()
            )
        )
    );

    private static Block register(String id, Block block, String itemId, Function<Block, Item> item) {
        if (item != null) Registry.register(Registry.ITEM, new Identifier(Badgers.MOD_ID, itemId), item.apply(block));
        return Registry.register(Registry.BLOCK, new Identifier(Badgers.MOD_ID, id), block);
    }

    private static Block register(String id, Block block) {
        return register(id, block, id, (b) -> new BlockItem(b, new FabricItemSettings().group(Badgers.ITEM_GROUP)));
    }
}
