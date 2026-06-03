package com.dreiksla.gtextra.common.data.machines;

import java.util.function.Consumer;

import com.dreiksla.gtextra.common.data.GTExtraRecipeTypes;
import com.dreiksla.gtextra.GTExtra;

import com.gregtechceu.gtceu.api.GTValues;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class GTExtraRecipesGreenhouse {
    public static void register(Consumer<FinishedRecipe> provider) {
        GTExtraRecipeTypes.GREENHOUSE.recipeBuilder(GTExtra.id("oak_log"))
        .notConsumable(Items.OAK_SAPLING)
        .outputItems(Blocks.OAK_LOG.asItem(), 4)
        .EUt(GTValues.VA[GTValues.LV])
        .duration(150)
        .save(provider);

        GTExtraRecipeTypes.GREENHOUSE.recipeBuilder(GTExtra.id("spruce_log"))
        .notConsumable(Items.SPRUCE_SAPLING)
        .outputItems(Blocks.SPRUCE_LOG.asItem(), 4)
        .EUt(GTValues.VA[GTValues.LV])
        .duration(150)
        .save(provider);

        GTExtraRecipeTypes.GREENHOUSE.recipeBuilder(GTExtra.id("acacia_log"))
        .notConsumable(Items.ACACIA_SAPLING)
        .outputItems(Blocks.ACACIA_LOG.asItem(), 4)
        .EUt(GTValues.VA[GTValues.LV])
        .duration(150)
        .save(provider);

        GTExtraRecipeTypes.GREENHOUSE.recipeBuilder(GTExtra.id("dark_oak_log"))
        .notConsumable(Items.DARK_OAK_SAPLING)
        .outputItems(Blocks.DARK_OAK_LOG.asItem(), 4)
        .EUt(GTValues.VA[GTValues.LV])
        .duration(150)
        .save(provider);
    
        GTExtraRecipeTypes.GREENHOUSE.recipeBuilder(GTExtra.id("birch_log"))
        .notConsumable(Items.BIRCH_SAPLING)
        .outputItems(Blocks.BIRCH_LOG.asItem(), 4)
        .EUt(GTValues.VA[GTValues.LV])
        .duration(150)
        .save(provider);

        GTExtraRecipeTypes.GREENHOUSE.recipeBuilder(GTExtra.id("jungle_log"))
        .notConsumable(Items.JUNGLE_SAPLING)
        .outputItems(Blocks.JUNGLE_LOG.asItem(), 4)
        .EUt(GTValues.VA[GTValues.LV])
        .duration(150)
        .save(provider);

        GTExtraRecipeTypes.GREENHOUSE.recipeBuilder(GTExtra.id("cherry_log"))
        .notConsumable(Items.CHERRY_SAPLING)
        .outputItems(Blocks.CHERRY_LOG.asItem(), 4)
        .EUt(GTValues.VA[GTValues.LV])
        .duration(150)
        .save(provider);
    }
}