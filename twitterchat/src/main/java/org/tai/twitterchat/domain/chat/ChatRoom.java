package org.tai.twitterchat.domain.chat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.twitter.api.DirectMessage;
import org.tai.twitterchat.domain.model.User;
import org.tai.twitterchat.service.TwitterConnectionService;

public class ChatRoom {
	private final static String MSG_RECEIVER = "AdmiinTAI";
	private final String name;
	private Set<String> participants;
	private List<DirectMessage> messages;
	private User admin;

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatRoom.class);
    
    private TwitterConnectionService service;
	
    public ChatRoom(String name) {
		this.messages = new ArrayList<DirectMessage>();
		this.participants = new HashSet<String>();
		this.name = name;
    }
    
	public ChatRoom(String name, User admin) {
		this(name);
		this.admin = admin;
		this.service = new TwitterConnectionService(admin);
	}
	
	public ChatRoom(String name, TwitterConnectionService service) {
		this(name);
		this.service = service;
	}
	
	public void synchronizeWithTwitter() {
		for (DirectMessage msg : service.getDirectMessages()){
			if (!messageAlreadyExists(msg)) {
				LOGGER.info("There is a new message from twitter! Adding to chat: " + msg.getText() + " from " + msg.getSender().getName());
				messages.add(msg);
			}
		}
	}
	
	private boolean messageAlreadyExists(DirectMessage msg) {
		for (DirectMessage message : messages) {
			if (message.getId() == msg.getId()) {
				return true;
			}
		}
		return false;
	}
	
	public void addParticipant(String participant) {
		if (participants.add(participant)) {
			LOGGER.info("Room " + name + ": participant: " + participant + " enters!");
		};
	}
	
	public boolean removeParticipant(String user) {
		LOGGER.info("Room " + name + ": participant: " + user + " leaves!");
		return participants.remove(user);
	}
	
	/**
	 * Method for sending message to the room
	 * @param message {@link ChatMessage}
	 */
	public void sendMessage(String sender, String message) {
		if (participants.contains(sender)){
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
