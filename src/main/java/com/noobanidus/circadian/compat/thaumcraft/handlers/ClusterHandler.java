package com.noobanidus.circadian.compat.thaumcraft.handlers;

import cofh.thermalfoundation.block.BlockOre;
import com.noobanidus.circadian.Circadian;
import com.noobanidus.circadian.items.Clusters;
import hellfirepvp.astralsorcery.common.block.BlockCustomOre;
import thaumcraft.common.lib.utils.Utils;

public class ClusterHandler {

    public static boolean enabled = Circadian.CONFIG.get("Items.Clusters", "Enable", true, "Enable extra clusters.");

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
            Utils.addSpecialMiningResult(BlockOre.orePlatinum, Clusters.clusterPlatinum, 1);
            Utils.addSpecialMiningResult(BlockCustomOre.OreType.STARMETAL.asStack(), Clusters.clusterStarmetal, 1);
        }
    }
}