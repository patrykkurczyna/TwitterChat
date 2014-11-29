package org.tai.twitterchat.service;

import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

@Service
public class TwitterConnectionService {
	private final String CONSUMER_KEY = "xXzWfyBVBhuUUTFF5Ykbachhr";
	private final String CONSUMER_SECRET = "SvV0s22vKJ1mF4AixAsGythTWCdr4PjwL8K20AgQSDqeOTEeeg";
	private final String ACCESS_TOKEN = "1197751716-ZWL0nIBKgRKiPK8MNJ9W1DLCBTTcsCl7ImsrqX7";
	private final String ACCESS_SECRET = "l2jBJdyrc2OwQIy22EJReJUwLSE1DjLa3JC2yKnN4VsYD";
	
	private Twitter twitter;
	
	public TwitterConnectionService(){
		this.twitter = new TwitterTemplate(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_SECRET);
	}
	
	public TwitterProfile getProfile() {
		return twitter.userOperations().getUserProfile();
	}
	
	public CursoredList<TwitterProfile> getFriends() {
		return twitter.friendOperations().getFriends();
	}
}
