package chat.server.info;

//TODO USE IT
public class Message {
	private String senderName;
	private String recieverName;
	private String content;
	private int timeStamp;
	public Message(String pSenderName, String pRecieverName,int pTimeStamp, String pContent) {
		senderName = pSenderName;
		recieverName = pRecieverName;
		content = pContent;
		timeStamp = pTimeStamp;
	}

	public String getSenderName() {
		return senderName;
	}

	public String getRecieverName() {
		return recieverName;
	}
	public int getTimeStamp() {
		return timeStamp;
	}
	public String getContent() {
		return content;
	}

	public String toString() {
		return "Msg:"+getSenderName()+":"+getRecieverName()+":"+getTimeStamp()+":"+getContent();
	}

	public static Message fromString(String m) {
		m = m.substring(4);
		String pRecieverName = "";
		String pContent = "";
		String pSenderName = "";
		String pTimeStamp = "";
		int i = 0;
		while (i < m.length() && m.charAt(i) != ':') {
			pSenderName= pSenderName+m.charAt(i);
			i++;
		}
		i++;
		while(i < m.length() && m.charAt(i) != ':') {
			pRecieverName=pRecieverName+m.charAt(i);
			i++;
		}
		i++;
		while(i < m.length() && m.charAt(i) != ':') {
			pTimeStamp=pTimeStamp+m.charAt(i);
			i++;
		}
		i++;
		while(i<m.length()) {
			pContent=pContent+m.charAt(i);
		}
		return new Message(pSenderName, pRecieverName,Integer.parseInt(pTimeStamp), pContent);
	}
}
