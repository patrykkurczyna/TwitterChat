package twitterchat;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.DirectMessage;
import org.springframework.social.twitter.api.TwitterProfile;
import org.tai.twitterchat.domain.model.User;
import org.tai.twitterchat.service.TwitterConnectionService;

public class TwitterConnectionServiceIntegrationTest {
	private final String consumerKey = "xXzWfyBVBhuUUTFF5Ykbachhr";
	private final String consumerSecret = "SvV0s22vKJ1mF4AixAsGythTWCdr4PjwL8K20AgQSDqeOTEeeg";
	private final String consumerAccessToken = "1197751716-ZWL0nIBKgRKiPK8MNJ9W1DLCBTTcsCl7ImsrqX7";
	private final String consumerAccessSecret = "l2jBJdyrc2OwQIy22EJReJUwLSE1DjLa3JC2yKnN4VsYD";
	private final String FRIEND_NAME = "PKurczynaTAI";
	private final String MESSAGE_TEXT = "test message";
	private final String TWITTER_NAME = "Patryk Kurczyna";
	
	@Mock
	User user;
	
    private TwitterConnectionService service;
    
    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    	when(user.getLogin()).thenReturn("Janek");
    	when(user.getConsumerKey()).thenReturn(consumerKey);
    	when(user.getConsumerSecret()).thenReturn(consumerSecret);
    	when(user.getConsumerAccessToken()).thenReturn(consumerAccessToken);
    	when(user.getConsumerAccessSecret()).thenReturn(consumerAccessSecret);
    	service = new TwitterConnectionService(user);
    }
  
    @Test
    public void testGettingFriendsFromTwitter() {   	 	
    	CursoredList<TwitterProfile> friends = service.getFriends();
    	boolean friendFound = false;
    	for (TwitterProfile friend : friends) {
    		if (friend.getScreenName().equals(FRIEND_NAME)) {
    			friendFound = true;
    		}
    	}
    	
    	Assert.assertTrue(friendFound);
    	Assert.assertEquals(20, friends.size());
    }
    
    @Test
    public void getUserProfile() {
    	TwitterProfile profile = service.getProfile();
    	Assert.assertEquals(TWITTER_NAME, profile.getName());
    }
    
//    @Test
//    public void testSendingMessage() {
//    	DirectMessage msg = service.sendMessage(FRIEND_NAME, MESSAGE_TEXT);
//    	
//    	Assert.assertEquals(FRIEND_NAME, msg.getRecipient().getScreenName());
//    }
}
