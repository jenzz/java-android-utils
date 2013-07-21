package com.jensdriller.utils.reflection;

import java.lang.reflect.InvocationTargetException;

/**
 * A set of helper methods for best-effort method calls via reflection.
 */
public class Reflection {

	public static Object tryInvoke(Object target, String methodName,
			Object... args) {
		Class<?>[] argTypes = new Class<?>[args.length];
		for (int i = 0; i < args.length; i++) {
			argTypes[i] = args[i].getClass();
		}

		return tryInvoke(target, methodName, argTypes, args);
	}

	public static Object tryInvoke(Object target, String methodName,
			Class<?>[] argTypes, Object... args) {
		try {
			return target.getClass().getMethod(methodName, argTypes)
					.invoke(target, args);
		} catch (NoSuchMethodException ignored) {
		} catch (IllegalAccessException ignored) {
		} catch (InvocationTargetException ignored) {
		}

		return null;
	}
}
