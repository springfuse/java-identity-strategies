package com.springfuse.blog.identity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class DefaultUserIdentityTest implements UserFactory {

	// ---------------------------------
	// UserFactory default implementation
	// ---------------------------------

	/**
	 * Create a raw User that uses the Object's equals/hashCode methods. Subclasses override this method to set the desired EqualsStrategy implementation.
	 */
	public User newUser() {
		return new User();
	}

	// ---------------------------------
	// helper methods for User creation
	// ---------------------------------

	private User newUser(Integer id) {
		User u = newUser();
		u.setId(id);
		return u;
	}

	private User newUser(String username) {
		User u = newUser();
		u.setUsername(username);
		return u;
	}

	private User newUser(Integer id, String username) {
		User u = newUser(id);
		u.setUsername(username);
		return u;
	}

	private User newUser(Integer id, String username, String favoriteColor) {
		User u = newUser(id);
		u.setUsername(username);
		u.setFavoriteColor(favoriteColor);
		return u;
	}

	// ---------------------------------
	// The tests...
	// ---------------------------------

	@Test
	public void reflexive() {
		User x = newUser(1, "x");
		assertTrue(x.equals(x));
	}

	@Test
	public void logicalEquality() {
		User x1 = newUser(1, "x", "yellow");
		User x2 = newUser(1, "x", "yellow");

		assertTrue(x1.equals(x2) && x2.equals(x1));

		// consistent
		x1.setFavoriteColor("red");
		x2.setFavoriteColor("blue");
		assertTrue(x1.equals(x2) && x2.equals(x1));

		x2.setId(2);
		x2.setUsername("x2");
		assertFalse(x1.equals(x2));
	}

	@Test
	public void falseWhenParameterIsNull() {
		User x = newUser(1, "x");
		assertFalse(x.equals(null));
	}

	@Test
	public void entityInSet() {
		Set<User> set = new HashSet<User>();
		set.add(newUser(1, "x"));
		set.add(newUser(1, "x"));
		assertEquals(1, set.size());
	}

	@Test
	public void setIdAfterAddingEntityInSetAndTryToRemoveIt() {
		Set<User> set = new HashSet<User>();
		User x = newUser("x");
		set.add(x);
		x.setId(1);
		set.remove(x);

		assertEquals(0, set.size());
	}

	@Test
	public void managerReturnsSameEntity() {
		UserManager um = new UserManager(this);

		Set<User> set = new HashSet<User>();
		set.add(um.getUser(1));
		set.add(um.getUser(1));

		assertEquals(1, set.size());
	}

	@Test
	public void differentManagersReturnSameEntity() {
		UserManager um1 = new UserManager(this);
		UserManager um2 = new UserManager(this);

		Set<User> set = new HashSet<User>();
		set.add(um1.getUser(1));
		set.add(um2.getUser(1));

		assertEquals(1, set.size());
	}

	@Test
	public void surviveSerializationWhenIdOrUsernameIsPresent() throws Exception {
		User x = newUser(1, "x");
		assertTrue(x.equals(serializeDeserialize(x)));
	}

	private User serializeDeserialize(User user) throws Exception {
		// Serialize to a byte array
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(user);
		out.close();

		// Get the bytes of the serialized object
		byte[] bytes = bos.toByteArray();

		// Deserialize from a byte array
		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes));
		User deserializedUser = (User) in.readObject();
		in.close();
		return deserializedUser;
	}
}
