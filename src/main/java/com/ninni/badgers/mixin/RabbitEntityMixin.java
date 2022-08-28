package com.ninni.badgers.mixin;

import com.ninni.badgers.entity.BadgerEntity;
import com.ninni.badgers.entity.ai.goal.PublicRabbitFleeGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RabbitEntity.class)
public abstract class RabbitEntityMixin extends AnimalEntity {
    private RabbitEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initGoals", at = @At("TAIL"))
    private void onInitGoals(CallbackInfo ci) {
        RabbitEntity that = RabbitEntity.class.cast(this);
        this.goalSelector.add(4, new PublicRabbitFleeGoal<>(that, BadgerEntity.class, 10.0F, 2.2D, 2.2D));
    }
}
