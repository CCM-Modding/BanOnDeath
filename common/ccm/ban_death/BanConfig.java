package ccm.ban_death;

import ccm.ban_death.utils.lib.Properties;
import ccm.nucleum.omnium.configuration.AdvConfiguration;
import ccm.nucleum.omnium.utils.handler.config.IConfig;

public class BanConfig implements IConfig
{

    private AdvConfiguration config;

    @Override
    public AdvConfiguration getConfiguration()
    {
        return config;
    }

    @Override
    public void init()
    {
        final String cat = "Configurations";

        Properties.BAN_REASON = config.getProp(cat, "BAN_REASON", "You have died");
        Properties.BAN_ADMINS = config.getProp(cat, "BAN_ADMINS", false);
        Properties.BAN_TIME = config.getProp(cat, "BAN_TIME", "30 M");
    }

    @Override
    public IConfig setConfiguration(final AdvConfiguration config)
    {
        this.config = config;
        return this;
    }
}