package dev.dhyces.prizemachines;

import dev.dhyces.prizemachines.blocks.PrizeMachineBlock;
import dev.dhyces.prizemachines.loot.functions.SetRandomFireworkStarFunction;
import dev.dhyces.prizemachines.loot.functions.SetRandomPotionFunction;
import dev.dhyces.prizemachines.registry.BlockRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.*;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.ExplorationMapFunction;
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction;
import net.minecraft.world.level.storage.loot.functions.SetStewEffectFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ModPrizeLootGen extends AbstractLootTableSubProvider {

    public ModPrizeLootGen() {
        super("prizes");
    }

    @Override
    protected void generate() {
        ResourceLocation localPotionsTable = PrizeMachines.MOD_RL.withPath("items_with_potions");
        add(localPotionsTable, LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .add(
                                LootItem.lootTableItem(Items.POTION)
                                        .apply(SetRandomPotionFunction.randomPotion())
                        )
                        .add(
                                LootItem.lootTableItem(Items.SPLASH_POTION)
                                        .apply(SetRandomPotionFunction.randomPotion())
                        )
                        .add(
                                LootItem.lootTableItem(Items.LINGERING_POTION)
                                        .apply(SetRandomPotionFunction.randomPotion())
                        )
                        .add(
                                LootItem.lootTableItem(Items.TIPPED_ARROW)
                                        .apply(SetRandomPotionFunction.randomPotion())
                        )
                        .add(
                                LootItem.lootTableItem(Items.SUSPICIOUS_STEW)
                                        .apply(
                                                SetStewEffectFunction.stewEffect()
                                                        .withEffect(MobEffects.ABSORPTION, UniformGenerator.between(60, 120))
                                                        .withEffect(MobEffects.DAMAGE_BOOST, UniformGenerator.between(60, 120))
                                                        .withEffect(MobEffects.FIRE_RESISTANCE, UniformGenerator.between(60, 120))
                                                        .withEffect(MobEffects.WATER_BREATHING, UniformGenerator.between(60, 120))
                                                        .withEffect(MobEffects.JUMP, UniformGenerator.between(60, 120))
                                                        .withEffect(MobEffects.MOVEMENT_SPEED, UniformGenerator.between(60, 120))
                                                        .withEffect(MobEffects.REGENERATION, UniformGenerator.between(60, 120))
                                        )
                        )
        ));

        ResourceLocation localFireworks = PrizeMachines.MOD_RL.withPath("fireworks");
        add(localFireworks, LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .add(
                                LootItem.lootTableItem(Items.FIREWORK_ROCKET)
                                        .apply(SetNbtFunction.setTag(createFireworkFlightTag(1)))
                        )
                        .add(
                                LootItem.lootTableItem(Items.FIREWORK_ROCKET)
                                        .apply(SetNbtFunction.setTag(createFireworkFlightTag(2)))
                        )
                        .add(
                                LootItem.lootTableItem(Items.FIREWORK_ROCKET)
                                        .apply(SetNbtFunction.setTag(createFireworkFlightTag(3)))
                        )
                        .add(
                                LootItem.lootTableItem(Items.FIREWORK_ROCKET)
                                        .apply(SetRandomFireworkStarFunction.randomFireworkStar())
                        )
                        .add(
                                LootItem.lootTableItem(Items.FIREWORK_ROCKET)
                                        .apply(SetRandomFireworkStarFunction.randomFireworkStar())
                        )
                        .add(
                                LootItem.lootTableItem(Items.FIREWORK_ROCKET)
                                        .apply(SetRandomFireworkStarFunction.randomFireworkStar())
                        )
                        .add(
                                LootItem.lootTableItem(Items.FIREWORK_ROCKET)
                                        .apply(SetNbtFunction.setTag(createFireworkFlightTag(1)))
                                        .apply(SetRandomFireworkStarFunction.randomFireworkStar())
                        )
                        .add(
                                LootItem.lootTableItem(Items.FIREWORK_ROCKET)
                                        .apply(SetNbtFunction.setTag(createFireworkFlightTag(2)))
                                        .apply(SetRandomFireworkStarFunction.randomFireworkStar())
                        )
                        .add(
                                LootItem.lootTableItem(Items.FIREWORK_ROCKET)
                                        .apply(SetNbtFunction.setTag(createFireworkFlightTag(3)))
                                        .apply(SetRandomFireworkStarFunction.randomFireworkStar())
                        )
        ));

        ResourceLocation localMaps = PrizeMachines.MOD_RL.withPath("maps");
        add(localMaps, LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .add(
                                LootItem.lootTableItem(Items.MAP)
                                        .apply(
                                                ExplorationMapFunction.makeExplorationMap()
                                                        .setDestination(StructureTags.ON_TREASURE_MAPS)
                                                        .setMapDecoration(MapDecoration.Type.RED_X)
                                                        .setSearchRadius(10)
                                        )
                        )
                        .add(
                                LootItem.lootTableItem(Items.MAP)
                                        .apply(
                                                ExplorationMapFunction.makeExplorationMap()
                                                        .setDestination(StructureTags.ON_WOODLAND_EXPLORER_MAPS)
                                                        .setMapDecoration(MapDecoration.Type.MANSION)
                                                        .setSearchRadius(10)
                                        )
                        )
                        .add(
                                LootItem.lootTableItem(Items.MAP)
                                        .apply(
                                                ExplorationMapFunction.makeExplorationMap()
                                                        .setDestination(StructureTags.ON_OCEAN_EXPLORER_MAPS)
                                                        .setMapDecoration(MapDecoration.Type.MONUMENT)
                                                        .setSearchRadius(10)
                                        )
                        )
                        .add(
                                LootItem.lootTableItem(Items.MAP)
                                        .apply(
                                                ExplorationMapFunction.makeExplorationMap()
                                                        .setDestination(StructureTags.RUINED_PORTAL)
                                                        .setMapDecoration(MapDecoration.Type.TARGET_X)
                                                        .setSearchRadius(10)
                                        )
                        )
                        .add(
                                AlternativesEntry.alternatives(
                                        createVillageMap(MapDecoration.Type.BANNER_BLUE)
                                                .when(LootItemRandomChanceCondition.randomChance(0.166f)),
                                        createVillageMap(MapDecoration.Type.BANNER_BROWN)
                                                .when(LootItemRandomChanceCondition.randomChance(0.166f)),
                                        createVillageMap(MapDecoration.Type.BANNER_GREEN)
                                                .when(LootItemRandomChanceCondition.randomChance(0.166f)),
                                        createVillageMap(MapDecoration.Type.BANNER_ORANGE)
                                                .when(LootItemRandomChanceCondition.randomChance(0.166f)),
                                        createVillageMap(MapDecoration.Type.BANNER_LIME)
                                                .when(LootItemRandomChanceCondition.randomChance(0.166f)),
                                        createVillageMap(MapDecoration.Type.BANNER_PINK)
                                                .when(LootItemRandomChanceCondition.randomChance(0.166f))
                                )
                        )
        ));

        addBlock(BlockRegistry.EQUIPMENT_PRIZE_MACHINE.get(),
                LootTable.lootTable().setParamSet(LootContextParamSets.BLOCK).withPool(
                        LootPool.lootPool().name("base_pool")
                                .add(DynamicLoot.dynamicEntry(PrizeMachineBlock.EQUIPMENT_DROP).setWeight(100))
                                .add(LootTableReference.lootTableReference(localPotionsTable.withPrefix("prizes/")).setWeight(1))
                                .add(LootTableReference.lootTableReference(localFireworks.withPrefix("prizes/")).setWeight(1))
                                .add(LootTableReference.lootTableReference(localMaps.withPrefix("prizes/")).setWeight(1))
                                .add(
                                        LootItem.lootTableItem(Items.FIREWORK_STAR)
                                                .apply(SetRandomFireworkStarFunction.randomFireworkStar()).setWeight(1)
                                )
                                .add(
                                        LootItem.lootTableItem(Items.BOOK)
                                                .apply(EnchantRandomlyFunction.randomApplicableEnchantment()).setWeight(1)
                                )
                ));
        addBlock(BlockRegistry.BLOCK_PRIZE_MACHINE.get(),
                LootTable.lootTable().setParamSet(LootContextParamSets.BLOCK).withPool(
                        poolWithDynamic(PrizeMachineBlock.BLOCK_DROP)
                ));
    }

    protected LootPoolSingletonContainer.Builder<?> createVillageMap(MapDecoration.Type marker) {
        return LootItem.lootTableItem(Items.MAP).apply(
                ExplorationMapFunction.makeExplorationMap()
                        .setDestination(StructureTags.VILLAGE)
                        .setMapDecoration(marker)
                        .setSearchRadius(10)
        );
    }

    protected CompoundTag createFireworkFlightTag(int flight) {
        CompoundTag tag = new CompoundTag();
        CompoundTag fireworks = new CompoundTag();
        fireworks.putByte("Flight", (byte) flight);
        tag.put("Fireworks", fireworks);
        return tag;
    }

    protected LootPool.Builder poolWithDynamic(ResourceLocation dynamicDropName) {
        return LootPool.lootPool().name("base_pool").add(DynamicLoot.dynamicEntry(dynamicDropName));
    }

    protected void addBlock(Block block, LootTable.Builder builder) {
        add(BuiltInRegistries.BLOCK.getKey(block), builder);
    }
}
