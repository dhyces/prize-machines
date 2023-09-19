package dev.dhyces.prizemachines.loot.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dhyces.prizemachines.registry.LootItemFunctionRegistry;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.ArrayList;
import java.util.List;

public class SetRandomFireworkStarFunction extends LootItemConditionalFunction {
    protected SetRandomFireworkStarFunction(LootItemCondition[] pPredicates) {
        super(pPredicates);
    }

    @Override
    protected ItemStack run(ItemStack pStack, LootContext pContext) {
        if (pStack.is(Items.FIREWORK_STAR)) {
            CompoundTag tag = generateExplosion(pContext.getRandom());
            pStack.getOrCreateTag().put("Explosion", tag);
        } else if (pStack.is(Items.FIREWORK_ROCKET)) {
            RandomSource random = pContext.getRandom();
            int numExplosions = random.nextInt(1, 4);
            ListTag tag = new ListTag();
            for (int i = 0; i < numExplosions; i++) {
                CompoundTag compoundTag = generateExplosion(random);
                tag.add(compoundTag);
            }
            pStack.getOrCreateTagElement("Fireworks").put("Explosions", tag);
        }

        return pStack;
    }

    private CompoundTag generateExplosion(RandomSource random) {
        int numColors = random.nextInt(1, 5);
        List<DyeColor> colors = new ArrayList<>();
        for (int i = 0; i < numColors; i++) {
            colors.add(Util.getRandom(DyeColor.values(), random));
        }
        int numFadeColors = random.nextInt(3);
        List<DyeColor> fadeColors = new ArrayList<>();
        for (int i = 0; i < numFadeColors; i++) {
            fadeColors.add(Util.getRandom(DyeColor.values(), random));
        }
        FireworkRocketItem.Shape shape = Util.getRandom(FireworkRocketItem.Shape.values(), random);
        boolean hasTrail = random.nextBoolean();
        boolean hasFlicker = random.nextBoolean();
        CompoundTag tag = new CompoundTag();
        tag.putByte("Type", (byte) shape.getId());
        tag.putString("forge:shape_type", shape.name());
        tag.putIntArray("Colors", colors.stream().mapToInt(DyeColor::getFireworkColor).toArray());
        tag.putIntArray("FadeColors", fadeColors.stream().mapToInt(DyeColor::getFireworkColor).toArray());
        tag.putBoolean("Trails", hasTrail);
        tag.putBoolean("Flicker", hasFlicker);
        return tag;
    }

    public static Builder randomFireworkStar() {
        return new Builder();
    }

    @Override
    public LootItemFunctionType getType() {
        return LootItemFunctionRegistry.SET_RANDOM_FIREWORK_STAR.get();
    }

    public static final class Builder extends LootItemConditionalFunction.Builder<Builder> {

        @Override
        protected Builder getThis() {
            return this;
        }

        @Override
        public LootItemFunction build() {
            return new SetRandomFireworkStarFunction(getConditions());
        }
    }

    public static final class Serializer extends LootItemConditionalFunction.Serializer<SetRandomFireworkStarFunction> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public SetRandomFireworkStarFunction deserialize(JsonObject pObject, JsonDeserializationContext pDeserializationContext, LootItemCondition[] pConditions) {
            return new SetRandomFireworkStarFunction(pConditions);
        }
    }
}
