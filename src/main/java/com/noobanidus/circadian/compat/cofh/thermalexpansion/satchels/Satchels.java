package com.noobanidus.circadian.compat.cofh.thermalexpansion.satchels;

import cofh.core.util.helpers.RecipeHelper;
import cofh.thermalexpansion.item.ItemSatchel;
import thaumcraft.api.items.ItemsTC;
import vazkii.botania.common.item.ModItems;

public class Satchels {
    public static void registerRecipes () {
        RecipeHelper.addShapedRecipe(ItemSatchel.satchelBasic,
                "IPI",
                "LCL",
                "LLL",
                'I', "ingotManasteel",
                'P', "plateManasteel",
                'L', "itemLeather",
                'C', "chestIron"
        );
        RecipeHelper.addShapedUpgradeRecipe(ItemSatchel.satchelHardened,
                "IPI",
                "SCS",
                "LLL",
                'I', "ingotThaumium",
                'P', "plateThaumium",
                'S', "quicksilver",
                'C', ItemSatchel.satchelBasic,
                'L', ItemsTC.fabric
        );

        RecipeHelper.addShapedUpgradeRecipe(ItemSatchel.satchelReinforced,
                "IPI",
                "SCS",
                "LLL",
                'I', "ingotTerrasteel",
                'P', "plateTerrasteel",
                'S', "manaDiamond",
                'C', ItemSatchel.satchelHardened,
                'L', "clothManaweave"
        );
        RecipeHelper.addShapedUpgradeRecipe(ItemSatchel.satchelSignalum,
                "IPI",
                "SCS",
                "LLL",
                'I', "ingotElvenElementium",
                'P', "plateElementium",
                'S', "gemResonating",
                'C', ItemSatchel.satchelReinforced,
                'L', ModItems.spellCloth
        );

        RecipeHelper.addShapedUpgradeRecipe(ItemSatchel.satchelResonant,
                "IPI",
                "SCS",
                "LGL",
                'I', "ingotVoid",
                'P', "plateVoid",
                'S', "blockPurpur",
                'C', ItemSatchel.satchelSignalum,
                'L', "clothCrimson",
                'G', ItemsTC.primordialPearl
        );
    }
}
