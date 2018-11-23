package com.noobanidus.circadian;

import cofh.core.util.ConfigHandler;
import com.noobanidus.circadian.compat.OreDictionaryEntries;
import com.noobanidus.circadian.compat.agricraft.handlers.CropHandler;
import com.noobanidus.circadian.compat.agricraft.handlers.RightClickHandler;
import com.noobanidus.circadian.compat.botania.brew.Brews;
import com.noobanidus.circadian.compat.cofh.thermalexpansion.GuiHandler;
import com.noobanidus.circadian.compat.oreberries.handlers.BerryHandler;
import com.noobanidus.circadian.compat.oreberries.world.TwilightWorldGen;
import com.noobanidus.circadian.compat.thaumcraft.handlers.ClusterHandler;
import com.noobanidus.circadian.compat.top.TOPProvider;
import com.noobanidus.circadian.compat.twilightforest.Mobs;
import com.noobanidus.circadian.config.Registrar;
import com.noobanidus.circadian.events.CircadianEvents;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod.EventBusSubscriber
@Mod(modid = Circadian.MODID, name = Circadian.MODNAME, version = Circadian.VERSION, dependencies = Circadian.DEPENDS)
@SuppressWarnings("WeakerAccess")
public class Circadian {
    public static final String MODID = "circadian";
    public static final String MODNAME = "Circadian";
    public static final String VERSION = "1.0.0";
    public static final String DEPENDS = "required-after:twilightforest;required-after:thaumcraft;required-after:thermalfoundation;required-after:theoneprobe;required-after:astralsorcery;required-after:agricraft;required-after:oreberries;required-after:bloodmagic;required-after:thermalexpansion;before:jei;";

    public final static Logger LOG = LogManager.getLogger(MODID);
    public final static ConfigHandler CONFIG = new ConfigHandler(VERSION);
    public final static GuiHandler GUI_HANDLER = new GuiHandler();

    public static CreativeTabCircadian TAB;

    @Mod.Instance(Circadian.MODID)
    public static Circadian instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        CONFIG.setConfiguration(new Configuration(new File(event.getModConfigurationDirectory(), "circadian.cfg"), true));
        TAB = new CreativeTabCircadian(CreativeTabs.getNextID(), MODID);
        Registrar.preInit();
        MinecraftForge.EVENT_BUS.register(CircadianEvents.class);
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, GUI_HANDLER);
    }

    // TODO: Better config attention

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        if (Loader.isModLoaded("theoneprobe")) {
            TOPProvider.init();
        }
        GameRegistry.registerWorldGenerator(new TwilightWorldGen(), 0);

        ClusterHandler.init();
        CropHandler.init();
        BerryHandler.init();
        Brews.registerBrews();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        Mobs.registerSpawns();
        OreDictionaryEntries.initEntries();
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
        RightClickHandler.init();

        CONFIG.cleanUp(false, true);

        LOG.info("Circadian: Load Complete.");
    }

    @Mod.EventHandler
    public void onFMLServerStarting(FMLServerStartingEvent event) {
    }

    public final class CreativeTabCircadian extends CreativeTabs {
        public CreativeTabCircadian(int id, String id2) {
            super(id, id2);
        }

        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(Registrar.fertilizer);
        }
    }
}
