package com.noobanidus.circadian.compat.thaumcraft.handlers;

import cofh.thermalfoundation.block.BlockOre;
import com.noobanidus.circadian.Circadian;
import com.noobanidus.circadian.items.Clusters;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.common.lib.utils.Utils;

import java.util.Arrays;
import java.util.List;

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

    public static void inject () {
        ResearchEntry purificiation = ResearchCategories.getResearch("METALPURIFICATION");

        // only one stage
        ResearchStage stage = purificiation.getStages()[0];
        List<ResourceLocation> resources = Arrays.asList(stage.getRecipes());

        resources.addAll(Arrays.asList(new ResourceLocation("thaumcraft", "metal_purification_platinum"), new ResourceLocation("thaumcraft", "metal_purification_diamond"), new ResourceLocation("thaumcraft", "metal_purification_emerald"), new ResourceLocation("thaumcraft", "metal_purification_coal"), new ResourceLocation("thaumcraft", "metal_purification_quartz"), new ResourceLocation("thaumcraft", "metal_purification_redstone"), new ResourceLocation("thaumcraft", "metal_purification_amber"), new ResourceLocation("thaumcraft", "metal_purification_redstone"), new ResourceLocation("thaumcraft", "metal_purification_starmetal")));

        stage.setRecipes(resources.toArray(new ResourceLocation[0]));
        Circadian.LOG.info("Injected new cluster recipes into Thaumonomicon.");
    }
}