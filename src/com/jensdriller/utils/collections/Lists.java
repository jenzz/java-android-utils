package com.jensdriller.utils.collections;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Provides static methods for creating mutable {@code List} instances easily
 * and other static methods for working with Lists.
 */
public class Lists {

	/**
	 * Creates an empty {@code ArrayList} instance.
	 * <p/>
	 * <p>
	 * <b>Note:</b> if you only need an <i>immutable</i> empty List, use
	 * {@link java.util.Collections#emptyList} instead.
	 * 
	 * @return a newly-created, initially-empty {@code ArrayList}
	 */
	public static <E> ArrayList<E> newArrayList() {
		return new ArrayList<E>();
	}

	/**
	 * Creates an empty {@code LinkedList} instance.
	 * <p/>
	 * <p>
	 * <b>Note:</b> if you only need an <i>immutable</i> empty List, use
	 * {@link java.util.Collections#emptyList} instead.
	 * 
	 * @return a newly-created, initially-empty {@code ArrayList}
	 */
	public static <E> LinkedList<E> newLinkedList() {
		return new LinkedList<E>();

	}

	/**
	 * Creates a resizable {@code ArrayList} instance containing the given
	 * elements.
	 * <p/>
	 * <p>
	 * <b>Note:</b> due to a bug in javac 1.5.0_06, we cannot support the
	 * following:
	 * <p/>
	 * <p>
	 * {@code List<Base> list = Lists.newArrayList(sub1, sub2);}
	 * <p/>
	 * <p>
	 * where {@code sub1} and {@code sub2} are references to subtypes of
	 * {@code Base}, not of {@code Base} itself. To get around this, you must
	 * use:
	 * <p/>
	 * <p>
	 * {@code List<Base> list = Lists.<Base>newArrayList(sub1, sub2);}
	 * 
	 * @param elements
	 *            the elements that the list should contain, in order
	 * @return a newly-created {@code ArrayList} containing those elements
	 */
	public static <E> ArrayList<E> newArrayList(E... elements) {
		int capacity = (elements.length * 110) / 100 + 5;
		ArrayList<E> list = new ArrayList<E>(capacity);
		Collections.addAll(list, elements);
		return list;
	}

	/**
	 * Returns consecutive {@linkplain List#subList(int, int) sublists} of a
	 * list, each of the same size (the final list may be smaller). For example,
	 * partitioning a list containing {@code [a, b, c, d, e]} with a partition
	 * size of 3 yields {@code [[a, b, c], [d, e]]} -- an outer list containing
	 * two inner lists of three and two elements, all in the original order.
	 * <p/>
	 * The outer list is unmodifiable, but reflects the latest state of the
	 * source list. The inner lists are sublist views of the original list,
	 * produced on demand using {@link List#subList(int, int)}, and are subject
	 * to all the usual caveats about modification as explained in that API.
	 * <p/>
	 * Adapted from <a
	 * href="http://code.google.com/p/google-collections">http://
	 * code.google.com/p/google-collections</a>
	 * 
	 * @param list
	 *            the list to return consecutive sublists of
	 * @param size
	 *            the desired size of each sublist (the last may be smaller)
	 * @return a list of consecutive sublists
	 * @throws IllegalArgumentException
	 *             if {@code partitionSize} is nonpositive
	 * 
	 */
	public static <T> List<List<T>> partition(List<T> list, int size) {
		if (list == null) {
			throw new NullPointerException("'list' must not be null");
		}
		if (!(size > 0)) {
			throw new IllegalArgumentException("'size' must be greater than 0");
		}

		return new Partition<T>(list, size);
	}

	private static class Partition<T> extends AbstractList<List<T>> {

		final List<T> list;
		final int size;

		Partition(List<T> list, int size) {
			this.list = list;
			this.size = size;
		}

		@Override
		public List<T> get(int index) {
			int listSize = size();
			if (listSize < 0) {
				throw new IllegalArgumentException("negative size: " + listSize);
			}
			if (index < 0) {
				throw new IndexOutOfBoundsException("index " + index + " must not be negative");
			}
			if (index >= listSize) {
				throw new IndexOutOfBoundsException("index " + index + " must be less than size " + listSize);
			}
			int start = index * size;
			int end = Math.min(start + size, list.size());
			return list.subList(start, end);
		}

		@Override
		public int size() {
			return (list.size() + size - 1) / size;
		}

		@Override
		public boolean isEmpty() {
			return list.isEmpty();
		}
	}
}
