package com.noobanidus.circadian;

import com.google.common.collect.Lists;
import com.noobanidus.circadian.compat.OreDictionaryEntries;
import com.noobanidus.circadian.compat.agricraft.handlers.CropHandler;
import com.noobanidus.circadian.compat.agricraft.handlers.RightClickHandler;
import com.noobanidus.circadian.compat.bloodmagic.rituals.handler.RitualCostHandler;
import com.noobanidus.circadian.compat.botania.brew.Brews;
import com.noobanidus.circadian.compat.cofh.thermalexpansion.GuiHandler;
import com.noobanidus.circadian.compat.oreberries.handlers.BerryHandler;
import com.noobanidus.circadian.compat.thaumcraft.handlers.ClusterHandler;
import com.noobanidus.circadian.compat.thaumcraft.handlers.LootHandler;
import com.noobanidus.circadian.compat.top.TOPProvider;
import com.noobanidus.circadian.compat.twilightforest.Mobs;
import com.noobanidus.circadian.compat.vanilla.handlers.HorseBreedingHandler;
import com.noobanidus.circadian.compat.vanilla.handlers.HorseMovementHandler;
import com.noobanidus.circadian.compat.vanilla.handlers.MansionBiomeTypesHandler;
import com.noobanidus.circadian.compat.vanilla.handlers.VillagerAgingHandler;
import com.noobanidus.circadian.events.CircadianEvents;
import com.noobanidus.circadian.events.RitualEventHandler;
import com.noobanidus.circadian.proxy.ISidedProxy;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

@Mod.EventBusSubscriber
@Mod(modid = Circadian.MODID, name = Circadian.MODNAME, version = Circadian.VERSION, dependencies = Circadian.DEPENDS)
@SuppressWarnings("WeakerAccess")
public class Circadian {
    public static final String MODID = "circadian";
    public static final String MODNAME = "Circadian";
    public static final String VERSION = "GRADLE:VERSION";
    public static final String DEPENDS = "after:twilightforest;required-after:thaumcraft;required-after:thermalfoundation;required-after:theoneprobe;required-after:agricraft;required-after:oreberries;required-after:bloodmagic;required-after:thermalexpansion;before:jei;";

    @SuppressWarnings("unused")
    public static final String KEY = "ca23084fc26ce53879eea4b7afb0a8d9da9744d7";

    @SidedProxy(modId=MODID, clientSide = "com.noobanidus.circadian.proxy.ClientProxy", serverSide = "com.noobanidus.circadian.proxy.CommonProxy")
    public static ISidedProxy proxy;

    public final static Logger LOG = LogManager.getLogger(MODID);
    public final static Configuration CONFIG = new Configuration(new File("config", "circadian.cfg"), true);
    public static GuiHandler GUI_HANDLER;

    public static CreativeTabCircadian TAB;

    @Mod.Instance(Circadian.MODID)
    public static Circadian instance;

    public static List<Class> EventClasses = Lists.newArrayList(CircadianEvents.class, RitualEventHandler.class, HorseBreedingHandler.class, VillagerAgingHandler.class, HorseMovementHandler.class);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    // TODO: Better config attention

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
        proxy.loadComplete(event);
    }

}
