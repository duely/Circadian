package com.noobanidus.circadian.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import com.noobanidus.circadian.Circadian;

public class Fertilizer extends Item {
    public boolean enabled;

	public Fertilizer() {
        enabled = Circadian.CONFIG.get("Items.Fertilizer", "Enable", true, "Enable fertilizer (bone-meal alternative).");

		setCreativeTab(CreativeTabs.MATERIALS);
		setRegistryName(new ResourceLocation("circadian", "fertilizer"));
		setUnlocalizedName("Fertilizer");
	}

	public Fertilizer(CreativeTabs creativeTab) {
		setCreativeTab(creativeTab);
	}

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            return EnumActionResult.FAIL;
        }
        else if (ItemDye.applyBonemeal(itemstack, worldIn, pos))
        {
            if (!worldIn.isRemote)
            {
                worldIn.playEvent(2005, pos, 0);
            }

            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.PASS;
    }
}