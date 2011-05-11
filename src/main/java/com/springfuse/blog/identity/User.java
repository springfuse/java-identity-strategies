package com.springfuse.blog.identity;

import java.io.Serializable;

/**
 * A simple fake entity, with a primary key, a business key and a simple property.
 * equals/hashCode implementation can be plugged using the equalsStrategy setter.
 */
public class User implements Serializable {
	static private final long serialVersionUID = 1L;

	private EqualsStrategy equalStrategy;

	private Integer id; // the primary key
	private String username; // a business key
	private String favoriteColor; // a simple non unique property

	public User() {
	}

	public User(Integer id) {
		this.id = id;
	}

	public User(String username) {
		this.username = username;
	}

	public User(Integer id, String username) {
		this.id = id;
		this.username = username;
	}

	public void setEqualsStrategy(EqualsStrategy equalsStrategy) {
		this.equalStrategy = equalsStrategy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFavoriteColor() {
		return favoriteColor;
	}

	public void setFavoriteColor(String favoriteColor) {
		this.favoriteColor = favoriteColor;
	}

	@Override
	public boolean equals(Object o) {
		if (equalStrategy == null) {
			return super.equals(o);
		}

		if (this == o) {
			return true;
		}

		if (!(o instanceof User)) {
			return false;
		}

		return equalStrategy.doEquals((User) o);
	}

	@Override
	public int hashCode() {
		if (equalStrategy == null) {
			return super.hashCode();
		}

		return equalStrategy.doHashCode();
	}
}
