package circadian.config;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import circadian.blocks.CompressedBlockVisBattery;
import net.minecraftforge.registries.IForgeRegistry;

import circadian.Circadian;

public class Blocks {
    public static CompressedBlockVisBattery compressed;
    public static ItemBlock ib_compressed;

    public static void initBlocks () {
        compressed = new CompressedBlockVisBattery();

        ib_compressed = new ItemBlock(compressed);
        ib_compressed.setRegistryName(compressed.getRegistryName());
    }

    public static void registerBlocks (IForgeRegistry<Block> iForgeRegistry) {
        iForgeRegistry.register(compressed);
    }

    public static void registerItems (IForgeRegistry<Item> iForgeRegistry) {
        iForgeRegistry.register(ib_compressed);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels () {
        ModelLoader.setCustomModelResourceLocation(ib_compressed, 0, new ModelResourceLocation(new ResourceLocation("circadian", "compressed_vis_battery"), "inventory"));
    }
}
