package com.noobanidus.circadian.world;

import com.noobanidus.circadian.config.BlockConfig;
import josephcsible.oreberries.worldgen.OreberriesWorldGen;
import josephcsible.oreberries.worldgen.WorldGenOreberryBush;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import twilightforest.biomes.TFBiomeBase;
import twilightforest.biomes.TFBiomeDarkForest;
import twilightforest.biomes.TFBiomeDarkForestCenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TwilightWorldGen extends OreberriesWorldGen {
    protected List<WorldGenOreberryBush> bushes = new ArrayList<>();

    public TwilightWorldGen () {
        bushes.add(new WorldGenOreberryBush(BlockConfig.knightmetal_berry));
        bushes.add(new WorldGenOreberryBush(BlockConfig.naga_berry));
        bushes.add(new WorldGenOreberryBush(BlockConfig.liveroot_berry));
    }

    @Override
    public void generate (Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        BlockPos pos = new BlockPos(chunkX*16 + 8, chunkZ * 16 + 8, 64);

        for (WorldGenOreberryBush gen : bushes) {
            if (gen.oreberryConfig.name.equals("knight") && (world.getBiome(pos) instanceof TFBiomeDarkForest || world.getBiome(pos) instanceof TFBiomeDarkForestCenter) | world.getBiome(pos) instanceof TFBiomeBase) {
                generateOreBushes (random, chunkX * 16 + 8, chunkZ * 16 + 8, world);
            }
        }
    }
}