package dev.dhyces.prizemachines.registry;

import dev.dhyces.prizemachines.PrizeMachines;
import dev.dhyces.prizemachines.blocks.PrizeMachineBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(Registries.BLOCK, PrizeMachines.MODID);

    public static final TagKey<Block> PRIZE_MACHINES = REGISTER.createTagKey("prize_machines");

    public static final RegistryObject<PrizeMachineBlock> EQUIPMENT_PRIZE_MACHINE = REGISTER.register("equipment_prize_machine", () -> new PrizeMachineBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<PrizeMachineBlock> BLOCK_PRIZE_MACHINE = REGISTER.register("block_prize_machine", () -> new PrizeMachineBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
}
