package com.noobanidus.circadian.items;

import cofh.core.item.ItemMulti;
import com.noobanidus.circadian.Circadian;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class Sticks extends ItemMulti {

    public static boolean enabled = Circadian.CONFIG.get("Items.Sticks", "Enable", true, "Enable compressed and double compressed sticks.").getBoolean(true);
    public static ItemStack stickCompressed;
    public static ItemStack stickDoubleCompressed;

    public Sticks() {
        super("circadian");

        setUnlocalizedName("stick");
        setCreativeTab(Circadian.TAB);
    }

    public boolean init() {
        ForgeRegistries.ITEMS.register(this.setRegistryName("circadian", "stick"));

        stickCompressed = addOreDictItem(0, "stickCompressed");
        stickDoubleCompressed = addOreDictItem(1, "stickDoubleCompressed");

        return true;
    }

    public void registerModels() {
        for (Map.Entry<Integer, ItemEntry> entry : itemMap.entrySet()) {
            ModelLoader.setCustomModelResourceLocation(this, entry.getKey(), new ModelResourceLocation("circadian:stick", "type=" + entry.getValue().name));
        }
    }
}