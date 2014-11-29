package twitterchat;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.tai.twitterchat.domain.chat.ChatRoom;
import org.tai.twitterchat.domain.model.User;

@RunWith(MockitoJUnitRunner.class)
public class ChatRoomTest {
    
    @Mock
    private User user;
    
    @InjectMocks
    private ChatRoom chatRoom = new ChatRoom("test");
    
    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    }
  
    @Test
    public void testAddingParticipant() {   	
    	when(user.getLogin()).thenReturn("user1");
    	chatRoom.addParticipant(user);
    	
    	Assert.assertEquals(chatRoom.getParticipants().size(), 1);
    }
    
    
}
