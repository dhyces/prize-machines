package dev.dhyces.prizemachines;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec;

public class Config {

    private static ForgeConfigSpec.BooleanValue blockCreativeItems;

    public static ForgeConfigSpec buildSpec() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("Server");
        blockCreativeItems = builder.comment("Blocks all items which appear in the Operator Blocks creative mode tab. Default: true")
                .define("blockCreativeItems", true);

        return builder.build();
    }

    public static boolean isItemAllowed(ItemStack stack) {
        if (Config.blockCreativeItems.get()) {
            return !BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.OP_BLOCKS).contains(stack);
        }
        return true;
    }
}
