package dev.dhyces.prizemachines;

import dev.dhyces.prizemachines.lootmodifiers.LootTableLootModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifierGen extends GlobalLootModifierProvider {
    public ModGlobalLootModifierGen(PackOutput output) {
        super(output, PrizeMachines.MODID);
    }

    @Override
    protected void start() {
        // TODO: add entity GLMs
        addInjected(mcLoc("chests/abandoned_mineshaft"), PrizeMachines.MOD_RL.withPath("common_prize_tokens"));
        addInjected(mcLoc("chests/buried_treasure"), PrizeMachines.MOD_RL.withPath("common_prize_tokens"));
        addInjected(mcLoc("chests/shipwreck_treasure"), PrizeMachines.MOD_RL.withPath("common_prize_tokens"));
        addInjected(mcLoc("chests/simple_dungeon"), PrizeMachines.MOD_RL.withPath("common_prize_tokens"));
        addInjected(mcLoc("chests/underwater_ruin_big"), PrizeMachines.MOD_RL.withPath("common_prize_tokens"));
        addInjected(mcLoc("chests/underwater_ruin_small"), PrizeMachines.MOD_RL.withPath("common_prize_tokens"));
        addInjected(mcLoc("chests/village/village_desert_house"), PrizeMachines.MOD_RL.withPath("common_prize_tokens"));
        addInjected(mcLoc("chests/village/village_plains_house"), PrizeMachines.MOD_RL.withPath("common_prize_tokens"));
        addInjected(mcLoc("chests/village/village_savanna_house"), PrizeMachines.MOD_RL.withPath("common_prize_tokens"));
        addInjected(mcLoc("chests/village/village_snowy_house"), PrizeMachines.MOD_RL.withPath("common_prize_tokens"));
        addInjected(mcLoc("chests/village/village_taiga_house"), PrizeMachines.MOD_RL.withPath("common_prize_tokens"));
        addInjected(mcLoc("chests/ancient_city"), PrizeMachines.MOD_RL.withPath("common_prize_tokens"));
        addInjected(mcLoc("chests/bastion_treasure"), PrizeMachines.MOD_RL.withPath("common_prize_tokens"));
        addInjected(mcLoc("chests/end_city_treasure"), PrizeMachines.MOD_RL.withPath("common_prize_tokens"));
        addInjected(mcLoc("chests/ruined_portal"), PrizeMachines.MOD_RL.withPath("common_prize_tokens"));
        addInjected(mcLoc("chests/stronghold_corridor"), PrizeMachines.MOD_RL.withPath("common_prize_tokens"));
        addInjected(mcLoc("chests/stronghold_crossing"), PrizeMachines.MOD_RL.withPath("common_prize_tokens"));
        addInjected(mcLoc("chests/woodland_mansion"), PrizeMachines.MOD_RL.withPath("common_prize_tokens"));

        addInjected(mcLoc("entities/blaze"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/cave_spider"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/creeper"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/drowned"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
//        addInjected(mcLoc("entities/enderman"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/evoker"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/ghast"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/guardian"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/husk"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/illusioner"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/magma_cube"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/phantom"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
//        addInjected(mcLoc("entities/piglin"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
//        addInjected(mcLoc("entities/piglin_brute"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/pillager"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/ravager"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/shulker"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/silverfish"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/skeleton"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/slime"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/spider"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/stray"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/witch"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/wither_skeleton"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/zombie"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
        addInjected(mcLoc("entities/zombie_villager"), PrizeMachines.MOD_RL.withPath("uncommon_prize_tokens"));
    }

    protected void addInjected(ResourceLocation injectionPoint, ResourceLocation injected) {
        int slash = injectionPoint.getPath().lastIndexOf('/');
        String name = "inject_" + (slash == -1 ? injectionPoint.getPath() : injectionPoint.getPath().substring(slash+1));
        add(name, createInjecting(injectionPoint, injected));
    }

    protected ResourceLocation mcLoc(String id) {
        return new ResourceLocation(id);
    }

    protected LootTableLootModifier createInjecting(ResourceLocation injectionPoint, ResourceLocation injected) {
        return new LootTableLootModifier(fromSingle(LootTableIdCondition.builder(injectionPoint).build()), injected);
    }

    public LootItemCondition[] fromSingle(LootItemCondition condition) {
        return new LootItemCondition[] {condition};
    }
}
