package org.tai.twitterchat.domain.chat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.twitter.api.DirectMessage;
import org.tai.twitterchat.domain.model.ChatMessage;

public class ChatRoom {
	private final String name;
	private Set<String> participants;
	private List<DirectMessage> messages;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatRoom.class);
	
	public ChatRoom(String name) {
		this.messages = new ArrayList<DirectMessage>();
		this.participants = new HashSet<String>();
		this.name = name;
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
	public void sendMessage(DirectMessage message) {
		String sender = message.getSender().getScreenName();
		if (participants.contains(sender)){
			messages.add(message);
			LOGGER.info("Room: " + name + " user: " + sender + " sends message: " + message.getText());
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
