package com.noobanidus.circadian.icons;

import cofh.core.item.ItemMulti;
import com.noobanidus.circadian.Circadian;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Map;

public class Icons extends ItemMulti {

    public static boolean enabled = Circadian.CONFIG.get("Items.Icons", "Enable", true, "Enable additional icon items.").getBoolean(true);

    ItemStack horseHead;
    ItemStack wither;
    ItemStack enderLily;
    ItemStack blaze;
    ItemStack vitium;

    public Icons() {
        super("circadian");

        setUnlocalizedName("icon");
        setCreativeTab(Circadian.TAB);
    }

    public boolean init() {
        ForgeRegistries.ITEMS.register(this.setRegistryName("circadian", "icon"));

        wither = addItem(0, "wither");
        horseHead = addItem(1, "horse_head");
        enderLily = addItem(2, "ender_lily");
        blaze = addItem(3, "blaze");
        vitium = addItem(4, "vitium");

        return true;
    }

    public void registerModels() {
        for (Map.Entry<Integer, ItemEntry> entry : itemMap.entrySet()) {
            ModelLoader.setCustomModelResourceLocation(this, entry.getKey(), new ModelResourceLocation("circadian:icons", "type=" + entry.getValue().name));
        }
    }
}
