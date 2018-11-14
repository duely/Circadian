package com.noobanidus.circadian.compat.botania.brew;

import WayofTime.bloodmagic.core.RegistrarBloodMagic;
import net.minecraft.potion.PotionEffect;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.brew.Brew;

public class Brews {
    public static void registerBrews () {
        Brew flight_brew = new Brew("flight", "Wings", 0x99ffff, 150000, new PotionEffect(RegistrarBloodMagic.FLIGHT, 6000));

        BotaniaAPI.registerBrew(flight_brew);
    }
}
