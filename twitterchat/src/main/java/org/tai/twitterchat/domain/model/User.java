package org.tai.twitterchat.domain.model;

/**
 * This class represents single user
 * @author patrykkurczyna
 *
 */
public class User {
	//TODO this should not be hardcoded but different for every user
	private final String consumerKey = "xXzWfyBVBhuUUTFF5Ykbachhr";
	private final String consumerSecret = "SvV0s22vKJ1mF4AixAsGythTWCdr4PjwL8K20AgQSDqeOTEeeg";
	private final String consumerAccessToken = "1197751716-ZWL0nIBKgRKiPK8MNJ9W1DLCBTTcsCl7ImsrqX7";
	private final String consumerAccessSecret = "l2jBJdyrc2OwQIy22EJReJUwLSE1DjLa3JC2yKnN4VsYD";
	
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

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public String getConsumerAccessToken() {
		return consumerAccessToken;
	}

	public String getConsumerAccessSecret() {
		return consumerAccessSecret;
	}
	
}
