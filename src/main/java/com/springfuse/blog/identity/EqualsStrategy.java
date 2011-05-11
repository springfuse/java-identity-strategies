package com.springfuse.blog.identity;

/**
 * equals/hashCode strategy interface.
 */
public interface EqualsStrategy {

	/**
	 * invoked from User equals()
	 */
	boolean doEquals(User user);

	/**
	 * invoked from User hashCode()
	 */
	int doHashCode();
}
