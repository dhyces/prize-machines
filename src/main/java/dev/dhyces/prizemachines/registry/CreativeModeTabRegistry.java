package dev.dhyces.prizemachines.registry;

import dev.dhyces.prizemachines.PrizeMachines;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeModeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PrizeMachines.MODID);

    public static final RegistryObject<CreativeModeTab> MOD_TAB = REGISTER.register("prize_machines", CreativeModeTabRegistry::createModTab);


    private static CreativeModeTab createModTab() {
        return CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.prizemachines.prize_machines"))
                .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                .icon(() -> ItemRegistry.BLOCK_PRIZE_MACHINE.get().getDefaultInstance())
                .displayItems((pParameters, pOutput) -> {
                    pOutput.accept(ItemRegistry.EQUIPMENT_PRIZE_MACHINE.get());
                    pOutput.accept(ItemRegistry.BLOCK_PRIZE_MACHINE.get());
                    pOutput.accept(ItemRegistry.PRIZE_TOKEN.get());
                }).build();
    }
}
