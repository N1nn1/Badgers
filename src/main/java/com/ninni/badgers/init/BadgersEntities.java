package com.ninni.badgers.init;

import com.ninni.badgers.Badgers;
import com.ninni.badgers.entity.BadgerEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class BadgersEntities {

    public static final EntityType<BadgerEntity> BADGER = register(
        "badger",
        FabricEntityTypeBuilder.createMob()
                               .entityFactory(BadgerEntity::new)
                               .defaultAttributes(BadgerEntity::createMobAttributes)
                               .spawnGroup(SpawnGroup.CREATURE)
                               .spawnRestriction(SpawnRestriction.Location.ON_GROUND, Heightmap.Type.WORLD_SURFACE_WG, BadgerEntity::canSpawn)
                               .dimensions(EntityDimensions.changing(0.8F, 0.6F))
                               .trackRangeBlocks(8),
        new int[]{ 0x48423c, 0xeae7de }
    );

    static {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SWAMP, BiomeKeys.MEADOW), SpawnGroup.WATER_AMBIENT, BadgersEntities.BADGER, 25, 1, 1);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Entity> EntityType<T> register(String id, EntityType<T> entityType, int[] spawnEggColors) {
        if (spawnEggColors != null)
            Registry.register(Registry.ITEM, new Identifier(Badgers.MOD_ID, id + "_spawn_egg"), new SpawnEggItem((EntityType<? extends MobEntity>) entityType, spawnEggColors[0], spawnEggColors[1], new Item.Settings().maxCount(64).group(Badgers.ITEM_GROUP)));

        return Registry.register(Registry.ENTITY_TYPE, new Identifier(Badgers.MOD_ID, id), entityType);
    }

    private static <T extends Entity> EntityType<T> register(String id, FabricEntityTypeBuilder<T> entityType, int[] spawnEggColors) {
        return register(id, entityType.build(), spawnEggColors);
    }

}

