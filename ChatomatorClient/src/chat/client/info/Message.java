package chat.client.info;

//TODO USE IT
public class Message {
	private String senderName;
	private String recieverName;
	private String content;
	public Message(String pSenderName, String pRecieverName, String pContent) {
		senderName=pSenderName;
		content=pContent;
	}
	public static String toString(Message m) {
		return null;
	}
}
