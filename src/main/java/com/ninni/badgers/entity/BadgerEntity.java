package com.ninni.badgers.entity;

import com.ninni.badgers.block.BadgersBlocks;
import com.ninni.badgers.sound.BadgersSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.UUID;

public class BadgerEntity extends AnimalEntity implements Angerable {
    private static final TrackedData<Integer> ANGER_TIME = DataTracker.registerData(BadgerEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
    private static final Ingredient BREEDING_INGREDIENT = Ingredient.ofItems(BadgersBlocks.SOUR_BERRY_BUSH.asItem());
    private static final Ingredient LURING_INGREDIENT = Ingredient.ofItems(Items.SWEET_BERRIES, Items.APPLE, BadgersBlocks.SOUR_BERRY_BUSH.asItem(), Items.RABBIT, Items.GLOW_BERRIES, Items.SPIDER_EYE, Items.MILK_BUCKET);
    @Nullable private UUID targetUuid;

    public BadgerEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.stepHeight = 1;
    }

    @Override
    protected void initGoals() {
        this.targetSelector.add(0, new ActiveTargetGoal<>(this, RabbitEntity.class, false));
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new UniversalAngerGoal<>(this, true));

        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new AnimalMateGoal(this, 0.8D));
        this.goalSelector.add(2, new BadgerEntity.AttackGoal(2F, true));
        this.goalSelector.add(3, new PounceAtTargetGoal(this, 0.2F));
        this.goalSelector.add(4, new TemptGoal(this, 1.5, LURING_INGREDIENT, false));
        this.goalSelector.add(5, new FollowParentGoal(this, 1.2D));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1D));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
    }

    @Override
    public boolean isAffectedBySplashPotions() {
        return false;
    }

    @Override
    public boolean canHaveStatusEffect(StatusEffectInstance effect) {
        return false;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return BREEDING_INGREDIENT.test(stack);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ANGER_TIME, 0);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        this.writeAngerToNbt(nbt);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.readAngerFromNbt(this.world, nbt);
    }

    public static DefaultAttributeContainer.Builder createBadgerAttributes() {
        return MobEntity.createMobAttributes()
                        .add(EntityAttributes.GENERIC_MAX_HEALTH, 12.0D)
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15D)
                        .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0D);
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

    @Override
    public int getAngerTime() {
        return this.dataTracker.get(ANGER_TIME);
    }

    @Override
    public void setAngerTime(int ticks) {
        this.dataTracker.set(ANGER_TIME, ticks);
    }

    @Nullable
    @Override
    public UUID getAngryAt() {
        return this.targetUuid;
    }

    @Override
    public void setAngryAt(@Nullable UUID uuid) {
        this.targetUuid = uuid;
    }

    @Override
    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
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
                BadgerEntity.this.playSound(BadgersSoundEvents.ENTITY_BADGER_BITE, 2F, 1F);
            }
        }
    }

    @SuppressWarnings("unused")
    public static boolean canSpawn(EntityType <BadgerEntity> entity, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random){
        BlockState state = world.getBlockState(pos.down());
        return state.isOf(Blocks.GRASS_BLOCK) && world.getBaseLightLevel(pos, 0) > 8;
    }

}
