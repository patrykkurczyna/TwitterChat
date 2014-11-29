package org.tai.twitterchat.domain.chat;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tai.twitterchat.domain.model.ChatMessage;
import org.tai.twitterchat.domain.model.User;

public class ChatRoom {
	private final String name;
	private List<User> participants;
	private List<ChatMessage> messages;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatRoom.class);
	
	public ChatRoom(String name) {
		this.messages = new ArrayList<ChatMessage>();
		this.participants = new ArrayList<User>();
		this.name = name;
	}
	
	public void addParticipant(User participant) {
		participants.add(participant);
		LOGGER.info("Room " + name + ": participant: " + participant.getLogin() + " enters!");
	}
	
	public boolean removeParticipant(User participant) {
		LOGGER.info("Room " + name + ": participant: " + participant.getLogin() + " leaves!");
		return participants.remove(participant);
	}
	
	/**
	 * Method for sending message to the room
	 * @param message {@link ChatMessage}
	 */
	public void sendMessage(ChatMessage message) {
		User sender = message.getSender();
		if (participants.contains(sender)){
			messages.add(message);
			LOGGER.info("Room: " + name + " user: " + sender.getLogin() + " sends message: " + message.getText());
		} else {
			LOGGER.warn("Room: " + name + " user: " + sender.getLogin() + " cannot send message to this room, you are not a member!");
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

	public List<User> getParticipants() {
		return participants;
	}

	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}

	public List<ChatMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<ChatMessage> messages) {
		this.messages = messages;
	}
	
	
}
