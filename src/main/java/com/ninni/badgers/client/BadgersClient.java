package com.ninni.badgers.client;

import com.google.common.collect.ImmutableMap;
import com.ninni.badgers.block.BadgersBlocks;
import com.ninni.badgers.client.model.BadgersEntityModelLayers;
import com.ninni.badgers.client.model.entity.BadgerEntityModel;
import com.ninni.badgers.client.render.BadgerEntityRenderer;
import com.ninni.badgers.entity.BadgersEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;

@Environment(EnvType.CLIENT)
public class BadgersClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(BadgersEntities.BADGER, BadgerEntityRenderer::new);

		new ImmutableMap.Builder<EntityModelLayer, EntityModelLayerRegistry.TexturedModelDataProvider>()
			.put(BadgersEntityModelLayers.BADGER, BadgerEntityModel::getTexturedModelData)
			.build().forEach(EntityModelLayerRegistry::registerModelLayer);

		BlockRenderLayerMap.INSTANCE.putBlocks(
			RenderLayer.getCutout(),
			BadgersBlocks.SOUR_BERRY_BUSH
		);
	}
}