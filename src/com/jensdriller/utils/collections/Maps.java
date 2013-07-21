package com.jensdriller.utils.collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * Provides static methods for creating mutable {@code Map} instances easily and
 * other static methods for working with Maps.
 */
public class Maps {
	/**
	 * Creates a {@code HashMap} instance.
	 * 
	 * @return a newly-created, initially-empty {@code HashMap}
	 */
	public static <K, V> HashMap<K, V> newHashMap() {
		return new HashMap<K, V>();
	}

	/**
	 * Creates a {@code LinkedHashMap} instance.
	 * 
	 * @return a newly-created, initially-empty {@code HashMap}
	 */
	public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
		return new LinkedHashMap<K, V>();
	}

	/**
	 * Creates a {@code TreeMap} instance.
	 * 
	 * @return a newly-created, initially-empty {@code TreeMap}
	 */
	public static <K, V> TreeMap<K, V> newTreeMap() {
		return new TreeMap<K, V>();
	}
}
