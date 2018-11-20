package com.noobanidus.circadian.compat.agricraft.handlers;

public class CropHandler {
    public static void init() {
        // TODO: I don't even think this is needed any more?

        // EXTREME HACKERY
        // CropUtils.clickableCrops.add("tile.agricraft:crop7");
        // addClickableCrop(new ItemStack(AgriBlocks.getInstance().CROP, 1, Constants.MATURE), Constants.MATURE);
        /* Plz work IMC
        FMLInterModComms.sendMessage("Thaumcraft", "harvestClickableCrop", new ItemStack(AgriBlocks.getInstance().CROP, 1, Constants.MATURE));
         */
    }
}