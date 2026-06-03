package com.dreiksla.gtextra.common.data.machines;

import com.dreiksla.gtextra.GTExtra;
import com.dreiksla.gtextra.common.data.GTExtraRecipeTypes;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.common.data.GCYMBlocks;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Blocks;

public class GTExtraMultiMachines {

    public static final MultiblockMachineDefinition MEGA_CHEMICAL_REACTOR = GTExtra.REGISTRATE
    .multiblock("mega_chemical_reactor", (holder) -> new WorkableElectricMultiblockMachine(holder))
    .tooltips(Component.literal("§6Recipes: Large Chemical Reactor and Chemical Reactor"))
    .tooltips(Component.literal("§6Supports Batch Mode"))
    .tooltips(Component.literal("§6Overclock: OC Perfect Subtick"))
    .tooltips(Component.literal("§6Supports PCH"))
    .rotationState(RotationState.ALL)
    .recipeTypes(GTRecipeTypes.LARGE_CHEMICAL_RECIPES, GTRecipeTypes.CHEMICAL_RECIPES)
    .recipeModifiers(
        GTRecipeModifiers.BATCH_MODE,
        GTRecipeModifiers.OC_PERFECT_SUBTICK,
        GTRecipeModifiers.DEFAULT_ENVIRONMENT_REQUIREMENT,
        GTRecipeModifiers.PARALLEL_HATCH
    )
    .appearanceBlock(GTBlocks.CASING_PTFE_INERT)
    .pattern(definition -> FactoryBlockPattern.start()
        .aisle("aaaaaaabbbbbbbbbaaaaaa", "aaaaaaabaaaaaaabaaaaaa", "aaaaaaabaaaaaaabaaaaaa", "aaaaaaabaaaaaaabaaaaaa", "aaaaaaabaaaaaaabaaaaaa", "aaaaaaabaaaaaaabaaaaaa", "aaaaaaabaaaaaaabaaaaaa", "aaaaaaabaaaaaaabaaaaaa", "daaaaaabbbbbbbbbaaaaaa")
        .aisle("aaaaaaabeeeeeeebaaaaaa", "aaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaa", "aaaaaaabaaaaaaabaaaaaa", "aaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaa", "aaaaaaabeeeeeeebaaaaaa")
        .aisle("aafffaabeeeeeeebaafffa", "aafgfaaaabbbbbaaaafgfa", "aafgfaaaahhhhhaaaafgfa", "aafgfaaaaeaaaeaaaafgfa", "aafgfaabaeeeeeabaafgfa", "aafgfaaaaeaaaeaaaafgfa", "aafgfaaaahhhhhaaaafgfa", "aafgfaaaabbbbbaaaafgfa", "aafffaabeeeeeeebaafffa")
        .aisle("afhhhfabeeeeeeebafhhhf", "afaaafaaabbbbbaaafaaaf", "afaaafaaahbbbhaaafaaaf", "afaaafebaahhhaabefaaaf", "afaaafabaehhheabafaaaf", "afaaafebaahhhaabefaaaf", "afaaafaaahbbbhaaafaaaf", "afaaafaaabbbbbaaafaaaf", "afhhhfabeeeeeeebafhhhf")
        .aisle("afhhhfabeeeeeeebafhhhf", "agaaagababbbbbabagaaag", "agaaagabahbbbhabagaaag", "agaaagabaahhhaabagaaag", "agaaagabaehhheabagaaag", "agaaagabaahhhaabagaaag", "agaaagabahbbbhabagaaag", "agaaagababbbbbabagaaag", "afhhhfabeeeeeeebafhhhf")
        .aisle("afhhhfabeeeeeeebafhhhf", "afaaafaaabbbbbaaafaaaf", "afaaafaaahbbbhaaafaaaf", "afaaafebaahhhaabefaaaf", "afaaafabaehhheabafaaaf", "afaaafebaahhhaabefaaaf", "afaaafaaahbbbhaaafaaaf", "afaaafaaabbbbbaaafaaaf", "afhhhfabeeeeeeebafhhhf")
        .aisle("aafffaabeeeeeeebaafffa", "aafgfaaaabbbbbaaaafgfa", "aafgfaaaahhhhhaaaafgfa", "aafgfaaaaeaaaeaaaafgfa", "aafgfaabaeeeeeabaafgfa", "aafgfaaaaeaaaeaaaafgfa", "aafgfaaaahhhhhaaaafgfa", "aafgfaaaabbbbbaaaafgfa", "aafffaabeeeeeeebaafffa")
        .aisle("aaaaaaabeeeeeeebaaaaaa", "aaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaa", "aaaaaaabaaaaaaabaaaaaa", "aaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaa", "aaaaaaabeeeeeeebaaaaaa")
        .aisle("aaaaaaabbbbcbbbbaaaaad", "aaaaaaabaaaaaaabaaaaaa", "aaaaaaabaaaaaaabaaaaaa", "aaaaaaabaaaaaaabaaaaaa", "aaaaaaabaaaaaaabaaaaaa", "aaaaaaabaaaaaaabaaaaaa", "aaaaaaabaaaaaaabaaaaaa", "aaaaaaabaaaaaaabaaaaaa", "aaaaaaabbbbbbbbbaaaaaa")
        .where("a", Predicates.any())
        .where("b", Predicates.blocks(GTBlocks.CASING_PTFE_INERT.get())
            .or(Predicates.autoAbilities(definition.getRecipeTypes()))
            .or(Predicates.autoAbilities(true, false, true)))
        .where("c", Predicates.controller(Predicates.blocks(definition.get())))
        .where("d", Predicates.any())
        .where("e", Predicates.blocks(GTBlocks.CASING_POLYTETRAFLUOROETHYLENE_PIPE.get()))
        .where("f", Predicates.blocks(GCYMBlocks.CASING_STRESS_PROOF.get()))
        .where("g", Predicates.blocks(GCYMBlocks.HEAT_VENT.get()))
        .where("h", Predicates.heatingCoils())
    .build())
    .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_robust_tungstensteel"),
                        GTCEu.id("block/multiblock/large_chemical_reactor"))
    .register();

    public static final MultiblockMachineDefinition GREENHOUSE = GTExtra.REGISTRATE
    .multiblock("greenhouse", (holder) -> new WorkableElectricMultiblockMachine(holder))
    .rotationState(RotationState.NON_Y_AXIS)
    .recipeTypes(GTExtraRecipeTypes.GREENHOUSE)
    .appearanceBlock(GTBlocks.CASING_STEEL_SOLID)
    .pattern(definition -> FactoryBlockPattern.start()
        .aisle("cabbbbbbbaa", "aaaaaaaaaaa", "aaaaaaaaaaa", "aaaaaaaaaaa", "aaaaaaaaaaa", "aaaaaaaaaaa", "aaaaaaaaaaa", "aabbbbbbbaa", "aaaaaaaaaaa", "aaaaaaaaaaa")
        .aisle("abbbbbbbbba", "aabdddddbaa", "aabdddddbaa", "aabdddddbaa", "aabdddddbaa", "aabdddddbaa", "aabdddddbaa", "abbbbbbbbba", "aaaaaaaaaaa", "aaaaaaaaaaa")
        .aisle("bbeeeeeeebb", "abaaaaaaaba", "abaaaaaaaba", "abaaaaaaaba", "abaaaaaaaba", "abaaaaaaaba", "abaaaaaaaba", "bbbdddddbbb", "aaaaaaaaaaa", "aaaaaaaaaaa")
        .aisle("bbeeeeeeebb", "adaaaaaaada", "adaaaaaaada", "adaaaaaaada", "adaafffaada", "adaafffaada", "adaafffaada", "bbddfffddbb", "aaaadddaaaa", "aaaaaaaaaaa")
        .aisle("bbeeeeeeebb", "adaaaaaaada", "adaaaaaaada", "adaaaaaaada", "adafffffada", "adafffffada", "adafffffada", "bbdfffffdbb", "aaadfffdaaa", "aaaadddaaaa")
        .aisle("bbeeebeeebb", "adaaahaaada", "adaaahaaada", "adaaahaaada", "adaffhffada", "adafffffada", "adafffffada", "bbdfffffdbb", "aaadfffdaaa", "aaaadddaaaa")
        .aisle("bbeeeeeeebb", "adaaaaaaada", "adaaaaaaada", "adaaaaaaada", "adafffffada", "adafffffada", "adafffffada", "bbdfffffdbb", "aaadfffdaaa", "aaaadddaaaa")
        .aisle("bbeeeeeeebb", "adaaaaaaada", "adaaaaaaada", "adaaaaaaada", "adaafffaada", "adaafffaada", "adaafffaada", "bbddfffddbb", "aaaadddaaaa", "aaaaaaaaaaa")
        .aisle("bbeeeeeeebb", "abaaaaaaaba", "abaaaaaaaba", "abaaaaaaaba", "abaaaaaaaba", "abaaaaaaaba", "abaaaaaaaba", "bbbdddddbbb", "aaaaaaaaaaa", "aaaaaaaaaaa")
        .aisle("abbbbbbbbba", "aabdddddbaa", "aabdddddbaa", "aabdddddbaa", "aabdddddbaa", "aabdddddbaa", "aabdddddbaa", "abbbbbbbbba", "aaaaaaaaaaa", "aaaaaaaaaaa")
        .aisle("aabbbgbbbaa", "aaaaaaaaaaa", "aaaaaaaaaaa", "aaaaaaaaaaa", "aaaaaaaaaaa", "aaaaaaaaaaa", "aaaaaaaaaaa", "aabbbbbbbaa", "aaaaaaaaaaa", "aaaaaaaaaac")
        .where("a", Predicates.any())
        .where("b", Predicates.blocks(GTBlocks.CASING_STEEL_SOLID.get())
            .or(Predicates.abilities(PartAbility.IMPORT_ITEMS).setPreviewCount(1))
            .or(Predicates.abilities(PartAbility.EXPORT_ITEMS).setPreviewCount(1))
            .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setPreviewCount(1)))
        .where("c", Predicates.any())
        .where("d", Predicates.blocks(GTBlocks.CASING_TEMPERED_GLASS.get()))
        .where("e", Predicates.blocks(Blocks.GRASS_BLOCK))
        .where("f", Predicates.blocks(Blocks.OAK_LEAVES))
        .where("g", Predicates.controller(Predicates.blocks(definition.get())))
        .where("h", Predicates.blocks(Blocks.OAK_LOG))
    .build())
    .register();

    public static void init() {}
}