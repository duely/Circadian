package com.noobanidus.circadian;

import cofh.core.util.ConfigHandler;
import com.noobanidus.circadian.top.TOPLilyProvider;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;

import java.io.File;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.noobanidus.circadian.config.Enchantments;
import com.noobanidus.circadian.config.BlockConfig;

@Mod(modid = "circadian", name = "Circadian", version = "0.0.1", dependencies = "required-after:thaumcraft;required-after:thermalfoundation;required-after:theoneprobe;before:jei")
public class Circadian {
    public final static Logger LOG = LogManager.getLogger("circadian");

    @Mod.Instance("circadian")
    public static Circadian instance;

    public final static ConfigHandler CONFIG = new ConfigHandler("0.0.1");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        CONFIG.setConfiguration(new Configuration(new File(event.getModConfigurationDirectory(), "circadian.cfg"), true));
        Enchantments.preInit();
        BlockConfig.preInitBlocks();
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        BlockConfig.initBlocks();
        if (Loader.isModLoaded("theoneprobe")) {
            TOPLilyProvider.init();
        }
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    }
    
    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
		CONFIG.cleanUp(false, true);

		LOG.info("Circadian: Load Complete.");
	}

    @Mod.EventHandler
    public void onFMLServerStarting(FMLServerStartingEvent event) {
    }
}
