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
import static twitterchat.TestProperties.*;

public class TwitterConnectionServiceIntegrationTest {
	
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
    	Assert.assertEquals(1, friends.size());
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
