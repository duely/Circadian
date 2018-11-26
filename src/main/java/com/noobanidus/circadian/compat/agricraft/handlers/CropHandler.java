package com.noobanidus.circadian.compat.agricraft.handlers;

import thaumcraft.common.lib.utils.CropUtils;

public class CropHandler {
    public static void init() {
        // TODO: I don't even think this is needed any more?

        CropUtils.clickableCrops.add("tile.agricraft:crop7");
        /* Plz work IMC
        FMLInterModComms.sendMessage("Thaumcraft", "harvestClickableCrop", new ItemStack(AgriBlocks.getInstance().CROP, 1, Constants.MATURE));
         */
    }
}