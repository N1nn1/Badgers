package com.ninni.badgers.item;

import com.ninni.badgers.Badgers;
import com.ninni.badgers.block.BadgersBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BadgersItems {
    public static final Item SOUR_BERRY_MUFFIN = register("sour_berry_muffin", new FoodComponent.Builder()
        .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20, 1), 0.75f)
        .hunger(4).saturationModifier(0.5F).snack()
    );
    public static final Item SWEET_BERRY_MUFFIN = register("sweet_berry_muffin", new FoodComponent.Builder()
        .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20, 1), 0.75f)
        .hunger(5).saturationModifier(0.3F).snack()
    );
    public static final Item GLOW_BERRY_MUFFIN = register("glow_berry_muffin", new FoodComponent.Builder()
        .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20, 1), 0.75f)
        .hunger(4).saturationModifier(0.3F).snack()
    );

    public static final Item LOGO = register("logo", new Item(new FabricItemSettings()));

    static {
        CompostingChanceRegistry composting = CompostingChanceRegistry.INSTANCE;
        composting.add(BadgersBlocks.SOUR_BERRY_BUSH, 0.3F);
        composting.add(SOUR_BERRY_MUFFIN, 0.65F);
        composting.add(SWEET_BERRY_MUFFIN, 0.65F);
        composting.add(GLOW_BERRY_MUFFIN, 0.65F);
    }

    private static Item register(String id, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Badgers.MOD_ID, id), item);
    }

    private static Item register(String id, FoodComponent.Builder food) {
        return register(id, new Item(new FabricItemSettings().food(food.build()).group(Badgers.ITEM_GROUP)));
    }
}
