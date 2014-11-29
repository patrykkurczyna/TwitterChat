package org.tai.twitterchat.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.DirectMessage;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;
import org.tai.twitterchat.domain.model.User;

/**
 * This service is used to connect with twitter API and perform operations
 * 
 * @author patrykkurczyna
 *
 */
@Service
public class TwitterConnectionService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TwitterConnectionService.class);

	private Twitter twitter;

	public TwitterConnectionService(User user) {
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

	public void sendMessage(String user, String msg) {
		LOGGER.info("Sending message: '" + "' to user: " + user);
		twitter.directMessageOperations().sendDirectMessage(user, msg);
	}

	public List<DirectMessage> getDirectMessages() {
		return twitter.directMessageOperations().getDirectMessagesReceived();
	}
}
