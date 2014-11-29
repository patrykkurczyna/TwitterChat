package twitterchat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.tai.twitterchat.domain.chat.ChatRoom;
import org.tai.twitterchat.domain.model.ChatMessage;
import org.tai.twitterchat.domain.model.User;

@RunWith(MockitoJUnitRunner.class)
public class ChatRoomTest {
    
    @Mock
    private User user;
    
    @Mock
    private ChatMessage message;
    
    @InjectMocks
    private ChatRoom chatRoom = new ChatRoom("test");
    
    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    }
  
    @Test
    public void testAddingNewParticipant() {   	
    	when(user.getLogin()).thenReturn("user1");
    	chatRoom.addParticipant(user);   	
    	
    	Assert.assertEquals(chatRoom.getParticipants().size(), 1);
    }
    
    @Test
    public void testAddingParticipantWhenExisting() {   	
    	when(user.getLogin()).thenReturn("alreadyExistingUser");
    	chatRoom.addParticipant(user);
    	chatRoom.addParticipant(user);
    	Assert.assertEquals(1, chatRoom.getParticipants().size());
    }
    
    @Test
    public void removingParticipant() {
    	when(user.getLogin()).thenReturn("removedUser");
    	Set<User> participants = new HashSet<User>();
    	participants.add(user);
    	chatRoom.setParticipants(participants);
    	chatRoom.addParticipant(user);
    	
    	Assert.assertEquals(1, chatRoom.getParticipants().size());
    	
    	chatRoom.removeParticipant(user);
    	Assert.assertEquals(0, chatRoom.getParticipants().size());
    	Assert.assertEquals(false, chatRoom.getParticipants().contains(user));
    }
    
    @Test
    public void testSendingMessage() {
    	when(user.getLogin()).thenReturn("sender1");
    	when(message.getSender()).thenReturn(user);
    	when(message.getText()).thenReturn("My message");
    	
    	chatRoom.addParticipant(user);
    	chatRoom.sendMessage(message);
    	
    	Assert.assertEquals(1, chatRoom.getMessages().size());
    	
    	chatRoom.removeParticipant(user);
    	chatRoom.sendMessage(message);
    	
    	Assert.assertEquals(1, chatRoom.getMessages().size());
    }
    
    
    
}
