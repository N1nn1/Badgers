package com.ninni.badgers.client.render;

import com.ninni.badgers.Badgers;
import com.ninni.badgers.client.init.BadgersEntityModelLayers;
import com.ninni.badgers.client.model.BadgerEntityModel;
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

    public BadgerEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new BadgerEntityModel<>(ctx.getPart(BadgersEntityModelLayers.BADGER)), 0.3F);
    }

    @Override
    public Identifier getTexture(T entity) {
        return TEXTURE;
    }
}
