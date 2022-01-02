package com.ninni.badgers.entity.ai.goal;

import com.ninni.badgers.entity.BadgerEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

import java.util.List;

public class PlayWithBabyBadgerGoal extends Goal {
    private final FoxEntity fox;
    private BadgerEntity badgerEntity;
    private int delay;
    private final double speed;
    private int timer;

    public PlayWithBabyBadgerGoal(FoxEntity fox, double speed) {
        this.fox = fox;
        this.speed = speed;
    }

    @Override
    public boolean canStart() {
        if (!this.fox.isBaby()) {
            return false;
        } else {
            BadgerEntity badger = null;
            List<? extends BadgerEntity> list = this.fox.world.getNonSpectatingEntities(BadgerEntity.class, this.fox.getBoundingBox().expand(8.0D, 4.0D, 8.0D));
            double d = 1.7976931348623157E308D;

            for (BadgerEntity badger2 : list) {
                if (badger2.isBaby()) {
                    double e = this.fox.squaredDistanceTo(badger2);
                    if (!(e > d)) {
                        d = e;
                        badger = badger2;
                    }
                }
            }

            if (d < 9.0D || badger == null) {
                return false;
            } else {
                this.badgerEntity = badger;
                return true;
            }
        }
    }

    @Override
    public boolean shouldContinue() {
        return this.badgerEntity != null && this.badgerEntity.isAlive() && !fox.isBaby();
    }

    @Override
    public void stop() {
        this.badgerEntity = null;
    }

    @Override
    public void tick() {
        if (this.fox.world instanceof ServerWorld serverWorld) {
            this.fox.getLookControl().lookAt(badgerEntity);
            if (this.fox.getRandom().nextFloat() <= 0.25F) {
                serverWorld.spawnParticles(ParticleTypes.HEART, fox.getX(), fox.getY() + 0.75D, fox.getZ(), 1, 0.0D, 0.0D, 0.0D, 1.0F);
            }
            if (this.fox.getRandom().nextFloat() <= 0.15F) {
                this.fox.getJumpControl().setActive();
            }
        }
        this.timer++;
        if (--this.delay <= 0) {
            this.delay = this.getTickCount(10);
            if (this.badgerEntity != null) {
                this.fox.getNavigation().startMovingTo(this.badgerEntity, this.speed);
            }
        }
    }
}

