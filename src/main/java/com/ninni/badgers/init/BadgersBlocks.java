package com.ninni.badgers.init;

import com.ninni.badgers.Badgers;
import com.ninni.badgers.block.SourBerryBushBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.ninni.badgers.Badgers.MOD_ID;

@SuppressWarnings("unused")
public class BadgersBlocks {
    public static final Block SOUR_BERRY_BUSH = register("sour_berry_bush", new SourBerryBushBlock(FabricBlockSettings.copyOf(Blocks.SWEET_BERRY_BUSH)), false);

    private static Block register(String id, Block block, boolean registerItem) {
        Block registered = Registry.register(Registry.BLOCK, new Identifier(MOD_ID, id), block);
        if (registerItem) {
            Registry.register(Registry.ITEM, new Identifier(MOD_ID, id), new BlockItem(registered, new FabricItemSettings().group(Badgers.ITEM_GROUP)));
        }
        return registered;
    }
    private static Block register(String id, Block block) {
        return register(id, block, true);
    }

}