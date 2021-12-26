package com.ninni.badgers.client.render;

import com.ninni.badgers.Badgers;
import com.ninni.badgers.client.init.BadgersEntityModelLayers;
import com.ninni.badgers.client.model.BadgerEntityModel;
import com.ninni.badgers.entity.BadgerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class BadgerEntityRenderer<T extends LivingEntity> extends MobEntityRenderer<BadgerEntity, BadgerEntityModel> {
    public static final Identifier TEXTURE = new Identifier(Badgers.MOD_ID, "textures/entity/badger/badger.png");
    public static final Identifier SLEEPING_TEXTURE = new Identifier(Badgers.MOD_ID, "textures/entity/badger/badger_sleeping.png");

    public BadgerEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new BadgerEntityModel(ctx.getPart(BadgersEntityModelLayers.BADGER)), 0.5F);
    }

    @Override
    protected void setupTransforms(BadgerEntity entity, MatrixStack matrixStack, float f, float g, float h) {
        super.setupTransforms(entity, matrixStack, f, g, h);
    }

    @Override
    public Identifier getTexture(BadgerEntity entity) {
        return entity.isSleeping() ? SLEEPING_TEXTURE : TEXTURE;
    }
}
