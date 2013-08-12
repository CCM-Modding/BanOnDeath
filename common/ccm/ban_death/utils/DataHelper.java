package ccm.ban_death.utils;

import java.io.File;

import ccm.ban_death.utils.lib.Archive;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.DimensionManager;

/**
 * Use this class to save per-server data.
 * 
 * @author Dries007
 */
public final class DataHelper {
	private static File	root;

	/**
	 * To be called on server start.
	 */
	public static void init() {
		DataHelper.root = new File(DimensionManager.getCurrentSaveRootDirectory(), "CCM-Modding");
		DataHelper.root.mkdirs();
	}

	/**
	 * Use to get a (new) folder for your mod only.
	 * 
	 * @return File instance of the folder. It exists.
	 */
	public static File getModFolder() {
		final File folder = new File(DataHelper.root, Archive.MOD_ID);
		folder.mkdirs();
		return folder;
	}

	/**
	 * Use this before you save data...
	 * 
	 * @param fileName
	 *            Do not add an extension
	 * @return data stored or new NBTTagCompound if file didn't exist.
	 */
	public static NBTTagCompound readData(final String fileName) {
		try {
			final File folder = DataHelper.getModFolder();

			final File file = new File(folder, fileName.trim() + ".dat");

			if (!file.exists()) {
				return new NBTTagCompound();
			} else {
				return CompressedStreamTools.read(file);
			}

		} catch (final Exception e) {
			e.printStackTrace();
			return new NBTTagCompound();
		}
	}

	/**
	 * Make sure you don't just override the old file. Read it, then manipulate, then save.
	 * 
	 * @param fileName
	 *            Do not add an extension
	 * @param data
	 *            in NBT format.
	 * @return true if success.
	 */
	public static boolean saveData(final String fileName, final NBTTagCompound data) {
		try {
			final File folder = DataHelper.getModFolder();

			final File tempFile = new File(folder, fileName.trim() + "_tmp.dat");
			final File realFile = new File(folder, fileName.trim() + ".dat");

			CompressedStreamTools.write(data, tempFile);

			if (realFile.exists()) {
				realFile.delete();
			}

			tempFile.renameTo(realFile);

			return true;
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Remove a data file.
	 * 
	 * @param fileName
	 *            Do not add an extension
	 * @return true if
	 */
	public static boolean deleteFile(final String fileName) {
		try {
			final File folder = DataHelper.getModFolder();
			final File file = new File(folder, fileName.trim() + ".dat");

			return file.delete();
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}