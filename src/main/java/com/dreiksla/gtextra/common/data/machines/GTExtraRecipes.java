package com.dreiksla.gtextra.common.data.machines;

import java.util.function.Consumer;

import com.dreiksla.gtextra.GTExtra;
import com.dreiksla.gtextra.common.data.materials.GTExtraMaterials;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.common.data.machines.GTMultiMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;

import net.minecraft.data.recipes.FinishedRecipe;

public class GTExtraRecipes {
    public static void register(Consumer<FinishedRecipe> provider) {

        GTRecipeTypes.ASSEMBLY_LINE_RECIPES.recipeBuilder("mega_chemical_reactor")
        .inputItems(CustomTags.LuV_CIRCUITS, 16)
        .inputItems(GTItems.SENSOR_LuV, 8)
        .inputItems(GTItems.ADVANCED_SMD_RESISTOR, 32)
        .inputItems(GTItems.ADVANCED_SMD_TRANSISTOR, 64)
        .inputItems(GTItems.ADVANCED_SMD_CAPACITOR, 48)
        .inputItems(GTItems.EMITTER_LuV, 8)
        .inputItems(GTItems.ROBOT_ARM_LuV, 8)
        .inputItems(GCYMBlocks.HEAT_VENT.asItem(), 16)
        .inputItems(GTBlocks.CASING_PTFE_INERT.asItem(), 64)
        .inputItems(GTBlocks.CASING_POLYTETRAFLUOROETHYLENE_PIPE.asItem(), 64)
        .inputFluids(GTMaterials.SolderingAlloy.getFluid(5256))
        .inputFluids(GTMaterials.Acetone.getFluid(5256))
        .inputFluids(GTExtraMaterials.Polytetrafluoroethylenebenzimadole.getFluid(5256))
        .inputFluids(GTMaterials.Polybenzimidazole.getFluid(5256))
        .outputItems(GTExtraMultiMachines.MEGA_CHEMICAL_REACTOR.asStack(), 1)
        .EUt(GTValues.VA[GTValues.ZPM])
        .duration(500)
        .stationResearch(b -> b
            .researchStack(GTMultiMachines.LARGE_CHEMICAL_REACTOR.asStack())
            .CWUt(6)
            .EUt(GTValues.VA[GTValues.ZPM])
        )
        .save(provider);

        GTRecipeTypes.LARGE_CHEMICAL_RECIPES.recipeBuilder(GTExtra.id("polytetrafluoroethylenebenzimadole"))
        .inputFluids(GTMaterials.Benzene.getFluid(250))
        .inputFluids(GTMaterials.Tetrafluoroethylene.getFluid(250))
        .inputFluids(GTMaterials.NitricAcid.getFluid(250))
        .inputFluids(GTMaterials.Polybenzimidazole.getFluid(250))
        .outputFluids(GTExtraMaterials.Polytetrafluoroethylenebenzimadole.getFluid(750))
        .EUt(GTValues.VA[GTValues.LuV])
        .save(provider);

        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(GTExtra.id("greenhouse"))
        .inputItems(ChemicalHelper.get(TagPrefix.gear, GTMaterials.Steel), 4)
        .inputItems(ChemicalHelper.get(TagPrefix.cableGtSingle, GTMaterials.Steel), 4)
        .inputItems(CustomTags.LV_CIRCUITS, 4)
        .inputItems(GTBlocks.MACHINE_CASING_LV, 2)
        .outputItems(GTExtraMultiMachines.GREENHOUSE.asStack(), 1)
        .EUt(GTValues.VA[GTValues.LV])
        .duration(1000)
        .save(provider);
    }
}