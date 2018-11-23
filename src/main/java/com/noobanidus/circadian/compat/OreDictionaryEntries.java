package com.noobanidus.circadian.compat;

import WayofTime.bloodmagic.core.RegistrarBloodMagicBlocks;
import hellfirepvp.astralsorcery.common.block.BlockCustomOre;
import hellfirepvp.astralsorcery.common.lib.BlocksAS;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.blocks.BlocksTC;

public class OreDictionaryEntries {
    public static void initEntries () {
        ItemStack rockCrystalOre = new ItemStack(BlocksAS.customOre, 1, BlockCustomOre.OreType.ROCK_CRYSTAL.ordinal());
        ItemStack thaumcraftQuartzOre = new ItemStack(BlocksTC.oreQuartz, 1, 0);
        ItemStack arcaneStone = new ItemStack(BlocksTC.stoneArcane, 1, 0);
        ItemStack arcaneBricks = new ItemStack(BlocksTC.stoneArcaneBrick, 1, 0);
        ItemStack stonePorous = new ItemStack(BlocksTC.stonePorous, 1, 0);
        ItemStack bloodstone = new ItemStack(RegistrarBloodMagicBlocks.DECORATIVE_BRICK, 1, 0);

        OreDictionary.registerOre("oreAstralRockCrystal", rockCrystalOre);
        OreDictionary.registerOre("oreThaumcraftQuartz", thaumcraftQuartzOre);
        OreDictionary.registerOre("arcaneStone", arcaneStone);
        OreDictionary.registerOre("arcaneStoneBrick", arcaneBricks);
        OreDictionary.registerOre("stonePorous", stonePorous);
        OreDictionary.registerOre("bloodstone", bloodstone);

    }
}