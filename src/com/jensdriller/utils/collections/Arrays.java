package com.jensdriller.utils.collections;

import android.util.SparseArray;

/**
 * Provides static methods for creating mutable {@code Array} instances easily
 * and other static methods for working with Arrays.
 */
public class Arrays {

	/**
	 * Creates an empty {@code SparseArray} instance.
	 * 
	 * @return a newly-created, initially-empty {@code SparseArray}
	 */
	public static <E> SparseArray<E> newSparseArray() {
		return new SparseArray<E>();
	}
}
