package de.derkalaender.ghyn;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = GHYN.MOD_ID, useMetadata = true)
public class GHYN {

    public static final String MOD_ID = "ghyn";

    @SidedProxy(clientSide = "de.derkalaender.ghyn.ClientProxy", serverSide = "de.derkalaender.ghyn.CommonProxy")
    public static CommonProxy proxy;

    public static final Logger logger = LogManager.getLogger("GHYN");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger.info("Loading " + "Glass Hurts You Now" + " Version @VERSION@");

        proxy.initRegistry();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.initHandler();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.initGlassList();

        logger.info("Finished loading");
    }
}
