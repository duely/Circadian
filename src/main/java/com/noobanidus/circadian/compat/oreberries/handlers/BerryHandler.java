package com.noobanidus.circadian.compat.oreberries.handlers;

import com.noobanidus.circadian.Circadian;
import josephcsible.oreberries.proxy.CommonProxy;
import net.minecraft.item.ItemStack;
import thaumcraft.common.lib.utils.CropUtils;

public class BerryHandler {

    public static boolean enabled = Circadian.CONFIG.get("Items.TwilightBushes", "Enable", true, "Enable Twilight Forest bushes.").getBoolean(true);

    public static void init() {
        if (enabled) {
            CropUtils.addClickableCrop(new ItemStack(CommonProxy.oreberryBushBlocks.get(0)), 3);
            /* WTB functional IMC
            FMLInterModComms.sendMessage("Thaumcraft", "harvestClickableCrop", new ItemStack(bush, 1, 0));
            */

        }
    }
}
