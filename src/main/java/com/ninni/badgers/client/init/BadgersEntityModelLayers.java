package com.ninni.badgers.client.init;

import com.ninni.badgers.Badgers;
import com.ninni.badgers.mixin.client.EntityModelLayersInvoker;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class BadgersEntityModelLayers {
    public static final EntityModelLayer BADGER = registerMain("badger");

    private static EntityModelLayer registerMain(String id) {
        return EntityModelLayersInvoker.register(new Identifier(Badgers.MOD_ID, id).toString(), "main");
    }

    private static EntityModelLayer register(String id, String layer) {
        return EntityModelLayersInvoker.register(new Identifier(Badgers.MOD_ID, id).toString(), layer);
    }
}
