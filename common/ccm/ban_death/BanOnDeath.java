package ccm.ban_death;

import net.minecraft.server.MinecraftServer;
import ccm.ban_death.utils.DataHelper;
import ccm.ban_death.utils.PlayerTracker;
import ccm.ban_death.utils.lib.Archive;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Archive.MOD_ID, name = Archive.MOD_NAME, version = Archive.MOD_VERSION)
public class BanOnDeath {

	@Instance
	public static BanOnDeath instance;
	public static MinecraftServer server;
	
	@PreInit
    public void preInit(final FMLPreInitializationEvent event)
    {
        Config.runConfig(event.getSuggestedConfigurationFile());
    }
    
    @Init
    public void init(final FMLInitializationEvent event)
    {
        new PlayerTracker();
    }
    
    @ServerStarting
    public void serverStart(final FMLServerStartingEvent event)
    {
    	 server = event.getServer();
    	 
    	 DataHelper.init();
    }
}