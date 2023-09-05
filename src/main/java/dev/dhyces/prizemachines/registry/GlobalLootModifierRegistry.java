package dev.dhyces.prizemachines.registry;

import com.mojang.serialization.Codec;
import dev.dhyces.prizemachines.PrizeMachines;
import dev.dhyces.prizemachines.lootmodifiers.LootTableLootModifier;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GlobalLootModifierRegistry {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> REGISTER = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, PrizeMachines.MODID);

    public static final RegistryObject<Codec<LootTableLootModifier>> LOOT_TABLE = REGISTER.register("loot_table_inject", () -> LootTableLootModifier.CODEC);
}
