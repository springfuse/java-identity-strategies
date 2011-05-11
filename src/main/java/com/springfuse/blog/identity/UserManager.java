package com.springfuse.blog.identity;

import java.util.HashMap;
import java.util.Map;

/**
 * Simulate a user repository.
 */
public class UserManager {

	private Map<Integer, User> repo = new HashMap<Integer, User>();
	private UserFactory userFactory;

	public UserManager(UserFactory userFactory) {
		this.userFactory = userFactory;
	}

	public User getUser(Integer id) {
		if (repo.containsKey(id)) {
			return repo.get(id);
		}

		User user = userFactory.newUser();
		user.setId(id);
		user.setUsername("username-" + id);
		user.setFavoriteColor("blue");
		repo.put(id, user);
		return user;
	}
}
