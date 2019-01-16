package com.noobanidus.circadian.icons;

import com.noobanidus.circadian.config.Registrar;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Locale;

@SuppressWarnings("WeakerAccess")
public class BlockMiniatures2 extends Block {

    public static final IProperty<MiniatureVariant> VARIANT = PropertyEnum.create("variant", MiniatureVariant.class);

    public BlockMiniatures2() {
        super(Material.BARRIER);
        this.setDefaultState(blockState.getBaseState().withProperty(VARIANT, MiniatureVariant.ALFHEIM_PORTAL));
        this.setUnlocalizedName("circadian.miniature2");
    }

    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    @Nonnull
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(VARIANT).ordinal();
    }

    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(VARIANT, MiniatureVariant.values()[meta]);
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
        for (MiniatureVariant variation : MiniatureVariant.values()) {
            list.add(new ItemStack(this, 1, variation.ordinal()));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @SideOnly(Side.CLIENT)
    public void registerModel() {
        for (MiniatureVariant variation : MiniatureVariant.values()) {
            ModelLoader.setCustomModelResourceLocation(Registrar.ib_miniatures2, variation.ordinal(), new ModelResourceLocation("circadian:miniatures", "inventory_" + variation.getName()));
        }
    }

    public enum MiniatureVariant implements IStringSerializable {
        ALFHEIM_PORTAL(0);

        public final int meta;

        MiniatureVariant(int meta) {
            this.meta = meta;
        }

        @Override
        public String getName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
}

