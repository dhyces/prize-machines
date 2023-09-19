package dev.dhyces.prizemachines;

import dev.dhyces.prizemachines.registry.ItemRegistry;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ModInjectedLootGen extends AbstractLootTableSubProvider {

    public ModInjectedLootGen() {
        super("injected");
    }

    @Override
    protected void generate() {
        add(PrizeMachines.MOD_RL.withPath("common_prize_tokens"), LootTable.lootTable().withPool(
                LootPool.lootPool().add(
                        LootItem.lootTableItem(ItemRegistry.PRIZE_TOKEN.get())
                                .when(LootItemRandomChanceCondition.randomChance(0.75f))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 6), true))
                ).setBonusRolls(UniformGenerator.between(0, 2))
        ));

        add(PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"), LootTable.lootTable().withPool(
                LootPool.lootPool().add(
                        LootItem.lootTableItem(ItemRegistry.PRIZE_TOKEN.get())
                                .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.05f, 2.0f))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4)))
                )
        ));
    }
}
