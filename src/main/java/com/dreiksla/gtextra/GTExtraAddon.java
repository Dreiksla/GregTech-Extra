package com.dreiksla.gtextra;

import com.gregtechceu.gtceu.api.addon.IGTAddon;

import java.util.function.Consumer;

import com.dreiksla.gtextra.common.data.machines.GTExtraRecipes;
import com.dreiksla.gtextra.common.data.machines.GTExtraRecipesGreenhouse;
import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import net.minecraft.data.recipes.FinishedRecipe;

@GTAddon
public class GTExtraAddon implements IGTAddon {

    @Override
    public GTRegistrate getRegistrate() {
        return GTExtra.REGISTRATE;
    }

    @Override
    public void initializeAddon() {}

    @Override
    public String addonModId() {
        return GTExtra.MOD_ID;
    }

    @Override
    public void registerTagPrefixes() {
        // CustomTagPrefixes.init();
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        GTExtraRecipes.register(provider);
        GTExtraRecipesGreenhouse.register(provider);
    }

    @Override
    public void registerElements() {
        // CustomElements.init();
    }
}