package com.noobanidus.circadian.compat.oreberries.world;

import com.noobanidus.circadian.compat.oreberries.blocks.BlockBerry;
import com.noobanidus.circadian.config.Registrar;
import josephcsible.oreberries.worldgen.OreberriesWorldGen;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import twilightforest.TFConfig;
import twilightforest.biomes.TFDarkForestBiomeDecorator;
import twilightforest.block.BlockTFRoots;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("WeakerAccess")
public class TwilightWorldGen extends OreberriesWorldGen {
    public TwilightBushGen knightmetal_gen;
    public TwilightBushGen liveroot_gen;
    public TwilightBushGen naga_gen;

    public TwilightWorldGen() {
        knightmetal_gen = new TwilightBushGen(Registrar.knightmetal_berry);
        liveroot_gen = new TwilightBushGen(Registrar.liveroot_berry);
        naga_gen = new TwilightBushGen(Registrar.naga_berry);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == TFConfig.dimension.dimensionID) {
            for (int i = 0; i < 3; i++) {
                generateOreBushes(random, chunkX * 16 + 8, chunkZ * 16 + 8, world);
            }
        }
    }

    @Override
    protected void generateOreBushes(Random random, int xChunk, int zChunk, World world) {
        Biome biome = world.getBiome(new BlockPos(xChunk, 64, zChunk));

        if (biome.decorator instanceof TFDarkForestBiomeDecorator) {
            generateOreBush(random, xChunk, zChunk, world, knightmetal_gen);
        } else {
            generateOreBush(random, xChunk, zChunk, world, naga_gen);
            for (int i = 0; i < 3; i++) {
                // try three times with new replacement
                generateOreBush(random, xChunk, zChunk, world, liveroot_gen);
            }
        }
    }

    protected void generateOreBush(Random random, int xChunk, int zChunk, World world, TwilightBushGen bush) {
        if (bush.oreberryConfig.rarity > 0 && random.nextInt(bush.oreberryConfig.rarity) == 0) {
            for (int i = 0; i < bush.oreberryConfig.density; ++i) {
                BlockPos pos = this.findAdequateLocation(world, xChunk, zChunk, bush); // xChunk + random.nextInt(16), y, zChunk + random.nextInt(16), heightLimit, depthLimit);
                if (pos != null && (!world.isAirBlock(pos.down()) || bush == liveroot_gen)) {
                    bush.generate(world, random, pos);
                }
            }
        }
    }

    protected BlockPos findAdequateLocation(World world, int x, int z, TwilightBushGen bush) { //int x, int y, int z, int heightLimit, int depthLimit) {
        int y = bush.oreberryConfig.getPreferredHeight(world);
        int heightLimit = bush.oreberryConfig.getMaxHeight(world);
        int depthLimit = bush.oreberryConfig.minHeight;

        BlockPos pos = new BlockPos(x, Math.min(y, heightLimit), z);
        do {
            if (bush == liveroot_gen && checkBlock(world, pos) || bush != liveroot_gen && world.isAirBlock(pos) && checkDownBlock(world, pos.down()))
                return pos.down();
            pos = pos.down();
        } while (pos.getY() > depthLimit);

        return null;
    }

    protected boolean checkBlock (World world, BlockPos pos) {
        if (world.isAirBlock(pos)) return false;

        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof BlockTFRoots) {
            // check for an airblock nearby
            if (world.isAirBlock(pos.east()) || world.isAirBlock(pos.west()) || world.isAirBlock(pos.north()) || world.isAirBlock(pos.south()) || world.isAirBlock(pos.up()) || world.isAirBlock(pos.down())) {
                return true;
            }

            // if there isn't an air but there's already a liveroot block we're good

            if (world.getBlockState(pos.east()).getBlock() instanceof BlockBerry || world.getBlockState(pos.west()).getBlock() instanceof BlockBerry || world.getBlockState(pos.north()).getBlock() instanceof BlockBerry || world.getBlockState(pos.south()).getBlock() instanceof BlockBerry || world.getBlockState(pos.up()).getBlock() instanceof BlockBerry || world.getBlockState(pos.down()).getBlock() instanceof BlockBerry) {
                return true;
            }
        }

        return false;
    }

    protected boolean checkDownBlock(World world, BlockPos down) {
        if (world.isAirBlock(down)) return false;

        IBlockState state = world.getBlockState(down);
        Block block = state.getBlock();
        if (block instanceof BlockLeaves || block instanceof BlockLiquid)
            return false; // don't place on flowing water or leaves

        return true;
    }
}