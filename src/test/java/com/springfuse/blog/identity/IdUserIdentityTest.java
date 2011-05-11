package com.springfuse.blog.identity;

/**
 * Use the IdEqualsStrategy to run the tests present in DefaultUserIdentityTest
 */
public class IdUserIdentityTest extends DefaultUserIdentityTest {
	
	@Override
	public User newUser() {
		User user = new User();
		user.setEqualsStrategy(new IdEqualsStrategy(user));
		return user;
	}
}
