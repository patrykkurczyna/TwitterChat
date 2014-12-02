package org.tai.twitterchat.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.DirectMessage;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

/**
 * This service is used to connect with twitter API and perform operations
 * 
 * @author patrykkurczyna
 *
 */
public class TwitterConnectionService {
	private static final int MSG_NUMBER = 5;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TwitterConnectionService.class);
	
	private final String adminConsumerKey = "XJebsejXnIZxSaG137uv9XPEx";
	private final String adminConsumerSecret = "s3FQc4Fsw05jp1CfN2DD9V1mGqG2utdzpuzIpFtsEovaVkgQHS";
	private final String adminConsumerAccessToken = "2898015615-bxWvZ4hWjdWTmxidM9ATQg5tU3R6Xe9ghsH3o32";
	private final String adminConsumerAccessSecret = "BTlUcLj260xL2nVBBxxI2BsfyITiBn3mc4ChnKJ93X6X6";
	
	private final String writerConsumerKey = "xXzWfyBVBhuUUTFF5Ykbachhr";
	private final String writerConsumerSecret = "SvV0s22vKJ1mF4AixAsGythTWCdr4PjwL8K20AgQSDqeOTEeeg";
	private final String writerConsumerAccessToken = "1197751716-ZWL0nIBKgRKiPK8MNJ9W1DLCBTTcsCl7ImsrqX7";
	private final String writerConsumerAccessSecret = "l2jBJdyrc2OwQIy22EJReJUwLSE1DjLa3JC2yKnN4VsYD";
	
	private final String readerConsumerKey = "QHXZQIOqIeRxeoWAOJFQiR9o6";
	private final String readerConsumerSecret = "68pyO4IqwYJT3G047ckTlUOFnbGNa0aIz0G2k45HMaTfl9MteC";
	private final String readerConsumerAccessToken = "2902308274-CvVeRCchQTwymxHI4k15jn4vGeAE6oBClSM0IGu";
	private final String readerConsumerAccessSecret = "BwfLGINXukDnDA7nFjwMr7oWB4LD2JfOmSqqoBiLQm5gW";
	
	private Twitter twitterSender;
	private final Twitter twitterReceiver = new TwitterTemplate(adminConsumerKey, adminConsumerSecret, adminConsumerAccessToken, adminConsumerAccessSecret);;
	
	private void createConnection(String consumerKey, String consumerSecret,
			String consumerAccessToken, String consumerAccessSecret) {
		this.twitterSender = new TwitterTemplate(consumerKey, consumerSecret, consumerAccessToken, consumerAccessSecret);
	}
	
	public TwitterConnectionService(String user) {
		switch (user) {
		case "admin":
			createConnection(adminConsumerKey, adminConsumerSecret, adminConsumerAccessToken, adminConsumerAccessSecret);
			break;
		case "writer":
			createConnection(writerConsumerKey, writerConsumerSecret, writerConsumerAccessToken, writerConsumerAccessSecret);
			break;
		case "reader":
			createConnection(readerConsumerKey, readerConsumerSecret, readerConsumerAccessToken, readerConsumerAccessSecret);
			break;
		default:
			break;
		}
	}

	public TwitterProfile getReceiverProfile() {
		LOGGER.info("Profile retrieved successfully");
		return twitterReceiver.userOperations().getUserProfile();
	}
	
	public TwitterProfile getSenderProfile() {
		LOGGER.info("Profile retrieved successfully");
		return twitterSender.userOperations().getUserProfile();
	}
	
	public CursoredList<TwitterProfile> getFriends() {
		LOGGER.info("Friends retrieved successfully");
		return twitterReceiver.friendOperations().getFriends();
	}
	
	/**
	 * Method for sending messages to other users via twitter
	 * It do not allow sending messages by users with role OBSERVER
	 * @param username Sender twitter screenName
	 * @param msg Message text
	 */
	public DirectMessage sendMessage(String username, String msg) {
		LOGGER.info("Sending message: '" + "' to user: " + username);
		return twitterSender.directMessageOperations().sendDirectMessage(username, msg);			
	}

	public List<DirectMessage> getDirectMessages() {
		return twitterReceiver.directMessageOperations().getDirectMessagesReceived(1, MSG_NUMBER);
	}
}
