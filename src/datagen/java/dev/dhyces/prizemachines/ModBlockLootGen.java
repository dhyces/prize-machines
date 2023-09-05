package dev.dhyces.prizemachines;

import dev.dhyces.prizemachines.registry.BlockRegistry;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootGen extends BlockLootSubProvider {
    protected ModBlockLootGen() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        // TODO: decide whether it needs iron pick
        dropSelf(BlockRegistry.EQUIPMENT_PRIZE_MACHINE.get());
        dropSelf(BlockRegistry.BLOCK_PRIZE_MACHINE.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlockRegistry.REGISTER.getEntries().stream().map(RegistryObject::get).toList();
    }
}
