package ru.erked.stalmine;

import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;
import ru.erked.stalmine.client.tabs.*;
import ru.erked.stalmine.common.commands.CommandInfiniteFireplaces;
import ru.erked.stalmine.common.commands.CommandSetTeleportCoordinates;
import ru.erked.stalmine.common.commands.CommandWeaponInfo;
import ru.erked.stalmine.proxy.CommonProxy;

@Mod(
        modid = StalmineMod.MODID,
        name = StalmineMod.NAME,
        version = StalmineMod.VERSION,
        useMetadata = true
)
public class StalmineMod
{
    public static final String MODID = "stalmine";
    public static final String NAME = "S.T.A.L.M.I.N.E. v0.6";
    public static final String VERSION = "8.5.3";

    public static Logger logger;

    // Creative tabs
    public static StalmineTabBlocks tabBlocks;
    public static StalmineTabItems tabItems;
    public static StalmineTabWeapons tabWeapons;
    public static StalmineTabArmor tabArmor;
    public static StalmineTabArtifacts tabArtifacts;

    public static final DamageSource deepfryDS = new DamageSource("anomaly").setFireDamage().setDifficultyScaled();
    public static final DamageSource anomalyDS = new DamageSource("anomaly").setDifficultyScaled();
    public static final DamageSource noArmorDS = new DamageSource("anomaly").setDamageBypassesArmor().setDifficultyScaled();

    @SidedProxy(clientSide = "ru.erked.stalmine.proxy.ClientProxy", serverSide = "ru.erked.stalmine.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static StalmineMod instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandWeaponInfo());
        event.registerServerCommand(new CommandInfiniteFireplaces());
        event.registerServerCommand(new CommandSetTeleportCoordinates());
    }
}
