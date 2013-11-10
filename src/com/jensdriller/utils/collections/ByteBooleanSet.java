package com.jensdriller.utils.collections;

/**
 * This class lets you store up to 8 booleans in one byte. Usually
 * {@code boolean} uses 1 {@code byte}, but with this class, boolean uses 1
 * {@code bit}.
 * 
 * @author jenz
 * 
 */
public class ByteBooleanSet {

	/**
	 * This byte has 8 bits, values can be 1 or 0 (so they can be used as
	 * booleans)
	 */
	private byte mValue;

	public void setBoolean(int slot, boolean bool) {
		if (slot > Byte.SIZE) {
			return;
		}
		mValue += bool ? (1 << slot) : -(1 << slot);
	}

	public void reset() {
		mValue = 0;
	}

	public boolean[] getBooleanArray() {
		byte value = mValue;
		boolean[] array = new boolean[Byte.SIZE];
		for (byte i = 0; i < Byte.SIZE; i++) {
			array[i] = (value & 1) != 0;
			value >>= 1;
		}
		return array;
	}

	public byte getValue() {
		return mValue;
	}
}
