package ccm.ban_death.utils;

import static ccm.nucleum.omnium.NucleumOmnium.server;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import ccm.ban_death.utils.lib.Properties;
import ccm.nucleum.omnium.utils.helper.CommandHelper;

public class PlayerTracker
{
    public static void load()
    {
        MinecraftForge.EVENT_BUS.register(new PlayerTracker());
    }
    
    @ForgeSubscribe
    public void handleDeath(final LivingDeathEvent evt)
    {
        if (evt.entityLiving instanceof EntityPlayer)
        {
            final EntityPlayer player = (EntityPlayer) evt.entityLiving;
            if (server != null)
            {
                if (!Properties.BAN_ADMINS)
                {
                    if (!CommandHelper.isPlayerOp(player.username))
                    {
                        ban(player);
                    }
                } else
                {
                    ban(player);
                }
            }
        }
    }

    private static void ban(final EntityPlayer player)
    {
        final StringBuilder sb = new StringBuilder();
        sb.append("/ban ");
        sb.append(player.username);
        sb.append(" ");
        sb.append(Properties.BAN_REASON);
        sb.append(" ");
        sb.append(Properties.BAN_TIME);
        server.executeCommand(sb.toString());
    }
}