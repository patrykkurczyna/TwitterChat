package org.tai.twitterchat.domain.model;

/**
 * This class represents single user
 * @author patrykkurczyna
 *
 */
public class User {
	//TODO this should not be hardcoded but different for every user
	private final String consumerKey = "XJebsejXnIZxSaG137uv9XPEx";
	private final String consumerSecret = "s3FQc4Fsw05jp1CfN2DD9V1mGqG2utdzpuzIpFtsEovaVkgQHS";
	private final String consumerAccessToken = "2898015615-bxWvZ4hWjdWTmxidM9ATQg5tU3R6Xe9ghsH3o32";
	private final String consumerAccessSecret = "BTlUcLj260xL2nVBBxxI2BsfyITiBn3mc4ChnKJ93X6X6";
	
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
