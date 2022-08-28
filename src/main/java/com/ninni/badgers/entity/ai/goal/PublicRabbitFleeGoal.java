package com.ninni.badgers.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.passive.RabbitEntity;

public class PublicRabbitFleeGoal<T extends LivingEntity> extends FleeEntityGoal<T> {
    private final RabbitEntity rabbit;

    public PublicRabbitFleeGoal(RabbitEntity rabbit, Class<T> fleeFromType, float distance, double slowSpeed, double fastSpeed) {
        super(rabbit, fleeFromType, distance, slowSpeed, fastSpeed);
        this.rabbit = rabbit;
    }

    public boolean canStart() {
        return this.rabbit.getRabbitType() != 99 && super.canStart();
    }
}
