package com.springfuse.blog.identity;

public interface UserFactory {

	/**
	 * Return a new User instance with the equalsStrategy set, or null if the default strategy should be used.
	 */
	User newUser();
}
