package com.springfuse.blog.identity;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * equals/hashCode strategy using the User primary key, the id property.
 */
public class IdEqualsStrategy implements EqualsStrategy, Serializable {
	static private final long serialVersionUID = 1L;
	static final private Log logger = LogFactory.getLog(IdEqualsStrategy.class);

	private User user;

	public IdEqualsStrategy(User user) {
		this.user = user;
	}

	public boolean doEquals(User other) {
		return user.getId() != null ? user.getId().equals(other.getId()) : false;
	}

	public int doHashCode() {
		if (user.getId() != null) {
			return user.getId().hashCode();
		} else {
			logger.warn("TODO: developer your code is not safe regarding hashCode", new Exception("stack trace"));
			return System.identityHashCode(user);
		}
	}
}
