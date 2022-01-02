package com.ninni.badgers.entity.ai.goal;

import com.ninni.badgers.entity.BadgerEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

import java.util.List;

public class PlayWithBabyFoxGoal extends Goal {
    private final BadgerEntity badger;
    private FoxEntity foxEntity;
    private int delay;
    private final double speed;
    private int timer;

    public PlayWithBabyFoxGoal(BadgerEntity badger, double speed) {
        this.badger = badger;
        this.speed = speed;
    }

    @Override
    public boolean canStart() {
        if (!this.badger.isBaby()) {
            return false;
        } else {
            FoxEntity fox = null;
            List<? extends FoxEntity> list = this.badger.world.getNonSpectatingEntities(FoxEntity.class, this.badger.getBoundingBox().expand(8.0D, 4.0D, 8.0D));
            double d = 1.7976931348623157E308D;

            for (FoxEntity fox2 : list) {
                if (fox2.isBaby()) {
                    double e = this.badger.squaredDistanceTo(fox2);
                    if (!(e > d)) {
                        d = e;
                        fox = fox2;
                    }
                }
            }

            if (d < 9.0D || fox == null) {
                return false;
            } else {
                this.foxEntity = fox;
                return true;
            }
        }
    }

    @Override
    public boolean shouldContinue() {
        return this.foxEntity != null && this.foxEntity.isAlive() && !badger.isBaby();
    }

    @Override
    public void stop() {
        this.foxEntity = null;
    }

    @Override
    public void tick() {
        if (this.badger.world instanceof ServerWorld serverWorld) {
            this.badger.getLookControl().lookAt(foxEntity);
            if (this.badger.getRandom().nextFloat() <= 0.25F) {
                serverWorld.spawnParticles(ParticleTypes.HEART, badger.getX(), badger.getY() + 0.75D, badger.getZ(), 1, 0.0D, 0.0D, 0.0D, 1.0F);
            }

            if (this.badger.getRandom().nextFloat() <= 0.15F) {
                this.badger.getJumpControl().setActive();
            }
        }
        this.timer++;
        if (--this.delay <= 0) {
            this.delay = this.getTickCount(10);
            if (this.foxEntity != null) {
                this.badger.getNavigation().startMovingTo(this.foxEntity, this.speed);
            }
        }
    }
}

