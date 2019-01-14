package com.noobanidus.circadian.items;

import cofh.core.item.ItemMulti;
import com.noobanidus.circadian.Circadian;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class Nuggets extends ItemMulti {

    public static boolean enabled = Circadian.CONFIG.get("Items.Nuggets", "Enable", true, "Enable extra nuggets.").getBoolean(true);
    public static ItemStack nuggetDemonmetal;

    public Nuggets() {
        super("circadian");

        setUnlocalizedName("nugget");
        setCreativeTab(Circadian.TAB);
    }

    public boolean init() {
        ForgeRegistries.ITEMS.register(this.setRegistryName("circadian", "nugget"));

        nuggetDemonmetal = addOreDictItem(0, "nuggetDemonmetal");

        return true;
    }

    public void registerModels() {
        for (Map.Entry<Integer, ItemEntry> entry : itemMap.entrySet()) {
            ModelLoader.setCustomModelResourceLocation(this, entry.getKey(), new ModelResourceLocation("circadian:nugget", "type=" + entry.getValue().name));
        }
    }
}
