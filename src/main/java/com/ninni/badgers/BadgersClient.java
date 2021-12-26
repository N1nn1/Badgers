package com.ninni.badgers;

import com.google.common.collect.ImmutableMap;
import com.ninni.badgers.client.init.BadgersEntityModelLayers;
import com.ninni.badgers.client.model.BadgerEntityModel;
import com.ninni.badgers.client.render.BadgerEntityRenderer;
import com.ninni.badgers.init.BadgersEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class BadgersClient implements ClientModInitializer {

	@Override
	@SuppressWarnings("deprecation")
	public void onInitializeClient() {
		EntityRendererRegistry erri = EntityRendererRegistry.INSTANCE;
		erri.register(BadgersEntities.BADGER, BadgerEntityRenderer::new);

		new ImmutableMap.Builder<EntityModelLayer, EntityModelLayerRegistry.TexturedModelDataProvider>()
			.put(BadgersEntityModelLayers.BADGER, BadgerEntityModel::getTexturedModelData)
			.build().forEach(EntityModelLayerRegistry::registerModelLayer);
	}
}
