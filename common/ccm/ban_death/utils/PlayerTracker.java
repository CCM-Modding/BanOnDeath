package ccm.ban_death.utils;

import static ccm.ban_death.BanOnDeath.server;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import ccm.ban_death.utils.lib.Properties;

public class PlayerTracker {

	public PlayerTracker() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@ForgeSubscribe
	public void handleDeath(final LivingDeathEvent evt) {
		if (evt.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) evt.entityLiving;
			if (server != null) {
				if (!Properties.BAN_ADMINS) {
					if (!FunctionHelper.isPlayerOp(player.username)) {
						ban(player);
					}
				} else {
					ban(player);
				}
			}
		}
	}

	private static void ban(final EntityPlayer player) {
		StringBuilder sb = new StringBuilder();
		// TODO Implement it so it kicks and then blacklist you >:D
		sb.append("/ban ");
		sb.append(player.username);
		sb.append(" ");
		sb.append(Properties.BAN_REASON);
		server.executeCommand(sb.toString());
	}
}