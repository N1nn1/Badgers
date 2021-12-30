package com.ninni.badgers.client.render;

import com.ninni.badgers.Badgers;
import com.ninni.badgers.client.model.BadgersEntityModelLayers;
import com.ninni.badgers.client.model.entity.BadgerEntityModel;
import com.ninni.badgers.entity.BadgerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class BadgerEntityRenderer<T extends BadgerEntity> extends MobEntityRenderer<T, BadgerEntityModel<T>> {
    public static final Identifier TEXTURE = new Identifier(Badgers.MOD_ID, "textures/entity/badger/badger.png");
    public static final Identifier SLEEPING_TEXTURE = new Identifier(Badgers.MOD_ID, "textures/entity/badger/badger_sleeping.png");

    public BadgerEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new BadgerEntityModel<>(ctx.getPart(BadgersEntityModelLayers.BADGER)), 0.5F);
    }

    @Override
    protected void scale(BadgerEntity badger, MatrixStack matrixStack, float f) {
        matrixStack.scale(1.25F, 1.25F, 1.25F);
    }

    @Override
    public Identifier getTexture(T entity) {
        return entity.isSleeping() ? SLEEPING_TEXTURE : TEXTURE;
    }
}
