package dev.dhyces.prizemachines;

import dev.dhyces.prizemachines.registry.BlockRegistry;
import dev.dhyces.prizemachines.registry.ItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.*;
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
        CreativeModeTab opTab = BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.OP_BLOCKS);
        opTab.buildContents(new CreativeModeTab.ItemDisplayParameters(FeatureFlags.REGISTRY.allFlags(), true, pProvider));
        tag(ItemRegistry.PRIZE_BLACKLIST)
                .add(
                        Items.AIR,
                        Items.POTION,
                        Items.SPLASH_POTION,
                        Items.LINGERING_POTION,
                        Items.TIPPED_ARROW,
                        Items.SUSPICIOUS_STEW,
                        Items.WRITTEN_BOOK,
                        Items.FILLED_MAP,
                        Items.ENCHANTED_BOOK,
                        Items.KNOWLEDGE_BOOK,
                        Items.FIREWORK_ROCKET,
                        Items.FIREWORK_STAR,
                        Items.DRAGON_EGG
                )
                .add(BuiltInRegistries.ITEM.stream().filter(item -> item instanceof SpawnEggItem).toArray(Item[]::new))
                .add(
                        opTab.getDisplayItems().stream()
                                .filter(stack -> !stack.is(Items.PAINTING))
                                .map(ItemStack::getItem)
                                .distinct()
                                .toArray(Item[]::new)
                );
    }
}
