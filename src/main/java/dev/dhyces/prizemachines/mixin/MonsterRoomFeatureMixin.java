package dev.dhyces.prizemachines.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.serialization.Codec;
import dev.dhyces.prizemachines.blocks.PrizeMachineBlock;
import dev.dhyces.prizemachines.registry.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.MonsterRoomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;
import java.util.function.Predicate;

@Debug(export = true)
@Mixin(MonsterRoomFeature.class)
public abstract class MonsterRoomFeatureMixin extends Feature<NoneFeatureConfiguration> {

    public MonsterRoomFeatureMixin(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @WrapOperation(method = "place", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/RandomizableContainerBlockEntity;setLootTable(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/BlockPos;Lnet/minecraft/resources/ResourceLocation;)V"))
    private void prizemachines$placePrizeMachine(BlockGetter pLevel, RandomSource pRandom, BlockPos pPos, ResourceLocation pLootTable, Operation<Void> original) {
        // TODO: change this to not replace a chest and instead be its own thing
        if (pRandom.nextFloat() <= 0.5) {
            getRandomPrizeMachine(pRandom).ifPresent(state -> {
                Predicate<BlockState> statePredicate = Feature.isReplaceable(BlockTags.FEATURES_CANNOT_REPLACE).or(testState -> testState.is(Blocks.CHEST));
                if (statePredicate.test(pLevel.getBlockState(pPos)) && statePredicate.test(pLevel.getBlockState(pPos.above()))) {
                    WorldGenLevel worldGenLevel = (WorldGenLevel) pLevel;
                    state = StructurePiece.reorient(pLevel, pPos, state);
                    state = state.setValue(PrizeMachineBlock.FACING, state.getValue(PrizeMachineBlock.FACING).getOpposite());
                    worldGenLevel.setBlock(pPos, state, 2);
                    worldGenLevel.setBlock(pPos.above(), state.setValue(PrizeMachineBlock.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER), 2);
                }
            });
        } else {
            original.call(pLevel, pRandom, pPos, pLootTable);
        }
    }

    private static Optional<BlockState> getRandomPrizeMachine(RandomSource pRandom) {
        return BuiltInRegistries.BLOCK.getTag(BlockRegistry.PRIZE_MACHINES)
                .flatMap(holders -> holders.getRandomElement(pRandom))
                .map(Holder::get)
                .map(Block::defaultBlockState);
    }
}
