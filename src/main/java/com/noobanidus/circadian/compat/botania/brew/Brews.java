package com.noobanidus.circadian.compat.botania.brew;

import WayofTime.bloodmagic.core.RegistrarBloodMagic;
import WayofTime.bloodmagic.item.types.ComponentTypes;
import com.noobanidus.circadian.Circadian;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.common.item.ModItems;

public class Brews {
    public static void registerBrews() {
        Brew flight_brew = new Brew("flight", "circadian.brew.Wings", 0x99ffff, Circadian.CONFIG.get("Items.FlightBrew", "Cost", 50000, "Mana cost of the Wings flight brew."), new PotionEffect(RegistrarBloodMagic.FLIGHT, 6000));

        BotaniaAPI.registerBrew(flight_brew);

        Item angelicFeather = Item.REGISTRY.getObject(new ResourceLocation("xreliquary", "angelic_feather"));

        if (angelicFeather == null) {
            angelicFeather = Items.FEATHER;
            Circadian.LOG.error("[Circadian] Reliquary Angelic Feather not found. Botania recipe for Flight Brew was downgraded.");
        }

        BotaniaAPI.registerBrewRecipe(flight_brew, new ItemStack(ModItems.manaResource, 1, 18), ComponentTypes.REAGENT_AIR.getStack(), new ItemStack(Items.GHAST_TEAR, 1, 0), new ItemStack(angelicFeather, 1, 0));
    }
}
