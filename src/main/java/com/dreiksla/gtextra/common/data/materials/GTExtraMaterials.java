package com.dreiksla.gtextra.common.data.materials;

import com.dreiksla.gtextra.GTExtra;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.common.data.GTMaterials;

public class GTExtraMaterials {

    public static Material Polytetrafluoroethylenebenzimadole;

    public static void init() {
        Polytetrafluoroethylenebenzimadole = new Material.Builder(GTExtra.id("polytetrafluoroethylenebenzimadole"))
            .ingot().fluid().dust()
            .color(0x2A5C82).secondaryColor(0x7A8B99)
            .iconSet(MaterialIconSet.METALLIC)
            .components(GTMaterials.Benzene, 250, GTMaterials.Polytetrafluoroethylene, 250, GTMaterials.Polybenzimidazole, 250)
            .flags(
                MaterialFlags.GENERATE_PLATE,
                MaterialFlags.GENERATE_FOIL,
                MaterialFlags.GENERATE_DENSE,
                MaterialFlags.GENERATE_ROD,
                MaterialFlags.GENERATE_DENSE
            )
            .buildAndRegister();
    }
}