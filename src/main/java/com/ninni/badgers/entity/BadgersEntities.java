package com.ninni.badgers.entity;

import com.ninni.badgers.Badgers;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class BadgersEntities {
    public static final EntityType<BadgerEntity> BADGER = register(
        "badger",
        FabricEntityTypeBuilder.createMob()
                               .entityFactory(BadgerEntity::new)
                               .defaultAttributes(BadgerEntity::createBadgerAttributes)
                               .spawnGroup(SpawnGroup.CREATURE)
                               .spawnRestriction(SpawnRestriction.Location.ON_GROUND, Heightmap.Type.WORLD_SURFACE_WG, BadgerEntity::canSpawn)
                               .dimensions(EntityDimensions.fixed(0.8F, 0.6F))
                               .trackRangeBlocks(8),
        colors(0x48423c, 0xeae7de)
    );

    static {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SWAMP, BiomeKeys.MEADOW), SpawnGroup.CREATURE, BadgersEntities.BADGER, 15, 1, 4);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Entity> EntityType<T> register(String id, FabricEntityTypeBuilder<T> entityType, Pair<Integer, Integer> colors, SpawnEggFactory eggFactory) {
        EntityType<T> builtEntityType = entityType.build();
        if (eggFactory != null) {
            Item.Settings settings = new FabricItemSettings().maxCount(64).group(Badgers.ITEM_GROUP);
            Item item = eggFactory.apply((EntityType<? extends MobEntity>) builtEntityType, colors.getLeft(), colors.getRight(), settings);
            Registry.register(Registry.ITEM,  new Identifier(Badgers.MOD_ID, "%s_spawn_egg".formatted(id)), item);
        }
        return Registry.register(Registry.ENTITY_TYPE, new Identifier(Badgers.MOD_ID, id), builtEntityType);
    }

    private static <T extends Entity> EntityType<T> register(String id, FabricEntityTypeBuilder<T> entityType, Pair<Integer, Integer> colors) {
        return register(id, entityType, colors, SpawnEggItem::new);
    }

    private static <T extends Entity> EntityType<T> register(String id, FabricEntityTypeBuilder<T> entityType) {
        return register(id, entityType, null);
    }

    private static Pair<Integer, Integer> colors(int primary, int secondary) {
        return new Pair<>(primary, secondary);
    }

    @FunctionalInterface
    private interface SpawnEggFactory {
        SpawnEggItem apply(EntityType<? extends MobEntity> type, int primaryColor, int secondaryColor, Item.Settings settings);
    }
}
