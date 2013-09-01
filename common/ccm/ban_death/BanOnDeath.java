package ccm.ban_death;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import ccm.ban_death.configuration.BanConfig;
import ccm.ban_death.utils.PlayerTracker;
import ccm.ban_death.utils.lib.Archive;
import ccm.nucleum.omnium.CCMMod;
import ccm.nucleum.omnium.IMod;
import ccm.nucleum.omnium.utils.handler.config.ConfigurationHandler;

@Mod(modid = Archive.MOD_ID, name = Archive.MOD_NAME)
public class BanOnDeath extends CCMMod implements IMod
{

    @Instance
    public static BanOnDeath instance;

    @EventHandler
    public void preInit(final FMLPreInitializationEvent event)
    {
        initializeConfig(event);
        ConfigurationHandler.init(instance, BanConfig.class);
    }

    @EventHandler
    public void init(final FMLInitializationEvent event)
    {
        new PlayerTracker();
    }
}