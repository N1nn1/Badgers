package com.ninni.badgers.client.particle;

import com.ninni.badgers.Badgers;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BadgersParticles {
    public static final DefaultParticleType SOUR_BERRY = Registry.register(Registry.PARTICLE_TYPE, new Identifier(Badgers.MOD_ID, "sour_berry"), FabricParticleTypes.simple());
}
