package com.noobanidus.circadian.config;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import com.noobanidus.circadian.items.Fertilizer;
import net.minecraftforge.registries.IForgeRegistry;

import com.noobanidus.circadian.Circadian;

public class Items {
    public static Item fertilizer;

    public static void initItems () {
        fertilizer = new Fertilizer();
    }

    public static void registerItems (IForgeRegistry<Item> iForgeRegistry) {
        if (((Fertilizer) fertilizer).enabled) {
            iForgeRegistry.register(fertilizer);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels () {
        if (((Fertilizer) fertilizer).enabled) {
            ModelLoader.setCustomModelResourceLocation(fertilizer, 0, new ModelResourceLocation(new ResourceLocation("circadian", "fertilizer"), "inventory"));
        }
    }
}
