package com.noobanidus.circadian.world;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.noobanidus.circadian.blocks.BlockBerry;
import josephcsible.oreberries.OreberriesMod;
import josephcsible.oreberries.config.OreberryConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TwilightBushGen extends WorldGenerator
{
	public final OreberryConfig oreberryConfig;
	private final IBlockState newState;
	private final Set<Block> replaceBlocks = new HashSet<>();
	public final BlockBerry block;

	public TwilightBushGen(BlockBerry block)
	{
		this.newState = block.getDefaultState().withProperty(BlockBerry.AGE, 3);
		this.block = block;
		this.oreberryConfig = block.config;
		for(String blockName : block.config.replaceBlocks) {
			Block replaceBlock = Block.REGISTRY.getObject(new ResourceLocation(blockName));
			if(replaceBlock == Blocks.AIR) {
				OreberriesMod.logger.warn("Oreberry bush {} has unknown replacement block {}", oreberryConfig.name, blockName);
			} else {
				replaceBlocks.add(replaceBlock);
			}
		}
	}

	@Override
	public boolean generate (World world, Random random, BlockPos pos)
	{
		int type = random.nextInt(100);
		if (type >= 90)
			generateMediumNode(world, random, pos);
		else if (type >= 70)
			generateSmallNode(world, random, pos);
		else if (type >= 50)
			generateTinyNode(world, random, pos);
		else
			return false;

		return true;
	}

	public void generateMediumNode (World world, Random random, BlockPos pos)
	{
		for (int xPos = -1; xPos <= 1; xPos++)
			for (int yPos = -1; yPos <= 1; yPos++)
				for (int zPos = -1; zPos <= 1; zPos++)
					if (random.nextInt(4) == 0)
						generateBerryBlock(world, pos.add(xPos, yPos, zPos));

		generateSmallNode(world, random, pos);
	}

	public void generateSmallNode (World world, Random random, BlockPos pos)
	{
		generateBerryBlock(world, pos);
		if (random.nextBoolean())
			generateBerryBlock(world, pos.east());
		if (random.nextBoolean())
			generateBerryBlock(world, pos.west());
		if (random.nextBoolean())
			generateBerryBlock(world, pos.south());
		if (random.nextBoolean())
			generateBerryBlock(world, pos.north());
		if (random.nextInt(4) != 0)
			generateBerryBlock(world, pos.up());
		// In 1.7.10, a typo led to up being checked twice, and down not being checked at all.
		// We emulate the effective probabilities of that here, but in a more obvious and efficient way.
	}

	public void generateTinyNode (World world, Random random, BlockPos pos)
	{
		generateBerryBlock(world, pos);
		if (random.nextInt(4) == 0)
			generateBerryBlock(world, pos.east());
		if (random.nextInt(4) == 0)
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

	void generateBerryBlock (World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);
		if ((world.isAirBlock(pos) || (state.getBlockHardness(world, pos) >= 0 && !state.isFullBlock()) ||
				state.getBlock().isReplaceableOreGen(state, world, pos, (s) -> replaceBlocks.contains(s.getBlock())
		)) && !(state.getBlock() instanceof BlockLiquid))
			world.setBlockState(pos, newState, 2);
	}
}
