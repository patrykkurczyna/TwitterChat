package org.tai.twitterchat.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.DirectMessage;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.tai.twitterchat.domain.model.User;
import org.tai.twitterchat.domain.model.UserRole;

/**
 * This service is used to connect with twitter API and perform operations
 * 
 * @author patrykkurczyna
 *
 */
public class TwitterConnectionService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TwitterConnectionService.class);

	private Twitter twitter;
	private User user;

	public TwitterConnectionService(User user) {
		this.user = user;
		this.twitter = new TwitterTemplate(user.getConsumerKey(),
				user.getConsumerSecret(), user.getConsumerAccessToken(),
				user.getConsumerAccessSecret());
	}

	public TwitterProfile getProfile() {
		LOGGER.info("Profile retrieved successfully");
		return twitter.userOperations().getUserProfile();
	}
	
	public CursoredList<TwitterProfile> getFriends() {
		LOGGER.info("Friends retrieved successfully");
		return twitter.friendOperations().getFriends();
	}
	
	/**
	 * Method for sending messages to other users via twitter
	 * It do not allow sending messages by users with role OBSERVER
	 * @param username Sender twitter screenName
	 * @param msg Message text
	 */
	public DirectMessage sendMessage(String username, String msg) {
		if (user.getUserRole() == UserRole.OBSERVER) {
			LOGGER.error("Cannot send message: '" + "' to user: " + username + "! You do not have permissions to write");
			return null;
		} else {
			LOGGER.info("Sending message: '" + "' to user: " + username);
			return twitter.directMessageOperations().sendDirectMessage(username, msg);			
		}
	}

	public List<DirectMessage> getDirectMessages() {
		return twitter.directMessageOperations().getDirectMessagesReceived();
	}
}
