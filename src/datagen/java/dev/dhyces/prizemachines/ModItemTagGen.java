package dev.dhyces.prizemachines;

import dev.dhyces.prizemachines.registry.BlockRegistry;
import dev.dhyces.prizemachines.registry.ItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGen extends ItemTagsProvider {
    public ModItemTagGen(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, PrizeMachines.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        copy(BlockRegistry.PRIZE_MACHINES, ItemRegistry.PRIZE_MACHINES);

        tag(ItemRegistry.TOKENS).add(ItemRegistry.PRIZE_TOKEN.get());
        tag(ItemRegistry.PRIZE_BLACKLIST)
                .add(
                        Items.ENDER_DRAGON_SPAWN_EGG,
                        Items.WITHER_SPAWN_EGG,
                        Items.RAVAGER_SPAWN_EGG
                );
    }
}
