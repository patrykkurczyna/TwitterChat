package twitterchat;

import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.twitter.api.DirectMessage;
import org.springframework.social.twitter.api.TwitterProfile;
import org.tai.twitterchat.domain.chat.ChatRoom;

@RunWith(MockitoJUnitRunner.class)
public class ChatRoomTest {
       
    private static final String USER_NAME = "user1";
    
    @Mock
    private TwitterProfile profile;
    
	@Mock
    private DirectMessage message;
    
    @InjectMocks
    private ChatRoom chatRoom = new ChatRoom("test");
    
    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
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
    
    @Test
    public void testSendingMessage() {
    	when(profile.getScreenName()).thenReturn(USER_NAME);
    	when(message.getSender()).thenReturn(profile);
    	when(message.getText()).thenReturn("My message");
    	
    	chatRoom.addParticipant(USER_NAME);
    	chatRoom.sendMessage(message);
    	
    	Assert.assertEquals(1, chatRoom.getMessages().size());
    	
    	chatRoom.removeParticipant(USER_NAME);
    	chatRoom.sendMessage(message);
    	
    	Assert.assertEquals(1, chatRoom.getMessages().size());
    }
    
    
    
}
