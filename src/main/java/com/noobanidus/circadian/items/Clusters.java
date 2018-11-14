package com.noobanidus.circadian.items;

import cofh.core.item.ItemMulti;
import com.noobanidus.circadian.Circadian;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Map;

public class Clusters extends ItemMulti {

    public Clusters () {
        super("circadian");

        setUnlocalizedName("cluster");
        setCreativeTab(Circadian.TAB);
    }

    public boolean init() {
        ForgeRegistries.ITEMS.register(this.setRegistryName("circadian", "cluster"));

        clusterAmber = addOreDictItem(0, "clusterAmber");
        clusterAquamarine = addOreDictItem(1, "clusterAquamarine");
        clusterCoal = addOreDictItem(2, "clusterCoal");
        clusterDiamond = addOreDictItem(3, "clusterDiamond");
        clusterEmerald = addOreDictItem(4, "clusterEmerald");
        clusterPlatinum = addOreDictItem(5, "clusterPlatinum");
        clusterRedstone = addOreDictItem(6, "clusterRedstone");
        clusterStarmetal = addOreDictItem(7, "clusterStarmetal");



        return true;
    }

    public void registerModels () {
        for (Map.Entry<Integer, ItemEntry> entry : itemMap.entrySet()) {
            ModelLoader.setCustomModelResourceLocation(this, entry.getKey(), new ModelResourceLocation("circadian:cluster", "type=" + entry.getValue().name));
        }
    }

    public static ItemStack clusterAmber;
    public static ItemStack clusterAquamarine;
    public static ItemStack clusterCoal;
    public static ItemStack clusterDiamond;
    public static ItemStack clusterEmerald;
    public static ItemStack clusterPlatinum;
    public static ItemStack clusterRedstone;
    public static ItemStack clusterStarmetal;
}
