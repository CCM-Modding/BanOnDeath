package ccm.ban_death.utils;

import static ccm.ban_death.BanOnDeath.server;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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
		// evt.entityPlayer
		if (evt.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) evt.entityLiving;
			if (server != null) {
				if (!server.isSinglePlayer()) {
					if (!Properties.BAN_ADMINS) {
						// if (NOT ADMIN) {
						ban(player);
						// }
					} else {
						ban(player);
					}
				}
			}
		}
	}

	private static void ban(final EntityPlayer player) {
		if (player instanceof EntityPlayerMP) {
			StringBuilder sb = new StringBuilder();
			// replace with ban for actual baning... TODO Implement it so it kicks and then blacklist you >:D
			sb.append("/kick ");
			sb.append(player.username);
			sb.append(" ");
			sb.append(Properties.BAN_REASON);
			server.executeCommand(sb.toString());
		}
	}
}