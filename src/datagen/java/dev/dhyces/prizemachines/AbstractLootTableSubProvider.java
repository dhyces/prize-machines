package dev.dhyces.prizemachines;

import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public abstract class AbstractLootTableSubProvider implements LootTableSubProvider {

    protected final String prefix;
    protected final Map<ResourceLocation, LootTable.Builder> map = new HashMap<>();

    public AbstractLootTableSubProvider(String prefix) {
        this.prefix = prefix;
    }

    protected abstract void generate();

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> pOutput) {
        generate();
        map.forEach(pOutput);
    }

    protected void add(ResourceLocation tableId, LootTable.Builder builder) {
        map.put(tableId.withPrefix(prefix + "/"), builder);
    }
}
