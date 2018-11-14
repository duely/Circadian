package com.noobanidus.circadian.items;

import cofh.core.item.ItemMulti;
import cofh.core.util.core.IInitializer;
import com.noobanidus.circadian.Circadian;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class Plate extends ItemMulti implements IInitializer {
    public Plate () {
        super("circadian");

        setUnlocalizedName("plate");
        setCreativeTab(Circadian.TAB);
    }

    @Override
    public boolean preInit() {
        ForgeRegistries.ITEMS.register(this.setRegistryName("circadian", "plate"));

        // deal with the models somehow???

        plateManasteel = addOreDictItem(0, "plateManasteel");
        plateElementium = addOreDictItem(1, "plateElementium");
        plateTerrasteel = addOreDictItem(2, "plateTerrasteel");

        plateKnightmetal = addOreDictItem(3, "plateKnightmetal");
        plateIronwood = addOreDictItem(4, "plateIronwood");

        plateStarmetal = addOreDictItem(5, "plateStarmetal");

        return true;
    }

    @Override
    public boolean initialize() {
        return true;
    }

    // Botania
    public static ItemStack plateManasteel;
    public static ItemStack plateElementium;
    public static ItemStack plateTerrasteel;

    // Twilight Forest
    public static ItemStack plateKnightmetal;
    public static ItemStack plateIronwood;

    // Astral Sorcery
    public static ItemStack plateStarmetal;
}
