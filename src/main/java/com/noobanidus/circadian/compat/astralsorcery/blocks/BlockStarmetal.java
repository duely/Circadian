package com.noobanidus.circadian.compat.astralsorcery.blocks;

import com.noobanidus.circadian.Circadian;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStarmetal extends Block {

	public static boolean enabled = Circadian.CONFIG.get("Items.BlockStarmetal", "Enable", true, "Enable the starmetal block.");

	public BlockStarmetal() {

		super(Material.IRON);

		setUnlocalizedName("block_starmetal");
		setRegistryName(new ResourceLocation("circadian", "block_starmetal"));
		setCreativeTab(Circadian.TAB);

		setHardness(5.0F);
		setResistance(10.0F);
		setSoundType(SoundType.METAL);

		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, net.minecraft.entity.EntityLiving.SpawnPlacementType type) {

		return false;
	}

	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
		return true;
	}

	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation("circadian:block_starmetal", "inventory"));
	}
}
