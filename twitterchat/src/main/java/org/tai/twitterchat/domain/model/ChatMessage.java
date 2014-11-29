package org.tai.twitterchat.domain.model;

import org.joda.time.DateTime;

/**
 * This class represents message visible on chat
 * @author patrykkurczyna
 *
 */
public class ChatMessage {
	private final User sender;
	private final String text;
	private final DateTime sendTime;
	
	public ChatMessage(User sender, String text, DateTime sendTime){
		this.sender = sender;
		this.sendTime = sendTime;
		this.text = text;
	}
	
	@Override
	public String toString() {
		return String.format("[%s] %s: %s", sendTime, sender.getLogin(), text);
	}
	
	/**
	 * Gets user who sended message
	 * @return {@link User}
	 */
	public User getSender() {
		return sender;
	}

	/**
	 * Gets message text
	 * @return
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Gets time when message was sent
	 * @return
	 */
	public DateTime getSendTime() {
		return sendTime;
	}
	
}
