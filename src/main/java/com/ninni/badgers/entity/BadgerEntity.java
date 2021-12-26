package com.ninni.badgers.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BadgerEntity extends AnimalEntity {
    public BadgerEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @SuppressWarnings("unused")
    public static boolean canSpawn (EntityType <BadgerEntity> entity, ServerWorldAccess
        world, SpawnReason reason, BlockPos pos, Random random){
        BlockState blockState = world.getBlockState(pos.down());
        return (blockState.isOf(Blocks.GRASS_BLOCK) && world.getBaseLightLevel(pos, 0) > 8);
    }
}
