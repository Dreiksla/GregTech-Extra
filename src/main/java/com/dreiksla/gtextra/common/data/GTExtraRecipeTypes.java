package com.dreiksla.gtextra.common.data;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.register;

public class GTExtraRecipeTypes {
    
    public static void init() {}

    public static final GTRecipeType GREENHOUSE = register("greenhouse", GTRecipeTypes.MULTIBLOCK)
            .setMaxIOSize(2, 2, 0, 0)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, FillDirection.LEFT_TO_RIGHT)
            .setEUIO(IO.IN)
            .setSound(GTSoundEntries.ASSEMBLER);
}