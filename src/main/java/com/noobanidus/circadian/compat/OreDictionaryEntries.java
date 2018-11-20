package com.noobanidus.circadian.compat;

import hellfirepvp.astralsorcery.common.block.BlockCustomOre;
import hellfirepvp.astralsorcery.common.lib.BlocksAS;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.blocks.BlocksTC;

public class OreDictionaryEntries {
    public static void initEntries () {
        ItemStack rockCrystalOre = new ItemStack(BlocksAS.customOre, 1, BlockCustomOre.OreType.ROCK_CRYSTAL.ordinal());
        ItemStack thaumcraftQuartzOre = new ItemStack(BlocksTC.oreQuartz, 1, 0);

        OreDictionary.registerOre("oreAstralRockCrystal", rockCrystalOre);
        OreDictionary.registerOre("oreThaumcraftQuartz", thaumcraftQuartzOre);
    }
}