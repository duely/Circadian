package com.noobanidus.circadian.proxy;

import com.noobanidus.circadian.Circadian;
import com.noobanidus.circadian.CreativeTabCircadian;
import com.noobanidus.circadian.compat.OreDictionaryEntries;
import com.noobanidus.circadian.compat.agricraft.handlers.CropHandler;
import com.noobanidus.circadian.compat.agricraft.handlers.RightClickHandler;
import com.noobanidus.circadian.compat.bloodmagic.rituals.handler.RitualCostHandler;
import com.noobanidus.circadian.compat.botania.brew.Brews;
import com.noobanidus.circadian.compat.cofh.thermalexpansion.GuiHandler;
import com.noobanidus.circadian.compat.extrautilities2.handlers.EnderLilySpawning;
import com.noobanidus.circadian.compat.oreberries.handlers.BerryHandler;
import com.noobanidus.circadian.compat.thaumcraft.handlers.ClusterHandler;
import com.noobanidus.circadian.compat.thaumcraft.handlers.LootHandler;
import com.noobanidus.circadian.compat.top.TOPProvider;
import com.noobanidus.circadian.compat.twilightforest.Mobs;
import com.noobanidus.circadian.compat.vanilla.handlers.MansionBiomeTypesHandler;
import com.noobanidus.circadian.compat.vanilla.handlers.StackSizeEtcHandler;
import com.noobanidus.circadian.config.Registrar;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy implements ISidedProxy {
    public void preInit(FMLPreInitializationEvent event) {
        Circadian.TAB = new CreativeTabCircadian(CreativeTabs.getNextID(), Circadian.MODID);
        Registrar.preInit();
        EnderLilySpawning.preInit();
        Circadian.EventClasses.forEach(MinecraftForge.EVENT_BUS::register);
        Circadian.GUI_HANDLER = new GuiHandler();
        NetworkRegistry.INSTANCE.registerGuiHandler(Circadian.instance, Circadian.GUI_HANDLER);
    }

    public void init(FMLInitializationEvent event) {
        if (Loader.isModLoaded("theoneprobe")) {
            TOPProvider.init();
        }

        CropHandler.init();
        BerryHandler.init();
        Brews.registerBrews();
    }

    public void postInit(FMLPostInitializationEvent event) {
        Mobs.registerSpawns();
        OreDictionaryEntries.initEntries();
        LootHandler.removeLootEntries();
        ClusterHandler.init();
    }

    public void loadComplete(FMLLoadCompleteEvent event) {
        StackSizeEtcHandler.init();

        RightClickHandler.init();
        MansionBiomeTypesHandler.init();
        RitualCostHandler.init();

        Circadian.LOG.info("Circadian: Load Complete.");
        Circadian.CONFIG.save();
    }

    public void serverStarting(FMLServerStartingEvent event) {
    }

    public void serverStarted(FMLServerStartedEvent event) {
    }
}
