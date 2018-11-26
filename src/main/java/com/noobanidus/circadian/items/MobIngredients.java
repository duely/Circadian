package com.noobanidus.circadian.items;

import cofh.core.item.ItemMulti;
import com.noobanidus.circadian.Circadian;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class MobIngredients extends ItemMulti {

    public static boolean enabled = Circadian.CONFIG.get("Items.MobDrops", "Enable", true, "Enable additional mob drops.").getBoolean(true);
    public static ItemStack PenguinFeather;

    public MobIngredients() {
        super("circadian");

        setUnlocalizedName("mob_ingredient");
        setCreativeTab(Circadian.TAB);
    }

    public boolean init() {
        ForgeRegistries.ITEMS.register(this.setRegistryName("circadian", "mob_ingredient"));

        PenguinFeather = addItem(0, "PenguinFeather");

        return true;
    }

    public void registerModels() {
        for (Map.Entry<Integer, ItemEntry> entry : itemMap.entrySet()) {
            ModelLoader.setCustomModelResourceLocation(this, entry.getKey(), new ModelResourceLocation("circadian:mob_ingredients", "type=" + entry.getValue().name));
        }
    }
}
