package com.noobanidus.circadian.blocks;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.noobanidus.circadian.Circadian;
import josephcsible.oreberries.BlockOreberryBush;
import josephcsible.oreberries.config.OreberryConfig;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Random;

public class BlockBerry extends BlockOreberryBush {
    private Item item;

    public BlockBerry(String name, OreberryConfig config, Item item) {
        super(name, config);

        this.item = item;

		setRegistryName(new ResourceLocation("circadian", "oreberry_"+name));
		setCreativeTab(Circadian.TAB);
    }

    @Override
    public ItemStack getBerriesStack (Random rand) {
        return new ItemStack(item, rand.nextInt(config.maxDrops - config.minDrops + 1) + config.minDrops);
    }

	public static OreberryConfig getDefaults (String name) {
		switch (name) {
            case "naga":
                JsonObject naga = new JsonObject();
                naga.addProperty("bushName", "Naga Stoneberry Bush");
                naga.addProperty("berryName", "Nagaleaf"); // this doesn't do anything
                naga.add("smeltingResult", JsonNull.INSTANCE);
                naga.addProperty("tooltip", "");
                naga.addProperty("special", "nagaleaf");
                naga.addProperty("growsInLight", true);
                naga.addProperty("tradeable", false);
                naga.addProperty("maxHeight", 78);
                naga.addProperty("sizeChance", 10);
                return new OreberryConfig("Naga", naga);
            case "knight":
                JsonObject knight = new JsonObject();
                knight.addProperty("bushName", "Armor Shardberry Bush");
                knight.addProperty("berryName", ""); // this doesn't do anything
                knight.add("smeltingResult", JsonNull.INSTANCE);
                knight.addProperty("tooltip", "");
                knight.addProperty("special", "armorshard");
                knight.addProperty("growsInLight", true);
                knight.addProperty("tradeable", false);
                knight.addProperty("maxHeight", 78);
                knight.addProperty("sizeChance", 10);
                return new OreberryConfig("Knight", knight);
            case "liveroot":
                JsonObject liveroot = new JsonObject();
                liveroot.addProperty("bushName", "Liveroot Cluster");
                liveroot.addProperty("berryName", ""); // this doesn't do anything
                liveroot.add("smeltingResult", JsonNull.INSTANCE);
                liveroot.addProperty("tooltip", "");
                liveroot.addProperty("special", "Liveroots");
                liveroot.addProperty("growsInLight", true);
                liveroot.addProperty("tradeable", false);
                liveroot.addProperty("maxHeight", 78);
                liveroot.addProperty("sizeChance", 10);
                return new OreberryConfig("Liveroot", liveroot);
            default:
                return null;
        }
	}
}
