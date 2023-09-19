package dev.dhyces.prizemachines.loot.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import dev.dhyces.prizemachines.registry.LootItemFunctionRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.Optional;

public class SetRandomPotionFunction extends LootItemConditionalFunction {
    protected SetRandomPotionFunction(LootItemCondition[] pPredicates) {
        super(pPredicates);
    }

    @Override
    protected ItemStack run(ItemStack pStack, LootContext pContext) {
        Optional<Holder.Reference<Potion>> potionOptional;
        while ((potionOptional = BuiltInRegistries.POTION.getRandom(pContext.getRandom())).isPresent() && potionOptional.get().is(Potions.EMPTY_ID));
        return potionOptional.map(Holder::value).map(potion -> PotionUtils.setPotion(pStack, potion)).get();
    }

    public static Builder randomPotion() {
        return new Builder();
    }

    @Override
    public LootItemFunctionType getType() {
        return LootItemFunctionRegistry.SET_RANDOM_POTION.get();
    }

    public static final class Builder extends LootItemConditionalFunction.Builder<Builder> {
        @Override
        protected Builder getThis() {
            return this;
        }

        @Override
        public LootItemFunction build() {
            return new SetRandomPotionFunction(getConditions());
        }
    }

    public static final class Serializer extends LootItemConditionalFunction.Serializer<SetRandomPotionFunction> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public SetRandomPotionFunction deserialize(JsonObject pObject, JsonDeserializationContext pDeserializationContext, LootItemCondition[] pConditions) {
            return new SetRandomPotionFunction(pConditions);
        }
    }
}
