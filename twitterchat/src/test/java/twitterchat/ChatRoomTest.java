package twitterchat;

import static org.mockito.Mockito.when;
import static twitterchat.TestProperties.consumerAccessSecret;
import static twitterchat.TestProperties.consumerAccessToken;
import static twitterchat.TestProperties.consumerKey;
import static twitterchat.TestProperties.consumerSecret;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.twitter.api.DirectMessage;
import org.springframework.social.twitter.api.TwitterProfile;
import org.tai.twitterchat.domain.chat.ChatRoom;
import org.tai.twitterchat.domain.model.User;
import org.tai.twitterchat.service.TwitterConnectionService;


@RunWith(MockitoJUnitRunner.class)
public class ChatRoomTest {
       
    private static final String USER_NAME = "user1";
    
    @Mock
    private User user;
    
    @Mock
    private TwitterProfile profile;
    
    @Mock
    private User admin;
    
	@Mock
    private DirectMessage message;
    
	private TwitterConnectionService service;
	
    private ChatRoom chatRoom;
    
 
    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    	when(user.getLogin()).thenReturn("Janek");
    	when(user.getConsumerKey()).thenReturn(consumerKey);
    	when(user.getConsumerSecret()).thenReturn(consumerSecret);
    	when(user.getConsumerAccessToken()).thenReturn(consumerAccessToken);
    	when(user.getConsumerAccessSecret()).thenReturn(consumerAccessSecret);
    	service = new TwitterConnectionService(user);
    	chatRoom = new ChatRoom("Test room", service);   	
    }
    
    @Test
    public void testAddingNewParticipant() {   	
    	chatRoom.addParticipant(USER_NAME);   	
    	
    	Assert.assertEquals(chatRoom.getParticipants().size(), 1);
    }
    
    @Test
    public void testAddingParticipantWhenExisting() {   	
    	chatRoom.addParticipant(USER_NAME);
    	chatRoom.addParticipant(USER_NAME);
    	Assert.assertEquals(1, chatRoom.getParticipants().size());
    }
    
    @Test
    public void removingParticipant() {
    	Set<String> participants = new HashSet<String>();
    	participants.add(USER_NAME);
    	chatRoom.setParticipants(participants);
    	chatRoom.addParticipant(USER_NAME);
    	
    	Assert.assertEquals(1, chatRoom.getParticipants().size());
    	
    	chatRoom.removeParticipant(USER_NAME);
    	Assert.assertEquals(0, chatRoom.getParticipants().size());
    	Assert.assertEquals(false, chatRoom.getParticipants().contains(USER_NAME));
    }
    
//    @Test
//    public void testSendingMessage() {
//    	chatRoom.addParticipant(USER_NAME);
//    	chatRoom.sendMessage(USER_NAME, MESSAGE_TEXT);
//    	
//    	Assert.assertEquals(1, chatRoom.getMessages().size());
//    	
//
//		chatRoom.synchronizeWithTwitter();
//    	Assert.assertEquals(21, chatRoom.getMessages().size());
//    }
    
    @Test
    public void testSynchronizingMessagesWithTwitter() {

    }
    
}
