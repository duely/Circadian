package com.noobanidus.circadian.compat.oreberries.world;

import com.noobanidus.circadian.Circadian;
import com.noobanidus.circadian.compat.oreberries.blocks.BlockBerry;
import com.noobanidus.circadian.config.Registrar;
import josephcsible.oreberries.OreberriesMod;
import josephcsible.oreberries.config.OreberryConfig;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import twilightforest.block.BlockTFRoots;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@SuppressWarnings("WeakerAccess")
public class TwilightBushGen extends WorldGenerator {
    public final OreberryConfig oreberryConfig;
    public final BlockBerry block;
    private final IBlockState newState;
    private final Set<Block> replaceBlocks = new HashSet<>();

    public TwilightBushGen(BlockBerry block) {
        this.newState = block.getDefaultState().withProperty(BlockBerry.AGE, 3);
        this.block = block;
        this.oreberryConfig = block.config;
        for (String blockName : block.config.replaceBlocks) {
            Block replaceBlock = Block.REGISTRY.getObject(new ResourceLocation(blockName));
            if (replaceBlock == Blocks.AIR) {
                OreberriesMod.logger.warn("Oreberry bush {} has unknown replacement block {}", oreberryConfig.name, blockName);
            } else {
                replaceBlocks.add(replaceBlock);
            }
        }
    }

    @Override
    public boolean generate(@Nonnull World world, @Nonnull Random random, @Nonnull BlockPos pos) {
        int type = random.nextInt(20);
        if (type >= 8 || block == Registrar.liveroot_berry)
            generateTinyNode(world, random, pos);
        else
            return false;

        return true;
    }

    public void generateTinyNode(World world, Random random, BlockPos pos) {
        generateBerryBlock(world, pos);
        boolean liveroot = block == Registrar.liveroot_berry;

        if (random.nextInt(4) == 0 || liveroot)
            generateBerryBlock(world, pos.east());
        if (random.nextInt(4) == 0 || liveroot)
            generateBerryBlock(world, pos.west());
        if (random.nextInt(4) == 0)
            generateBerryBlock(world, pos.south());
        if (random.nextInt(4) == 0)
            generateBerryBlock(world, pos.north());
        if (random.nextInt(16) < 7)
            generateBerryBlock(world, pos.up());
        // In 1.7.10, a typo led to up being checked twice, and down not being checked at all.
        // We emulate the effective probabilities of that here, but in a more obvious and efficient way.
    }

    void generateBerryBlock(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        if (block == Registrar.liveroot_berry && state.getBlock() instanceof BlockTFRoots || (
                world.isAirBlock(pos) || (state.getBlockHardness(world, pos) >= 0 && !state.isFullBlock()) ||
                        state.getBlock().isReplaceableOreGen(state, world, pos, (s) -> s != null && replaceBlocks.contains(s.getBlock())
                        ))) {
            world.setBlockState(pos, newState, 2);
            if (block == Registrar.liveroot_berry) {
                Circadian.LOG.error(String.format("Placed a Liveroot Bush at %d/%d/%d", pos.getX(), pos.getY(), pos.getZ()));
            }
        }
    }
}
