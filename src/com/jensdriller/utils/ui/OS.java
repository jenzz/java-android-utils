package com.jensdriller.utils.ui;

import android.os.Build;

/**
 * A set of helper methods for identifying API Level support.
 */
public class OS {

	public static boolean hasFroyo() {
		return hasApiLevel(Build.VERSION_CODES.FROYO);
	}

	public static boolean hasGingerbread() {
		return hasApiLevel(Build.VERSION_CODES.GINGERBREAD);
	}

	public static boolean hasHoneycomb() {
		return hasApiLevel(Build.VERSION_CODES.HONEYCOMB);
	}

	public static boolean hasICS() {
		return hasApiLevel(Build.VERSION_CODES.ICE_CREAM_SANDWICH);
	}

	public static boolean hasJellyBean() {
		return hasApiLevel(Build.VERSION_CODES.JELLY_BEAN);
	}

	public static boolean hasApiLevel(int apiLevel) {
		return Build.VERSION.SDK_INT >= apiLevel;
	}
}
