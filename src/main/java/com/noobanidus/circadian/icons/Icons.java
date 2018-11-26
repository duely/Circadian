package com.noobanidus.circadian.icons;

import cofh.core.item.ItemMulti;
import com.noobanidus.circadian.Circadian;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Map;

public class Icons extends ItemMulti {

    public static boolean enabled = Circadian.CONFIG.get("Items.Icons", "Enable", true, "Enable additional icon items.").getBoolean(true);

    public Icons() {
        super("circadian");

        setUnlocalizedName("icon");
    }

    public boolean init() {
        ForgeRegistries.ITEMS.register(this.setRegistryName("circadian", "icon"));

        addItem(0, "horse_head");

        return true;
    }

    public void registerModels() {
        for (Map.Entry<Integer, ItemEntry> entry : itemMap.entrySet()) {
            ModelLoader.setCustomModelResourceLocation(this, entry.getKey(), new ModelResourceLocation("circadian:icons", "type=" + entry.getValue().name));
        }
    }
}
