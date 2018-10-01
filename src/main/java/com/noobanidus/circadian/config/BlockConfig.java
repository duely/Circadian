package com.noobanidus.circadian.config;

import com.noobanidus.circadian.blocks.BlockCompressedStoneEntry;
import com.rwtema.extrautils2.crafting.CraftingHelper;
import net.minecraft.block.BlockStone;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import com.noobanidus.circadian.blocks.CompressedBlockVisBattery;
import net.minecraftforge.registries.IForgeRegistry;

import net.minecraft.init.Blocks;

public class BlockConfig {
    public static CompressedBlockVisBattery compressed;
    public static ItemBlock ib_compressed;

    public static BlockCompressedStoneEntry[] compressedBlocks = new BlockCompressedStoneEntry[] { new BlockCompressedStoneEntry(((BlockStone) Blocks.STONE).getStateFromMeta(0), "stone", 2), new BlockCompressedStoneEntry(((BlockStone) Blocks.STONE).getStateFromMeta(1), "stone_granite", 2), new BlockCompressedStoneEntry(((BlockStone) Blocks.STONE).getStateFromMeta(3), "stone_diorite", 2), new BlockCompressedStoneEntry(((BlockStone) Blocks.STONE).getStateFromMeta(5), "stone_andesite", 2) };

    public static void preInitBlocks () {
        for (BlockCompressedStoneEntry entry : compressedBlocks) {
            entry.preInitLoad();
            entry.preInitRegister();
            entry.registerOres();
            CraftingHelper.recipeCallback.set(entry.recipes);
            entry.addRecipes();
            CraftingHelper.recipeCallback.set(null);
        }
    }

    public static void initBlocks () {
        compressed = new CompressedBlockVisBattery();

        ib_compressed = new ItemBlock(compressed);
        ib_compressed.setRegistryName(compressed.getRegistryName());
    }

    public static void registerBlocks (IForgeRegistry<Block> iForgeRegistry) {
        if (((CompressedBlockVisBattery) compressed).enabled) {
            iForgeRegistry.register(compressed);
        }
    }

    public static void registerItems (IForgeRegistry<Item> iForgeRegistry) {
        if (((CompressedBlockVisBattery) compressed).enabled) {
            iForgeRegistry.register(ib_compressed);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels () {
        if (((CompressedBlockVisBattery) compressed).enabled) {
            ModelLoader.setCustomModelResourceLocation(ib_compressed, 0, new ModelResourceLocation(new ResourceLocation("circadian", "compressed_vis_battery"), "inventory"));
        }
    }
}
