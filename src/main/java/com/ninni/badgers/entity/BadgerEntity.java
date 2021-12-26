package com.ninni.badgers.entity;

import com.ninni.badgers.init.BadgersEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BadgerEntity extends AnimalEntity {

    public BadgerEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        stepHeight = 1;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    protected void initGoals() {
        this.targetSelector.add(0, new ActiveTargetGoal<>(this, RabbitEntity.class, false));

        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new RevengeGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.add(3, new PounceAtTargetGoal(this, 0.2F));
        this.goalSelector.add(5, new FollowParentGoal(this, 1.2D));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1D));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(9, new UniversalAngerGoal(this, true));
    }

    public static DefaultAttributeContainer.Builder createBadgerAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 12.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0D);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return BadgersEntities.BADGER.create(world);
    }

    @SuppressWarnings("unused")
    public static boolean canSpawn (EntityType <BadgerEntity> entity, ServerWorldAccess
        world, SpawnReason reason, BlockPos pos, Random random){
        BlockState blockState = world.getBlockState(pos.down());
        return (blockState.isOf(Blocks.GRASS_BLOCK) && world.getBaseLightLevel(pos, 0) > 8);
    }
}
