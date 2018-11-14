package com.noobanidus.circadian.config;

import com.noobanidus.circadian.compat.oreberries.blocks.BlockBerry;
import com.noobanidus.circadian.compat.extrautilities2.blocks.BlockCompressedStoneEntry;
import com.noobanidus.circadian.compat.astralsorcery.blocks.BlockStarmetal;
import com.rwtema.extrautils2.crafting.CraftingHelper;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import com.noobanidus.circadian.compat.thaumcraft.blocks.BlockCompressedVisBattery;
import net.minecraftforge.registries.IForgeRegistry;

import net.minecraft.init.Blocks;
import twilightforest.item.TFItems;

public class Blocks {
    public static CompressedBlockVisBattery compressed;
    public static BlockStarmetal starmetal;

    public static ItemBlock ib_compressed;
    public static ItemBlock ib_starmetal;
    public static ItemBlock ib_knightmetal;
    public static ItemBlock ib_liveroot;
    public static ItemBlock ib_naga;

    public static BlockBerry knightmetal_berry;
    public static BlockBerry liveroot_berry;
    public static BlockBerry naga_berry;

    public static BlockCompressedStoneEntry[] compressedBlocks = new BlockCompressedStoneEntry[] { new BlockCompressedStoneEntry(((BlockStone) Blocks.STONE).getStateFromMeta(0), "stone", 2), new BlockCompressedStoneEntry(((BlockStone) Blocks.STONE).getStateFromMeta(1), "stone_granite", 2), new BlockCompressedStoneEntry(((BlockStone) Blocks.STONE).getStateFromMeta(3), "stone_diorite", 2), new BlockCompressedStoneEntry(((BlockStone) Blocks.STONE).getStateFromMeta(5), "stone_andesite", 2), new BlockCompressedStoneEntry(Blocks.SANDSTONE, "sandstone_normal", 2), new BlockCompressedStoneEntry(Blocks.RED_SANDSTONE, "red_sandstone_normal", 2)};

    public static void preInitBlocks () {
        compressed = new CompressedBlockVisBattery();
        starmetal = new BlockStarmetal();
        knightmetal_berry = new BlockBerry("knight", BlockBerry.getDefaults("knight"));
        liveroot_berry = new BlockBerry("liveroot", BlockBerry.getDefaults("liveroot"));
        naga_berry = new BlockBerry("naga", BlockBerry.getDefaults("naga"));
    }

    public static void initBlocks () {
        ib_compressed = new ItemBlock(compressed);
        ib_compressed.setRegistryName(compressed.getRegistryName());

        ib_starmetal = new ItemBlock(starmetal);
        ib_starmetal.setRegistryName(starmetal.getRegistryName());

        ib_knightmetal = new ItemBlock(knightmetal_berry);
        ib_knightmetal.setRegistryName(knightmetal_berry.getRegistryName());

        ib_liveroot = new ItemBlock(liveroot_berry);
        ib_liveroot.setRegistryName(liveroot_berry.getRegistryName());

        ib_naga = new ItemBlock(naga_berry);
        ib_naga.setRegistryName(naga_berry.getRegistryName());
    }

    public static void registerBlocks (IForgeRegistry<Block> iForgeRegistry) {
        if (((CompressedBlockVisBattery) compressed).enabled) {
            iForgeRegistry.register(compressed);
        }

        iForgeRegistry.register(starmetal);
        iForgeRegistry.register(knightmetal_berry);
        iForgeRegistry.register(liveroot_berry);
        iForgeRegistry.register(naga_berry);
    }

    public static void registerItems (IForgeRegistry<Item> iForgeRegistry) {
        if (((CompressedBlockVisBattery) compressed).enabled) {
            iForgeRegistry.register(ib_compressed);
        }

        iForgeRegistry.register(ib_starmetal);
        iForgeRegistry.register(ib_knightmetal);
        iForgeRegistry.register(ib_liveroot);
        iForgeRegistry.register(ib_naga);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels () {
        if (((CompressedBlockVisBattery) compressed).enabled) {
            ModelLoader.setCustomModelResourceLocation(ib_compressed, 0, new ModelResourceLocation(new ResourceLocation("circadian", "compressed_vis_battery"), "inventory"));
        }

        starmetal.registerModels();

        // knightmetal
        ModelLoader.setCustomStateMapper(knightmetal_berry, StateMapper.KNIGHTMETAL);
        ModelLoader.setCustomModelResourceLocation(ib_knightmetal, 0, new ModelResourceLocation(new ResourceLocation("circadian", "block_knightmetal_bush"), "inventory"));

         // liveroot
        ModelLoader.setCustomStateMapper(naga_berry, StateMapper.NAGA);
        ModelLoader.setCustomModelResourceLocation(ib_naga, 0, new ModelResourceLocation(new ResourceLocation("circadian", "block_nagascale_bush"), "inventory"));

        // naga
        ModelLoader.setCustomStateMapper(liveroot_berry, StateMapper.LIVEROOT);
        ModelLoader.setCustomModelResourceLocation(ib_liveroot, 0, new ModelResourceLocation(new ResourceLocation("circadian", "block_liveroot_bush"), "inventory"));

    }

    @SideOnly(Side.CLIENT)
    protected static class StateMapper extends StateMapperBase {
        public static final StateMapper KNIGHTMETAL = new StateMapper("circadian", "block_knightmetal_bush");
        public static final StateMapper NAGA = new StateMapper("circadian", "block_nagascale_bush");
        public static final StateMapper LIVEROOT = new StateMapper("circadian", "block_liveroot_bush");

        protected ResourceLocation texture;

        protected StateMapper (String path, String domain) {
            this.texture = new ResourceLocation(path, domain);
        }

        @Override
        public ModelResourceLocation getModelResourceLocation(IBlockState state) {
            return new ModelResourceLocation(texture, this.getPropertyString(state.getProperties()));
        }
    }
}
