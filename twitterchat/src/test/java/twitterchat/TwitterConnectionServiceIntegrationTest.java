package twitterchat;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.TwitterProfile;
import org.tai.twitterchat.domain.model.User;
import org.tai.twitterchat.service.TwitterConnectionService;

public class TwitterConnectionServiceIntegrationTest {
	private final String consumerKey = "xXzWfyBVBhuUUTFF5Ykbachhr";
	private final String consumerSecret = "SvV0s22vKJ1mF4AixAsGythTWCdr4PjwL8K20AgQSDqeOTEeeg";
	private final String consumerAccessToken = "1197751716-ZWL0nIBKgRKiPK8MNJ9W1DLCBTTcsCl7ImsrqX7";
	private final String consumerAccessSecret = "l2jBJdyrc2OwQIy22EJReJUwLSE1DjLa3JC2yKnN4VsYD";
	private final String FRIEND_TEST_NAME = "PKurczynaTAI";
	
	@Mock
	User user;
	
    private TwitterConnectionService service;
    
    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    }
  
    @Test
    public void testGettingFriendsFromTwitter() {   	
    	when(user.getLogin()).thenReturn("Janek");
    	when(user.getConsumerKey()).thenReturn(consumerKey);
    	when(user.getConsumerSecret()).thenReturn(consumerSecret);
    	when(user.getConsumerAccessToken()).thenReturn(consumerAccessToken);
    	when(user.getConsumerAccessSecret()).thenReturn(consumerAccessSecret);
    	
    	service = new TwitterConnectionService(user);
    	
    	CursoredList<TwitterProfile> friends = service.getFriends();
    	boolean friendFound = false;
    	for (TwitterProfile friend : friends) {
    		if (friend.getScreenName().equals(FRIEND_TEST_NAME)) {
    			friendFound = true;
    		}
    	}
    	
    	Assert.assertTrue(friendFound);
    	Assert.assertEquals(20, friends.size());
    }
}
