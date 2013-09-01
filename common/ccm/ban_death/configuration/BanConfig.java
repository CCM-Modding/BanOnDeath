package ccm.ban_death.configuration;

import ccm.ban_death.utils.lib.Properties;
import ccm.nucleum.omnium.utils.handler.config.IConfig;

public class BanConfig extends IConfig
{
    @Override
    public void init()
    {
        final String cat = "Configurations";

        Properties.BAN_REASON = config.getProp(cat, "BAN_REASON", "You have died");
        Properties.BAN_ADMINS = config.getProp(cat, "BAN_ADMINS", false);
        Properties.BAN_TIME = config.getProp(cat, "BAN_TIME", "30 M");
    }
}