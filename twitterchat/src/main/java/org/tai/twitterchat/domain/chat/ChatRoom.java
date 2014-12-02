package org.tai.twitterchat.domain.chat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.twitter.api.DirectMessage;
import org.tai.twitterchat.domain.model.User;
import org.tai.twitterchat.domain.model.UserRole;
import org.tai.twitterchat.service.TwitterConnectionService;

/**
 * Class representing chat room
 * @author patrykkurczyna
 *
 */
public class ChatRoom {
	private final static String MSG_RECEIVER = "AdmiinTAI";
	private final String name;
	private Set<String> participants;
	private List<DirectMessage> messages;

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatRoom.class);
    
    /**
     * Service for perform twitter API operations
     */
    private TwitterConnectionService service;
	
    public ChatRoom(String name) {
		this.messages = new ArrayList<DirectMessage>();
		this.participants = new HashSet<String>();
		this.name = name;
    }
    
    /**
     * 
     * @param name chat room name
     * @param admin user to whom messages are sent via twitter and from whom they are retrieved to populate chat
     * @param sender user who is actual sender of a message
     */
	public ChatRoom(String name, User admin, User sender) {
		this(name);
		addParticipant(sender.getLogin());
		this.service = sender.getService();
	}
	
	/**
	 * Method for retrieve messages from twitter and populate our chat
	 */
	public void synchronizeWithTwitter() {
		for (DirectMessage msg : service.getDirectMessages()){
			if (!messageAlreadyExists(msg)) {
				LOGGER.info("There is a new message from twitter! Adding to chat: " + msg.getText() + " from " + msg.getSender().getName());
				messages.add(msg);
			}
		}
	}
	
	/**
	 * Returns true when message is already in our chat
	 * @param msg message for check
	 * @return
	 */
	private boolean messageAlreadyExists(DirectMessage msg) {
		for (DirectMessage message : messages) {
			if (message.getId() == msg.getId()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adding participant name to the list
	 * @param participant
	 */
	public void addParticipant(String participant) {
		if (participants.add(participant)) {
			LOGGER.info("Room " + name + ": participant: " + participant + " enters!");
		};
	}
	
	/**
	 * Removing participant
	 * @param user
	 * @return
	 */
	public boolean removeParticipant(String user) {
		LOGGER.info("Room " + name + ": participant: " + user + " leaves!");
		return participants.remove(user);
	}
	
	/**
	 * Method for sending message to the room
	 * @param message {@link ChatMessage}
	 */
	public void sendMessage(User sender, String message) {
		if (participants.contains(sender.getLogin()) && !(sender.getUserRole() == UserRole.READER)){
			// set service to send messages from new sender
			service = sender.getService();
			DirectMessage directMsg = service.sendMessage(MSG_RECEIVER, message);
			messages.add(directMsg);
			LOGGER.info("Room: " + name + " user: " + sender + " sends message: " + message);
		} else {
			LOGGER.warn("Room: " + name + " user: " + sender + " cannot send message to this room, you are not a member!");
		}
	}
		
	/**
	 * Method for clearing all messages in room
	 */
	public void clearMessages() {
		messages.clear();
		LOGGER.info("Room: " + name + " all messages were cleared!");
	}

	public String getName() {
		return name;
	}

	public Set<String> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<String> participants) {
		this.participants = participants;
	}

	public List<DirectMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<DirectMessage> messages) {
		this.messages = messages;
	}
	
	
}
