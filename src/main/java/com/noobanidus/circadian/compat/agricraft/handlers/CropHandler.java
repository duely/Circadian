package com.noobanidus.circadian.compat.agricraft.handlers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInterModComms;

import com.infinityraider.agricraft.init.AgriBlocks;
import com.infinityraider.agricraft.reference.Constants;
import thaumcraft.common.lib.utils.CropUtils;

public class CropHandler {
    public static void init () {
        // EXTREME HACKERY
        // CropUtils.clickableCrops.add("tile.agricraft:crop7");
        // addClickableCrop(new ItemStack(AgriBlocks.getInstance().CROP, 1, Constants.MATURE), Constants.MATURE);
        /* Plz work IMC
        FMLInterModComms.sendMessage("Thaumcraft", "harvestClickableCrop", new ItemStack(AgriBlocks.getInstance().CROP, 1, Constants.MATURE));
         */
    }
}