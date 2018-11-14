package com.noobanidus.circadian.compat.agricraft.handlers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInterModComms;

import com.infinityraider.agricraft.init.AgriBlocks;
import com.infinityraider.agricraft.reference.Constants;
import net.minecraft.item.ItemStack;

public class CropHandler {
    public static void init () {
        FMLInterModComms.sendMessage("Thaumcraft", "harvestClickableCrop", new ItemStack(AgriBlocks.getInstance().CROP, 1, Constants.MATURE));
    }
}