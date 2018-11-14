package com.noobanidus.circadian.items;

import cofh.core.item.ItemMulti;
import com.noobanidus.circadian.Circadian;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Map;

public class Food extends ItemMulti {

    public static boolean enabled = Circadian.CONFIG.get("Items.Food", "Enable", true, "Enable silvered potatos, carrots, etc.");

    public Food () {
        super("circadian");

        setUnlocalizedName("food");
        setCreativeTab(Circadian.TAB);
    }

    public boolean init() {
        ForgeRegistries.ITEMS.register(this.setRegistryName("circadian", "food"));

        goldenPotato = addOreDictItem(0, "goldenPotato");
        silveredPotato = addOreDictItem(1, "silveredPotato");
        silveredCarrot = addOreDictItem(2, "silveredCarrot");
        silveredApple = addOreDictItem(3, "silveredApple");
        silveredMelon = addOreDictItem(5, "silveredMelon");


        return true;
    }

    public void registerModels () {
        for (Map.Entry<Integer, ItemEntry> entry : itemMap.entrySet()) {
            ModelLoader.setCustomModelResourceLocation(this, entry.getKey(), new ModelResourceLocation("circadian:food", "type=" + entry.getValue().name));
        }
    }

    public static ItemStack goldenPotato;
    public static ItemStack silveredPotato;
    public static ItemStack silveredCarrot;
    public static ItemStack silveredApple;
    public static ItemStack silveredMelon;
}
