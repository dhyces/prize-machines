package dev.dhyces.prizemachines;

import dev.dhyces.prizemachines.registry.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(PrizeMachines.MODID)
public class PrizeMachines {
    public static final String MODID = "prizemachines";
    public static final ResourceLocation MOD_RL = new ResourceLocation(MODID, MODID);

    public PrizeMachines() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockRegistry.REGISTER.register(modBus);
        ItemRegistry.REGISTER.register(modBus);
        SoundEventRegistry.REGISTER.register(modBus);
        CreativeModeTabRegistry.REGISTER.register(modBus);
        GlobalLootModifierRegistry.REGISTER.register(modBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.buildSpec());
    }

    private void addStructures(final ServerAboutToStartEvent event) {
        // TODO: create village structure and add it to pool
        MinecraftServer server = event.getServer();
    }
}
