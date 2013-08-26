package ccm.ban_death.utils;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import cpw.mods.fml.common.FMLCommonHandler;

public class FunctionHelper {

	/**
	 * OP detection
	 */
	public static boolean isPlayerOp(String username) {
		if (FMLCommonHandler.instance().getEffectiveSide().isClient())
			return true;

		MinecraftServer server = FMLCommonHandler.instance().getSidedDelegate().getServer();

		// SP and LAN
		if (server.isSinglePlayer()) {
			if (server instanceof IntegratedServer && server.getServerOwner().equalsIgnoreCase(username))
				return true;
		}

		// SMP
		return server.getConfigurationManager().getOps().contains(username);
	}
}