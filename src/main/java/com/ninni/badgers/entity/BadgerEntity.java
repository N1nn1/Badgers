package com.ninni.badgers.entity;

import com.ninni.badgers.init.BadgersEntities;
import com.ninni.badgers.init.BadgersSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

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
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new UniversalAngerGoal(this, true));

        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(2, new BadgerEntity.AttackGoal(1.2, true));
        this.goalSelector.add(3, new PounceAtTargetGoal(this, 0.2F));
        this.goalSelector.add(5, new FollowParentGoal(this, 1.2D));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1D));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createBadgerAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 12.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0D);
    }

    @Override
    protected SoundEvent getAmbientSound() {
            return BadgersSoundEvents.ENTITY_BADGER_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return BadgersSoundEvents.ENTITY_BADGER_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return BadgersSoundEvents.ENTITY_BADGER_HURT;
    }

    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return BadgersEntities.BADGER.create(world);
    }

    private class AttackGoal extends MeleeAttackGoal {
        public AttackGoal(double speed, boolean pauseWhenIdle) {
            super(BadgerEntity.this, speed, pauseWhenIdle);
        }

        @Override
        protected void attack(LivingEntity target, double squaredDistance) {
            double d = this.getSquaredMaxAttackDistance(target);
            if (squaredDistance <= d && this.isCooledDown()) {
                this.resetCooldown();
                this.mob.tryAttack(target);
                BadgerEntity.this.playSound(BadgersSoundEvents.ENTITY_BADGER_BITE, 0.8F, 1F);
            }

        }
    }

    @SuppressWarnings("unused")
    public static boolean canSpawn (EntityType <BadgerEntity> entity, ServerWorldAccess
        world, SpawnReason reason, BlockPos pos, Random random){
        BlockState blockState = world.getBlockState(pos.down());
        return (blockState.isOf(Blocks.GRASS_BLOCK) && world.getBaseLightLevel(pos, 0) > 8);
    }

}
