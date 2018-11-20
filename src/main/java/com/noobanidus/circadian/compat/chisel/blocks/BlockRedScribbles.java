package com.noobanidus.circadian.compat.chisel.blocks;

import com.noobanidus.circadian.Circadian;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SuppressWarnings("WeakerAccess")
public class BlockRedScribbles extends Block {
    public static final PropertyInteger VARIANT = PropertyInteger.create("variant", 0, 15);

    public static boolean enabled = Circadian.CONFIG.get("Items.RedSandstoneScribbles", "Enable", true, "Enable red variant of Chisel sandstone scribbles.");

    public BlockRedScribbles() {
        super(Material.ROCK);

        setUnlocalizedName("redscribbles");
        setRegistryName("circadian", "redscribbles");
        setHardness(0.8F);
        setCreativeTab(Circadian.TAB);
        setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, 0));
    }

    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(VARIANT, meta);
    }

    public int getMetaFromState(IBlockState state) {
        return state.getValue(VARIANT);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
        for (int i = 0; i < 16; i++) {
            ItemStack stack = new ItemStack(this, 1, i);
            list.add(stack);
        }
    }
}
