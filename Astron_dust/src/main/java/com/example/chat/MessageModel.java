package com.example.chat;

public class MessageModel {
	private String type;
	private String userId;
	private String text;
	private String sender;
	
	public String getType() { 
        return this.type;
    }

    public void setType(String type) {  
        this.type = type;
    }
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
	
	
	
}
