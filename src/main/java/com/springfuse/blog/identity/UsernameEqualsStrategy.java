package com.springfuse.blog.identity;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * equals/hashCode strategy using a business key, the username property.
 */
public class UsernameEqualsStrategy implements EqualsStrategy, Serializable {
	static private final long serialVersionUID = 1L;
	static final private Log logger = LogFactory.getLog(UsernameEqualsStrategy.class);

	private User user;

	public UsernameEqualsStrategy(User user) {
		this.user = user;
	}

	public boolean doEquals(User other) {
		return user.getUsername() != null && user.getUsername().length() > 0 ? user.getUsername().equals(other.getUsername()) : false;
	}

	public int doHashCode() {
		if (user.getUsername() != null && user.getUsername().length() > 0) {
			return user.getUsername().hashCode();
		} else {
			logger.warn("TODO: developer your code is not safe regarding hashCode", new Exception("stack trace"));
			return System.identityHashCode(user);
		}
	}
}
