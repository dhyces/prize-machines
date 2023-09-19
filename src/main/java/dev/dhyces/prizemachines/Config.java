package dev.dhyces.prizemachines;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec;

public class Config {

    private static ForgeConfigSpec.BooleanValue dungeonsHavePrizeMachines;
    private static ForgeConfigSpec.DoubleValue prizeMachineDungeonChance;

    public static ForgeConfigSpec buildServerSpec() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("Server");
        dungeonsHavePrizeMachines = builder.comment("Determines whether prize machines attempt to generate in dungeons. Default: true")
                .define("dungeonsHavePrizeMachines", true);
        prizeMachineDungeonChance = builder.comment("Chance of a prize machine being placed for any spot against a cobblestone wall in a dungeon. Default: 0.25")
                .defineInRange("prizeMachineDungeonChance", 0.25, 0, 1);

        return builder.build();
    }

    public static boolean dungeonPrizeMachines() {
        return dungeonsHavePrizeMachines.get();
    }

    public static float dungeonPrizeMachineChance() {
        return prizeMachineDungeonChance.get().floatValue();
    }
}
