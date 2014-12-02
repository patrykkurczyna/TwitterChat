package org.tai.twitterchat.domain.model;

/**
 * This class represents single user
 * @author patrykkurczyna
 *
 */
public class User {
	private final String adminConsumerKey = "XJebsejXnIZxSaG137uv9XPEx";
	private final String adminConsumerSecret = "s3FQc4Fsw05jp1CfN2DD9V1mGqG2utdzpuzIpFtsEovaVkgQHS";
	private final String adminConsumerAccessToken = "2898015615-bxWvZ4hWjdWTmxidM9ATQg5tU3R6Xe9ghsH3o32";
	private final String adminConsumerAccessSecret = "BTlUcLj260xL2nVBBxxI2BsfyITiBn3mc4ChnKJ93X6X6";
	
	private final String writerConsumerKey = "xXzWfyBVBhuUUTFF5Ykbachhr";
	private final String writerConsumerSecret = "SvV0s22vKJ1mF4AixAsGythTWCdr4PjwL8K20AgQSDqeOTEeeg";
	private final String writerConsumerAccessToken = "1197751716-ZWL0nIBKgRKiPK8MNJ9W1DLCBTTcsCl7ImsrqX7";
	private final String writerConsumerAccessSecret = "l2jBJdyrc2OwQIy22EJReJUwLSE1DjLa3JC2yKnN4VsYD";
	
	private final String readerConsumerKey = "XJebsejXnIZxSaG137uv9XPEx";
	private final String readerConsumerSecret = "s3FQc4Fsw05jp1CfN2DD9V1mGqG2utdzpuzIpFtsEovaVkgQHS";
	private final String readerConsumerAccessToken = "2898015615-bxWvZ4hWjdWTmxidM9ATQg5tU3R6Xe9ghsH3o32";
	private final String readerConsumerAccessSecret = "BTlUcLj260xL2nVBBxxI2BsfyITiBn3mc4ChnKJ93X6X6";
	
	private String consumerKey;
	private String consumerSecret;
	private String consumerAccessToken;
	private String consumerAccessSecret;
	
	private String login;
	private String password;
	private UserRole userRole;
	
	public User(String login, String password) {
		switch (login) {
		case "admin":
			this.userRole = UserRole.ADMIN;
			setUser(login, password, adminConsumerKey, adminConsumerSecret, adminConsumerAccessToken, adminConsumerAccessSecret);
			break;
		case "writer":
			this.userRole = UserRole.WRITER;
			setUser(login, password, writerConsumerKey, writerConsumerSecret, writerConsumerAccessToken, writerConsumerAccessSecret);
			break;
		case "reader":
			this.userRole = UserRole.OBSERVER;
			setUser(login, password, readerConsumerKey, readerConsumerSecret, readerConsumerAccessToken, readerConsumerAccessSecret);
			break;
		default:
			break;
		}
	}
	
	private void setUser(String login, String password, String consKey, String consSecret, String consAT, String consAS) {
		this.login = login;
		this.password = password;
		this.consumerKey = consKey;
		this.consumerSecret = consSecret;
		this.consumerAccessToken = consAT;
		this.consumerAccessSecret = consAS;
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
