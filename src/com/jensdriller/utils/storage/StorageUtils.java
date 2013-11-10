package com.jensdriller.utils.storage;

import android.os.Environment;
import android.os.StatFs;

/**
 * Some useful methods when dealing with Android's shared external storage.
 */
public class StorageUtils {

	public static String getExternalBasePath() {
		return Environment.getExternalStorageDirectory().getAbsolutePath();
	}

	public static long getExternalTotalStorage() {
		StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());

		long blockCount = statFs.getBlockCount();
		long blockSize = statFs.getBlockSize();

		return blockCount * blockSize;
	}

	public static long getExternalFreeStorage() {
		StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());

		long availableBlocks = statFs.getAvailableBlocks();
		long blockSize = statFs.getBlockSize();

		return availableBlocks * blockSize;
	}

	public static long getExternalUsedStorage() {
		return getExternalTotalStorage() - getExternalFreeStorage();
	}

}
