package com.noobanidus.circadian.items;

import com.noobanidus.circadian.Circadian;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class Fertilizer extends Item {
    public boolean enabled;

    public Fertilizer() {
        enabled = Circadian.CONFIG.get("Items.Fertilizer", "Enable", true, "Enable fertilizer (bone-meal alternative).");

        setCreativeTab(Circadian.TAB);
        setRegistryName(new ResourceLocation("circadian", "fertilizer"));
        setUnlocalizedName("fertilizer");
    }

    @Override
    @Nonnull
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
            return EnumActionResult.FAIL;
        } else if (ItemDye.applyBonemeal(itemstack, worldIn, pos)) {
            if (!worldIn.isRemote) {
                worldIn.playEvent(2005, pos, 0);
            }

            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.PASS;
    }
}
