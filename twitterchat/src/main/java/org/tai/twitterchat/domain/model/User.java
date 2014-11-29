package org.tai.twitterchat.domain.model;

/**
 * This class represents single user
 * @author patrykkurczyna
 *
 */
public class User {
	private final String login;
	private final String password;
	private final UserRole userRole;
	
	public User(String login, String password, UserRole role) {
		this.login = login;
		this.password = password;
		this.userRole = role;
	}

	@Override
	public String toString() {
		return String.format("User [login=%s]", login);
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * Getter for acknowledge user's role
	 * @return {@link UserRole}
	 */
	public UserRole getUserRole() {
		return userRole;
	}
	
}
