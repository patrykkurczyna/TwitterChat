package org.tai.twitterchat.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

/**
 * This service is used to connect with twitter API and perform operations
 * @author patrykkurczyna
 *
 */
@Service
public class TwitterConnectionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterConnectionService.class);
	
	private final String CONSUMER_KEY = "xXzWfyBVBhuUUTFF5Ykbachhr";
	private final String CONSUMER_SECRET = "SvV0s22vKJ1mF4AixAsGythTWCdr4PjwL8K20AgQSDqeOTEeeg";
	private final String ACCESS_TOKEN = "1197751716-ZWL0nIBKgRKiPK8MNJ9W1DLCBTTcsCl7ImsrqX7";
	private final String ACCESS_SECRET = "l2jBJdyrc2OwQIy22EJReJUwLSE1DjLa3JC2yKnN4VsYD";
	
	private Twitter twitter;
	
	public TwitterConnectionService(){
		this.twitter = new TwitterTemplate(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_SECRET);
	}
	
	public TwitterProfile getProfile() {
		LOGGER.info("Profile retrieved successfully");
		return twitter.userOperations().getUserProfile();
	}
	
	public CursoredList<TwitterProfile> getFriends() {
		LOGGER.info("Friends retrieved successfully");
		return twitter.friendOperations().getFriends();
	}
	
	public void sendMessage(String user, String msg) {
		LOGGER.info("Sending message: '" + "' to user: " + user);
		twitter.directMessageOperations().sendDirectMessage(user, msg);
	}
}
