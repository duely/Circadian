package com.noobanidus.circadian.compat.vanilla.handlers;

import com.noobanidus.circadian.Circadian;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

public class StackSizeEtcHandler {
    public static void init() {
        Blocks.END_PORTAL_FRAME.setHardness(50.0F).setResistance(2000.0F).setHarvestLevel("pickaxe", 3);

        int cakeCount = Circadian.CONFIG.get("Vanilla.Items", "CakeStackSize", 64, "Modify default stack size of cakes.").getInt(64);
        if (cakeCount <= 64 && cakeCount > 0) {
            Items.CAKE.setMaxStackSize(cakeCount);
        }
        int enderCount = Circadian.CONFIG.get("Vanilla.Items", "EnderPearlStackSize", 64, "Modify default stack size of ender pearls.").getInt(64);
        if (enderCount <= 64 && enderCount > 0) {
            Items.ENDER_PEARL.setMaxStackSize(enderCount);
        }
        int snowballCount = Circadian.CONFIG.get("Vanilla.Items", "SnowballStackSize", 64, "Modify default stack size of snowballs.").getInt(64);
        if (snowballCount <= 64 && snowballCount > 0) {
            Items.SNOWBALL.setMaxStackSize(snowballCount);
        }
        int signCount = Circadian.CONFIG.get("Vanilla.Items", "SignStackSize", 64, "Modify default stack size of signs.").getInt(64);
        if (signCount <= 64 && signCount > 0) {
            Items.SIGN.setMaxStackSize(signCount);
        }
    }
}
