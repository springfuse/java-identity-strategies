package com.springfuse.blog.identity;

/**
 * Use the UsernameEqualsStrategy to run the tests present in DefaultUserIdentityTest
 */
public class UsernameUserIdentityTest extends DefaultUserIdentityTest {
	
	@Override
	public User newUser() {
		User user = new User();
		user.setEqualsStrategy(new UsernameEqualsStrategy(user));
		return user;
	}
}
