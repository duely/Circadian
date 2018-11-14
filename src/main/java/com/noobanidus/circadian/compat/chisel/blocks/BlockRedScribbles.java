package com.noobanidus.circadian.compat.chisel.blocks;

import com.noobanidus.circadian.Circadian;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRedScribbles extends Block
{
    public static final PropertyInteger VARIANT = PropertyInteger.create("variant", 0, 15);

    public static boolean enabled = Circadian.CONFIG.get("Items.RedSandstoneScribbles", "Enable", true, "Enable red variant of Chisel sandstone scribbles.");

    public BlockRedScribbles () {
        super(Material.ROCK);

        setUnlocalizedName("redscribbles");
        setRegistryName("circadian", "redscribbles");
        setHardness(0.8F);
		setCreativeTab(Circadian.TAB);
        setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, Integer.valueOf(0)));
    }

    public int damageDropped(IBlockState state)
    {
        return getMetaFromState(state);
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] { VARIANT });
    }

    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(VARIANT, Integer.valueOf(meta));
    }

    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(VARIANT)).intValue();
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list)
    {
        for (int i = 0; i < 16; i++) {
            ItemStack stack = new ItemStack(this, 1, i);
            list.add(stack);
        }
    }
}
