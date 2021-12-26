package com.ninni.badgers.client.model;

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
        return register(id, "main");
    }

    private static EntityModelLayer register(String id, String layer) {
        return EntityModelLayersInvoker.invoke_register(new Identifier(Badgers.MOD_ID, id).toString(), layer);
    }
}
