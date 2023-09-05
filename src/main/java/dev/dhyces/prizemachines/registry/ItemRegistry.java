package dev.dhyces.prizemachines.registry;

import dev.dhyces.prizemachines.PrizeMachines;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(Registries.ITEM, PrizeMachines.MODID);

    public static final TagKey<Item> PRIZE_MACHINES = REGISTER.createTagKey("prize_machines");
    public static final TagKey<Item> TOKENS = REGISTER.createTagKey("tokens");
    public static final TagKey<Item> PRIZE_BLACKLIST = REGISTER.createTagKey("prize_blacklist");

    public static final RegistryObject<Item> PRIZE_TOKEN = REGISTER.register("prize_token", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EQUIPMENT_PRIZE_MACHINE = REGISTER.register("equipment_prize_machine", () -> new BlockItem(BlockRegistry.EQUIPMENT_PRIZE_MACHINE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BLOCK_PRIZE_MACHINE = REGISTER.register("block_prize_machine", () -> new BlockItem(BlockRegistry.BLOCK_PRIZE_MACHINE.get(), new Item.Properties()));
}
