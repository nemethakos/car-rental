package com.msci.carrental.client.util;

public interface TextDecoratorInterface {

	/**
	 * The implementor of this interface returns a {@link String} (decoration) for
	 * the {@link DecorationType}
	 * 
	 * @param decorationType
	 *            the {@link DecorationType}
	 * @return the {@link String} for the {@link DecorationType}
	 */
	String getDecorationFor(DecorationType decorationType);

}
