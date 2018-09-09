package circadian;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = "circadian", name = "Circadian", version = "0.0.1", dependencies = "required-after:thaumcraft;required-after:thermalfoundation;before:jei")
public class Circadian {
    public final static Logger LOG = LogManager.getLogger("circadian");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    }
    
    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
    }

    @Mod.EventHandler
    public void onFMLServerStarting(FMLServerStartingEvent event) {
    }
}
