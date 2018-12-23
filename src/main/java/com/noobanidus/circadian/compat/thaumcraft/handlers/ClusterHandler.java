package com.noobanidus.circadian.compat.thaumcraft.handlers;

import cofh.thermalfoundation.block.BlockOre;
import com.noobanidus.circadian.Circadian;
import com.noobanidus.circadian.items.Clusters;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import thaumcraft.common.lib.utils.Utils;

public class ClusterHandler {

    public static boolean enabled = Circadian.CONFIG.get("Items.Clusters", "Enable", true, "Enable extra clusters.").getBoolean(true);

    public static void init() {
        // we only register platinum and starmetal. it should be done like this:
        /*
        String s1 = Item.getIdFromItem(BlockOre.orePlatinum.getItem()) + "," + BlockOre.orePlatinum.getItemDamage();
        String s2 = Item.getIdFromItem(Clusters.clusterPlatinum.getItem()) + "," + Clusters.clusterPlatinum.getItemDamage();

        FMLInterModComms.sendMessage("Thaumcraft", "nativeCluster", s1 + "," + s2 + "," + 1);

        ItemStack starmetalOre = BlockCustomOre.OreType.STARMETAL.asStack();

        s1 = Item.getIdFromItem(starmetalOre.getItem()) + "," + starmetalOre.getItemDamage();
        s2 = Item.getIdFromItem(Clusters.clusterStarmetal.getItem()) + "," + Clusters.clusterStarmetal.getItemDamage();

        FMLInterModComms.sendMessage("Thaumcraft", "nativeCluster", s1 + "," + s2 + "," + 1);
        */

        if (ClusterHandler.enabled) {
            if (Loader.isModLoaded("astralsorcery")) {
                Item starmetal = Item.REGISTRY.getObject(new ResourceLocation("astralsorcery:blockcustomore"));
                if (starmetal == null) {
                    Circadian.LOG.error("Couldn't find Starmetal Ore to register custom cluster!");
                } else {
                    ItemStack ore = new ItemStack(starmetal, 1, 1);
                    Utils.addSpecialMiningResult(ore, Clusters.clusterStarmetal, 1);
                }
            }

            if (Loader.isModLoaded("thermalfoundation")) {
                Utils.addSpecialMiningResult(BlockOre.orePlatinum, Clusters.clusterPlatinum, 1);
            }
        }
    }
}