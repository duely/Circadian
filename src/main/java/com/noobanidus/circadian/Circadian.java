package com.noobanidus.circadian;

import cofh.core.util.ConfigHandler;
import com.noobanidus.circadian.compat.agricraft.handlers.CropHandler;
import com.noobanidus.circadian.compat.botania.brew.Brews;
import com.noobanidus.circadian.compat.cofh.thermalexpansion.satchels.Satchels;
import com.noobanidus.circadian.compat.oreberries.handlers.BerryHandler;
import com.noobanidus.circadian.compat.thaumcraft.handlers.ClusterHandler;
import com.noobanidus.circadian.config.Registrar;
import com.noobanidus.circadian.compat.top.TOPProvider;
import com.noobanidus.circadian.compat.oreberries.world.TwilightWorldGen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thaumcraft.api.blocks.BlocksTC;
import vazkii.botania.api.state.enums.LivingRockVariant;
import vazkii.botania.common.block.ModBlocks;

@Mod.EventBusSubscriber
@Mod(modid = Circadian.MODID, name = Circadian.MODNAME, version = Circadian.VERSION, dependencies = Circadian.DEPENDS)
public class Circadian {
    public static final String MODID = "circadian";
    public static final String MODNAME = "Circadian";
    public static final String VERSION = "0.0.1";
    public static final String DEPENDS = "required-after:twilightforest;required-after:thaumcraft;required-after:thermalfoundation;required-after:theoneprobe;required-after:astralsorcery;required-after:agricraft;required-after:oreberries;required-after:bloodmagic;before:jei";

    public final static Logger LOG = LogManager.getLogger(MODID);
    public final static ConfigHandler CONFIG = new ConfigHandler(VERSION);

    public static CreativeTabCircadian TAB;

    @Mod.Instance(Circadian.MODID)
    public static Circadian instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        CONFIG.setConfiguration(new Configuration(new File(event.getModConfigurationDirectory(), "circadian.cfg"), true));
        TAB = new CreativeTabCircadian(CreativeTabs.getNextID(), MODID);
        Registrar.preInit();
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        if (Loader.isModLoaded("theoneprobe")) {
            TOPProvider.init();
        }
        GameRegistry.registerWorldGenerator(new TwilightWorldGen(), 0);

        ClusterHandler.init();
        CropHandler.init();
        BerryHandler.init();
        Satchels.registerRecipes();
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        Brews.registerBrews();
    }
    
    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
		CONFIG.cleanUp(false, true);

		LOG.info("Circadian: Load Complete.");
	}

    @Mod.EventHandler
    public void onFMLServerStarting(FMLServerStartingEvent event) {
    }

    public final class CreativeTabCircadian extends CreativeTabs {
        public CreativeTabCircadian (int id, String id2) {
            super(id, id2);
        }

        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(Registrar.fertilizer);
        }
    }
}
