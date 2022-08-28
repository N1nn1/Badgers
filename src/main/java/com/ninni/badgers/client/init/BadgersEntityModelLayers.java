package com.ninni.badgers.client.init;

import com.ninni.badgers.mixin.client.EntityModelLayersInvoker;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import static com.ninni.badgers.Badgers.*;

@Environment(EnvType.CLIENT)
public class BadgersEntityModelLayers {

    public static final EntityModelLayer BADGER = registerMain();

    private static EntityModelLayer registerMain() { return EntityModelLayersInvoker.register(new Identifier(MOD_ID, "badger").toString(), "main"); }
}
