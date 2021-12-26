package com.ninni.badgers.init;

import com.ninni.badgers.Badgers;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BadgersSoundEvents {

    public static final SoundEvent ENTITY_BADGER_AMBIENT = badger("ambient");
    public static final SoundEvent ENTITY_BADGER_BITE = badger("bite");
    public static final SoundEvent ENTITY_BADGER_HURT = badger("hurt");
    public static final SoundEvent ENTITY_BADGER_DEATH = badger("death");
    private static SoundEvent badger(String id) {
        return createEntitySound("badger", id);
    }


    private static SoundEvent register(String id) {
        Identifier identifier = new Identifier(Badgers.MOD_ID, id);
        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }

    private static SoundEvent createEntitySound(String entity, String id) {
        return register("entity." + entity + "." + id);
    }
}