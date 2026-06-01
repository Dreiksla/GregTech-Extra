package com.dreiksla.gtextra.common.data.machines;

import java.util.function.Consumer;

import com.dreiksla.gtextra.common.data.GTExtraRecipeTypes;
import com.gregtechceu.gtceu.api.GTValues;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class GTExtraRecipesGreenhouse {
    public static void register(Consumer<FinishedRecipe> provider) {
        GTExtraRecipeTypes.GREENHOUSE.recipeBuilder("oak_log")
        .notConsumable(Items.OAK_SAPLING)
        .outputItems(Blocks.OAK_LOG, 4)
        .EUt(GTValues.VA[GTValues.LV])
        .duration(150)
        .save(provider);
    }
}