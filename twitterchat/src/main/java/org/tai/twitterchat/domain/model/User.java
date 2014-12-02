package org.tai.twitterchat.domain.model;

import org.tai.twitterchat.service.TwitterConnectionService;

/**
 * This class represents single user
 * @author patrykkurczyna
 *
 */
public class User {	
	private String login;
	private String password;
	private UserRole userRole;
	private TwitterConnectionService service = null;
	
	public User(String login, String password, UserRole userRole) {
		this.login = login;
		this.password = password;
		this.userRole = userRole;
		setTwitterConnectionService(new TwitterConnectionService(login));
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

	public TwitterConnectionService getService() {
		return service;
	}
	
	public void setTwitterConnectionService(TwitterConnectionService service) {
		this.service = service;
	}
	
}
