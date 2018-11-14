package com.noobanidus.circadian.compat.oreberries.world;

import com.noobanidus.circadian.config.BlockConfig;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TwilightWorldGen extends OreberriesWorldGen {
    protected List<TwilightBushGen> bushes = new ArrayList<>();

    public TwilightBushGen knightmetal_gen;

    public TwilightWorldGen () {

        knightmetal_gen = new TwilightBushGen(BlockConfig.knightmetal_berry);


        bushes.add(knightmetal_gen);
        bushes.add(new TwilightBushGen(BlockConfig.naga_berry));
        bushes.add(new TwilightBushGen(BlockConfig.liveroot_berry));
    }

    @Override
    public void generate (Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == TFConfig.dimension.dimensionID) {
            for (TwilightBushGen gen : bushes) {
                generateOreBushes(random, chunkX * 16 + 8, chunkZ * 16 + 8, world);
            }
        }
    }

    @Override
    protected void generateOreBushes (Random random, int xChunk, int zChunk, World world)
    {
        Biome biome = world.getBiome(new BlockPos(xChunk, 64, zChunk));

        for(TwilightBushGen gen : bushes) {
		    if (biome.decorator instanceof TFDarkForestBiomeDecorator) {
                if (gen == knightmetal_gen) {
                    generateOreBush(random, xChunk, zChunk, world, gen, gen.oreberryConfig.getPreferredHeight(world), gen.oreberryConfig.getMaxHeight(world), gen.oreberryConfig.minHeight);
                }
            } else if (gen != knightmetal_gen){
                generateOreBush(random, xChunk, zChunk, world, gen, gen.oreberryConfig.getPreferredHeight(world), gen.oreberryConfig.getMaxHeight(world), gen.oreberryConfig.minHeight);
            }
        }
    }

    protected void generateOreBush(Random random, int xChunk, int zChunk, World world, TwilightBushGen bush, int y, int heightLimit, int depthLimit) {
        if (bush.oreberryConfig.rarity > 0 && random.nextInt(bush.oreberryConfig.rarity) == 0) {
            for (int i = 0; i < bush.oreberryConfig.density; ++i) {
                BlockPos pos = this.findAdequateLocation(world, xChunk + random.nextInt(16), y, zChunk + random.nextInt(16), heightLimit, depthLimit);
                if (pos != null && !world.isAirBlock(pos.down())) {
                    bush.generate(world, random, pos);
                }
            }
        }
    }

    protected BlockPos findAdequateLocation (World world, int x, int y, int z, int heightLimit, int depthLimit)
    {
        BlockPos pos = new BlockPos(x, Math.min(y, heightLimit), z);
        do
        {
            if(world.isAirBlock(pos) && checkDownBlock(world, pos.down()))
                return pos.down();
            pos = pos.down();
        } while (pos.getY() > depthLimit);

        return null;
    }

    protected boolean checkDownBlock (World world, BlockPos down) {
        if (world.isAirBlock(down)) return false;

        IBlockState state = world.getBlockState(down);
        Block block = state.getBlock();
        if (block instanceof BlockLeaves || block instanceof BlockLiquid) return false; // don't place on flowing water or leaves

        return true;
    }
}