package dev.dhyces.prizemachines.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.mojang.serialization.Codec;
import dev.dhyces.prizemachines.Config;
import dev.dhyces.prizemachines.blocks.PrizeMachineBlock;
import dev.dhyces.prizemachines.registry.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.MonsterRoomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.function.Predicate;

@Mixin(MonsterRoomFeature.class)
public abstract class MonsterRoomFeatureMixin extends Feature<NoneFeatureConfiguration> {

    public MonsterRoomFeatureMixin(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Inject(method = "place", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Direction$Plane;iterator()Ljava/util/Iterator;"))
    private void prizemachines$placePrizeMachine(FeaturePlaceContext<NoneFeatureConfiguration> pContext, CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 1) LocalRef<BlockPos> blockPos2) {
        if (Config.dungeonPrizeMachines()) {
            RandomSource random = pContext.random();
            WorldGenLevel level = pContext.level();
            BlockPos pos = blockPos2.get();

            getRandomPrizeMachine(random).ifPresent(state -> {
                Predicate<BlockState> statePredicate = Feature.isReplaceable(BlockTags.FEATURES_CANNOT_REPLACE).or(testState -> testState.is(Blocks.CHEST));
                for (Direction dir : Direction.Plane.HORIZONTAL) {
                    BlockPos offPos = pos.relative(dir);
                    if (random.nextFloat() <= Config.dungeonPrizeMachineChance()) {
                        if (level.getBlockState(offPos).is(Blocks.COBBLESTONE)) {
                            if (statePredicate.test(level.getBlockState(pos)) && statePredicate.test(level.getBlockState(pos.above()))) {
                                state = state.setValue(PrizeMachineBlock.FACING, dir);
                                level.setBlock(pos, state, Block.UPDATE_CLIENTS);
                                level.setBlock(pos.above(), state.setValue(PrizeMachineBlock.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER), Block.UPDATE_CLIENTS);
                                return;
                            }
                        }
                    }
                }
            });
        }
    }

    private Optional<BlockState> getRandomPrizeMachine(RandomSource pRandom) {
        return BuiltInRegistries.BLOCK.getTag(BlockRegistry.PRIZE_MACHINES)
                .flatMap(holders -> holders.getRandomElement(pRandom))
                .map(Holder::get)
                .map(Block::defaultBlockState);
    }
}
