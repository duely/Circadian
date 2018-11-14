package com.noobanidus.circadian.items;

import cofh.core.item.ItemMulti;
import com.noobanidus.circadian.Circadian;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Map;

public class Plate extends ItemMulti {

    public static boolean enabled = Circadian.CONFIG.get("Items.Plates", "Enable", true, "Enable additional plates.");

    public Plate () {
        super("circadian");

        setUnlocalizedName("plate");
        setCreativeTab(Circadian.TAB);
    }

    public boolean init() {
        ForgeRegistries.ITEMS.register(this.setRegistryName("circadian", "plate"));

        plateManasteel = addOreDictItem(0, "plateManasteel");
        plateElementium = addOreDictItem(1, "plateElementium");
        plateTerrasteel = addOreDictItem(2, "plateTerrasteel");

        plateKnightmetal = addOreDictItem(3, "plateKnightmetal");
        plateIronwood = addOreDictItem(4, "plateIronwood");

        plateStarmetal = addOreDictItem(5, "plateStarmetal");

        return true;
    }

    public void registerModels () {
        for (Map.Entry<Integer, ItemEntry> entry : itemMap.entrySet()) {
            ModelLoader.setCustomModelResourceLocation(this, entry.getKey(), new ModelResourceLocation("circadian:plate", "type=" + entry.getValue().name));
        }
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
