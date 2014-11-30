package org.tai.twitterchat.security;

import java.util.Arrays;
import java.util.List;

import org.tai.twitterchat.domain.model.User;
import org.tai.twitterchat.domain.model.UserRole;

public enum UsersStorage {
	INSTANCE;

	private List<User> users = Arrays.asList(
			new User("admin", "admin",UserRole.ADMIN), 
			new User("writer", "writer", UserRole.WRITER),
			new User("reader", "reader", UserRole.OBSERVER));

	public boolean checkCredentials(String username, String password) {
		for (User user : users) {
			if (user.getLogin().equals(username)
					&& user.getPassword().equals(password))
				return true;
		}
		return false;
	}

	public User findUser(String username) {
		for (User user : users) {
			if (user.equals(username))
				return user;
		}
		return null;
	}
}
