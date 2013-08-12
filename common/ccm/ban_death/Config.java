package ccm.ban_death;

import java.io.File;

import ccm.ban_death.utils.lib.Properties;
import net.minecraftforge.common.Configuration;

public class Config {
	
	private static Configuration config;

	public static void runConfig(File file) {
		config = new Configuration(file);
		
		String cat = "Configurations";
		
		Properties.BAN_REASON = config.get(cat, "BAN_REASON", "You have died").getString();
		Properties.BAN_ADMINS = config.get(cat, "BAN_ADMINS", false).getBoolean(false);
	}
}