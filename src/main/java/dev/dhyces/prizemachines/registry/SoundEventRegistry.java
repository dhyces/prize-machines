package dev.dhyces.prizemachines.registry;

import dev.dhyces.prizemachines.PrizeMachines;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class SoundEventRegistry {
    public static final DeferredRegister<SoundEvent> REGISTER = DeferredRegister.create(Registries.SOUND_EVENT, PrizeMachines.MODID);

    public static final RegistryObject<SoundEvent> AWARDED_PRIZE = REGISTER.register("awarded_prize", () -> SoundEvent.createVariableRangeEvent(PrizeMachines.MOD_RL.withPath("block.prizemachines.awarded_prize")));
}
