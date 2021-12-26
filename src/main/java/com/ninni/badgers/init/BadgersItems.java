package com.ninni.badgers.init;

import com.ninni.badgers.Badgers;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class BadgersItems {
    public static final Item SOUR_BERRIES = register("sour_berries", new BlockItem(BadgersBlocks.SOUR_BERRY_BUSH, new FabricItemSettings().group(Badgers.ITEM_GROUP).food( new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 6 * 20), 0.5f).hunger(3).saturationModifier(0.3F).build())));
    public static final Item SOUR_BERRY_MUFFIN = register("sour_berry_muffin", new Item(new FabricItemSettings().group(Badgers.ITEM_GROUP).food(new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20, 1), 0.75f).snack().hunger(4).saturationModifier(0.5F).build())));
    public static final Item SWEET_BERRY_MUFFIN = register("sweet_berry_muffin", new Item(new FabricItemSettings().group(Badgers.ITEM_GROUP).food(new FoodComponent.Builder().snack().statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20, 1), 0.75f).hunger(5).saturationModifier(0.3F).build())));
    public static final Item GLOW_BERRY_MUFFIN = register("glow_berry_muffin", new Item(new FabricItemSettings().group(Badgers.ITEM_GROUP).food(new FoodComponent.Builder().snack().statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20, 1), 0.75f).hunger(4).saturationModifier(0.3F).build())));
    public static final Item LOGO = register("logo", new Item(new FabricItemSettings()));

    static {
        CompostingChanceRegistry composting = CompostingChanceRegistry.INSTANCE;
        composting.add(SOUR_BERRIES, 0.3F);
        composting.add(SOUR_BERRY_MUFFIN, 0.65F);
        composting.add(SWEET_BERRY_MUFFIN, 0.65F);
        composting.add(GLOW_BERRY_MUFFIN, 0.65F);
    }

    private static Item register(String id, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Badgers.MOD_ID, id), item);
    }
}