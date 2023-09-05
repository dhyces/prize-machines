package dev.dhyces.prizemachines;

import dev.dhyces.prizemachines.blocks.PrizeMachineBlock;
import dev.dhyces.prizemachines.registry.BlockRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.DynamicLoot;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ModPrizeLootGen extends AbstractLootTableSubProvider {

    @Override
    protected void generate() {
        createPrizeDrop(BlockRegistry.EQUIPMENT_PRIZE_MACHINE.get(), PrizeMachineBlock.EQUIPMENT_DROP);
        createPrizeDrop(BlockRegistry.BLOCK_PRIZE_MACHINE.get(), PrizeMachineBlock.BLOCK_DROP);
    }

    protected void createPrizeDrop(Block block, ResourceLocation dynamicDropName) {
        add(BuiltInRegistries.BLOCK.getKey(block), new LootTable.Builder().setParamSet(LootContextParamSets.BLOCK).withPool(
                LootPool.lootPool().add(
                        DynamicLoot.dynamicEntry(dynamicDropName)
                )
        ));
    }
}
