package dev.dhyces.prizemachines;

import dev.dhyces.prizemachines.registry.BlockRegistry;
import dev.dhyces.prizemachines.registry.CreativeModeTabRegistry;
import dev.dhyces.prizemachines.registry.ItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = PrizeMachines.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Datagen {

    @SubscribeEvent
    static void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();

        CompletableFuture<TagsProvider.TagLookup<Block>> tagLookup = generator.addProvider(event.includeServer(), new ModBlockTagGen(packOutput, lookupProvider, fileHelper)).contentsGetter();
        generator.addProvider(event.includeServer(), new ModItemTagGen(packOutput, lookupProvider, tagLookup, fileHelper));
        List<LootTableProvider.SubProviderEntry> entries = List.of(
                new LootTableProvider.SubProviderEntry(ModBlockLootGen::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(ModPrizeLootGen::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(ModInjectedLootGen::new, LootContextParamSets.CHEST)
        );
        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Set.of(), entries));
        generator.addProvider(event.includeServer(), new ModGlobalLootModifierGen(packOutput));

        generator.addProvider(event.includeClient(), new ModBlockStateGen(packOutput, fileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelGen(packOutput, fileHelper));
        generator.addProvider(event.includeClient(), new ModLangGen(packOutput, "en_us", languageProvider -> {
            languageProvider.add(BlockRegistry.EQUIPMENT_PRIZE_MACHINE.get(), "Equipment Prize Machine");
            languageProvider.add(BlockRegistry.BLOCK_PRIZE_MACHINE.get(), "Block Prize Machine");
            languageProvider.add(ItemRegistry.PRIZE_TOKEN.get(), "Prize Token");
            languageProvider.add(CreativeModeTabRegistry.MOD_TAB.get(), "Prize Machines");
            languageProvider.add("block.prizemachines.awarded_prize", "Prize machine dings");
            languageProvider.add("info.prizemachines.awarded", "Congratulations! You won a %s!");
        }));
    }
}
