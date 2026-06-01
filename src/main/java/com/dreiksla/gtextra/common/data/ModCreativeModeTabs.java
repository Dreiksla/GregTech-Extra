package com.dreiksla.gtextra.common.data;

import net.minecraftforge.eventbus.api.IEventBus;

import com.dreiksla.gtextra.GTExtra;
import com.dreiksla.gtextra.common.data.machines.GTExtraMultiMachines;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GTExtra.MOD_ID);

    public static final RegistryObject<CreativeModeTab> GTDICore_TAB = CREATIVE_MODE_TABS.register("gtextra_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.NETHERITE_BLOCK))
                    .title(Component.translatable("creativetab.gtextra_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(GTExtraMultiMachines.MEGA_CHEMICAL_REACTOR.asStack());
                        pOutput.accept(GTExtraMultiMachines.GREENHOUSE.asStack());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}