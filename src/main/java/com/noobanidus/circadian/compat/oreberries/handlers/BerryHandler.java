package com.noobanidus.circadian.compat.oreberries.handlers;

import com.noobanidus.circadian.Circadian;
import com.noobanidus.circadian.config.Registrar;
import josephcsible.oreberries.BlockOreberryBush;
import josephcsible.oreberries.proxy.CommonProxy;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import thaumcraft.common.lib.utils.CropUtils;

public class BerryHandler {

    public static boolean enabled = Circadian.CONFIG.get("Items.TwilightBushes", "Enable", true, "Enable Twilight Forest bushes.");

    public static void init () {
        if (enabled) {
            CropUtils.addClickableCrop(new ItemStack(CommonProxy.oreberryBushBlocks.get(0)), 3);
            /* WTB functional IMC
            FMLInterModComms.sendMessage("Thaumcraft", "harvestClickableCrop", new ItemStack(bush, 1, 0));
            */

            /* IMC not functioning currently
            FMLInterModComms.sendMessage("Thaumcraft", "harvestClickableCrop", new ItemStack(Registrar.knightmetal_berry, 1, 0));
            FMLInterModComms.sendMessage("Thaumcraft", "harvestClickableCrop", new ItemStack(Registrar.naga_berry, 1, 0));
            FMLInterModComms.sendMessage("Thaumcraft", "harvestClickableCrop", new ItemStack(Registrar.liveroot_berry, 1, 0));
            */
            CropUtils.addClickableCrop(new ItemStack(Registrar.knightmetal_berry), 3);
            CropUtils.addClickableCrop(new ItemStack(Registrar.naga_berry), 3);
            CropUtils.addClickableCrop(new ItemStack(Registrar.liveroot_berry), 3);
        }
    }
}
