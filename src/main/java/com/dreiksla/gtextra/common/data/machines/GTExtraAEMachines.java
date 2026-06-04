package com.dreiksla.gtextra.common.data.machines;

import com.dreiksla.gtextra.GTExtra;
import com.dreiksla.gtextra.integration.ae2.machine.MESuperPatternBufferPartMachine;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;

import net.minecraft.network.chat.Component;

import static com.gregtechceu.gtceu.api.GTValues.UV;

public class GTExtraAEMachines {

    public static final MachineDefinition ME_SUPER_PATTERN_BUFFER = GTExtra.REGISTRATE
    .machine("me_super_pattern_buffer", MESuperPatternBufferPartMachine::new)
    .tier(UV)
    .rotationState(RotationState.ALL)
    .abilities(PartAbility.IMPORT_ITEMS, PartAbility.EXPORT_ITEMS, PartAbility.IMPORT_FLUIDS, PartAbility.EXPORT_FLUIDS)
    .colorOverlayTieredHullModel(GTCEu.id("block/overlay/appeng/me_buffer_hatch"))
    .langValue("ME Super Pattern Buffer")
    .tooltips(
        Component.translatable("block.gtceu.pattern_buffer.desc.0"),
        Component.translatable("block.gtceu.pattern_buffer.desc.1"),
        Component.translatable("block.gtceu.pattern_buffer.desc.2"),
        Component.translatable("gtceu.part_sharing.enabled")
    )
    .register();

    public static void init() {}
}