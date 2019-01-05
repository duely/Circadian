package com.noobanidus.circadian.compat;

import WayofTime.bloodmagic.core.RegistrarBloodMagicBlocks;
import com.noobanidus.circadian.Circadian;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.blocks.BlocksTC;

public class OreDictionaryEntries {
    public static void initEntries() {
        if (Loader.isModLoaded("astralsorcery")) {
            Item rock = Item.REGISTRY.getObject(new ResourceLocation("astralsorcery:blockcustomore"));
            if (rock == null) {
                Circadian.LOG.error("Couldn't find Rock Crystal Ore to register in Ore Dictionary!");
            } else {
                ItemStack ore = new ItemStack(rock, 1, 0);
                OreDictionary.registerOre("oreAstralRockCrystal", ore);
            }
        }

        ItemStack thaumcraftQuartzOre = new ItemStack(BlocksTC.oreQuartz, 1, 0);
        ItemStack arcaneStone = new ItemStack(BlocksTC.stoneArcane, 1, 0);
        ItemStack arcaneBricks = new ItemStack(BlocksTC.stoneArcaneBrick, 1, 0);
        ItemStack stonePorous = new ItemStack(BlocksTC.stonePorous, 1, 0);
        ItemStack bloodstone = new ItemStack(RegistrarBloodMagicBlocks.DECORATIVE_BRICK, 1, 0);

        OreDictionary.registerOre("oreThaumcraftQuartz", thaumcraftQuartzOre);
        OreDictionary.registerOre("arcaneStone", arcaneStone);
        OreDictionary.registerOre("arcaneStoneBrick", arcaneBricks);
        OreDictionary.registerOre("stonePorous", stonePorous);
        OreDictionary.registerOre("bloodstone", bloodstone);

    }
}