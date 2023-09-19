package dev.dhyces.prizemachines.registry;

import dev.dhyces.prizemachines.PrizeMachines;
import dev.dhyces.prizemachines.loot.functions.SetRandomFireworkStarFunction;
import dev.dhyces.prizemachines.loot.functions.SetRandomPotionFunction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class LootItemFunctionRegistry {
    public static final DeferredRegister<LootItemFunctionType> REGISTER = DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, PrizeMachines.MODID);

    public static final RegistryObject<LootItemFunctionType> SET_RANDOM_POTION = REGISTER.register("set_random_potion", () -> new LootItemFunctionType(SetRandomPotionFunction.Serializer.INSTANCE));
    public static final RegistryObject<LootItemFunctionType> SET_RANDOM_FIREWORK_STAR = REGISTER.register("set_random_firework_star", () -> new LootItemFunctionType(SetRandomFireworkStarFunction.Serializer.INSTANCE));
}
