package dev.dhyces.prizemachines;

import dev.dhyces.prizemachines.registry.ItemRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.CompositeModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelGen extends ItemModelProvider {

    public ModItemModelGen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PrizeMachines.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ItemRegistry.PRIZE_TOKEN.get());

        prizeMachine(ItemRegistry.EQUIPMENT_PRIZE_MACHINE.get());
        prizeMachine(ItemRegistry.BLOCK_PRIZE_MACHINE.get());
    }

    protected void prizeMachine(Item blockItem) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(blockItem);
        withExistingParent(id.getPath(), mcLoc("block/block")).customLoader(CompositeModelBuilder::begin)
                .child("upper", (ItemModelBuilder) new ItemModelBuilder(id.withPrefix("item/").withSuffix("_upper"), existingFileHelper)
                        .parent(new ModelFile.ExistingModelFile(id.withPrefix("block/").withSuffix("_upper"), existingFileHelper))
                        .renderType("cutout")
                        .rootTransforms()
                                .translation(0, 1, 0)
                                .end()
                )
                .child("lower", new ItemModelBuilder(id.withPrefix("item/").withSuffix("_lower"), existingFileHelper)
                        .parent(new ModelFile.ExistingModelFile(id.withPrefix("block/").withSuffix("_lower"), existingFileHelper))
                )
                .end()
                .transforms()
                .transform(ItemDisplayContext.GUI)
                .rotation(30, 225, 0)
                .translation(0, -3f, 0)
                .scale(0.4f)
                .end()
                .end();
    }
}


