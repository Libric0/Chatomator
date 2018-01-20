package chat.client.info;

import java.util.ArrayList;

public class Chat {
	private ArrayList<Message> messages = new ArrayList<Message>();
	private String conversationPartner;

	public void addMessage(Message pMessage) {
		messages.add(pMessage);
	}

	public Chat(String pConversationPartner) {
		conversationPartner = pConversationPartner;
	}
}
