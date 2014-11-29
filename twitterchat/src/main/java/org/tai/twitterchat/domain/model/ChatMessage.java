package org.tai.twitterchat.domain.model;

import org.joda.time.DateTime;

public class ChatMessage {
	private final User sender;
	private final String text;
	private final DateTime sendTime;
	
	public ChatMessage(User sender, String text, DateTime sendTime){
		this.sender = sender;
		this.sendTime = sendTime;
		this.text = text;
	}

	public User getSender() {
		return sender;
	}

	public String getText() {
		return text;
	}

	public DateTime getSendTime() {
		return sendTime;
	}
	
}
