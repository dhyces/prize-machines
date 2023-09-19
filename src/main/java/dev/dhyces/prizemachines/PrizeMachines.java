package dev.dhyces.prizemachines;

import com.mojang.datafixers.util.Pair;
import dev.dhyces.prizemachines.mixin.StructureTemplatePoolAccessor;
import dev.dhyces.prizemachines.registry.*;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.levelgen.structure.pools.ListPoolElement;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.ArrayList;
import java.util.List;

@Mod(PrizeMachines.MODID)
public class PrizeMachines {
    public static final String MODID = "prizemachines";
    public static final ResourceLocation MOD_RL = new ResourceLocation(MODID, MODID);

    public PrizeMachines() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        BlockRegistry.REGISTER.register(modBus);
        ItemRegistry.REGISTER.register(modBus);
        SoundEventRegistry.REGISTER.register(modBus);
        CreativeModeTabRegistry.REGISTER.register(modBus);
        LootItemFunctionRegistry.REGISTER.register(modBus);
        GlobalLootModifierRegistry.REGISTER.register(modBus);

        forgeBus.addListener(this::addStructures);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.buildServerSpec());
    }

    public static final ResourceLocation BLOCK_PRIZE_MACHINE_STRUCTURE = PrizeMachines.MOD_RL.withPath("block_prize_machine");
    public static final ResourceLocation EQUIPMENT_PRIZE_MACHINE_STRUCTURE = PrizeMachines.MOD_RL.withPath("equipment_prize_machine");

    private void addStructures(final ServerAboutToStartEvent event) {
        MinecraftServer server = event.getServer();

        Registry<StructureTemplatePool> templatePoolRegistry = server.registryAccess().registryOrThrow(Registries.TEMPLATE_POOL);

        SinglePoolElement blockMachine = StructurePoolElement.single(BLOCK_PRIZE_MACHINE_STRUCTURE.toString()).apply(StructureTemplatePool.Projection.RIGID);
        SinglePoolElement equipmentMachine = StructurePoolElement.single(EQUIPMENT_PRIZE_MACHINE_STRUCTURE.toString()).apply(StructureTemplatePool.Projection.RIGID);

        addToAllVillageHouses(templatePoolRegistry, blockMachine, 1);
        addToAllVillageHouses(templatePoolRegistry, equipmentMachine, 1);
    }

    private <T extends StructurePoolElement> void addToAllVillageHouses(Registry<StructureTemplatePool> templatePoolRegistry, T poolElement, int weight) {
        StructureTemplatePool plainsPool = templatePoolRegistry.get(new ResourceLocation("village/plains/houses"));
        StructureTemplatePool desertPool = templatePoolRegistry.get(new ResourceLocation("village/desert/houses"));
        StructureTemplatePool savannaPool = templatePoolRegistry.get(new ResourceLocation("village/savanna/houses"));
        StructureTemplatePool snowyPool = templatePoolRegistry.get(new ResourceLocation("village/snowy/houses"));
        StructureTemplatePool taigaPool = templatePoolRegistry.get(new ResourceLocation("village/taiga/houses"));

        addStructureToPool(plainsPool, poolElement, weight);
        addStructureToPool(desertPool, poolElement, weight);
        addStructureToPool(savannaPool, poolElement, weight);
        addStructureToPool(snowyPool, poolElement, weight);
        addStructureToPool(taigaPool, poolElement, weight);
    }

    private <T extends StructurePoolElement> void addStructureToPool(StructureTemplatePool templatePool, T poolElement, int weight) {
        if (templatePool == null) {
            return;
        }

        StructureTemplatePoolAccessor accessor = (StructureTemplatePoolAccessor) templatePool;
        for (int i = 0; i < weight; i++) {
            accessor.getTemplates().add(poolElement);
        }

        List<Pair<StructurePoolElement, Integer>> rawTemplates = new ArrayList<>(accessor.getRawTemplates());
        rawTemplates.add(Pair.of(poolElement, weight));
        accessor.setRawTemplates(rawTemplates);
    }
}
