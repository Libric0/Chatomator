package chat.server.info;

//TODO USE IT
public class GroupMessage {
	private String senderName;
	private int groupid;
	private String content;
	private int timestamp;
	public GroupMessage(String pSenderName, int pgroupid,int pTimeStamp, String pContent) {
		senderName=pSenderName;
		groupid=pgroupid;
		content=pContent;
		timestamp=pTimeStamp;
	}

	public String getSenderName() {
		return senderName;
	}

	public int getGroupID() {
		return groupid;
	}

	public String getContent() {
		return content;
	}
	public static GroupMessage fromString(String m) {
		m = m.substring(5);
		String pGroupID = "";
		String pContent = "";
		String pSenderName = "";
		String pTimestamp = "";
		int i = 0;
		while (i < m.length() && m.charAt(i) != ':') {
			pSenderName= pSenderName+m.charAt(i);
			i++;
		}
		i++;
		while(i < m.length() && m.charAt(i) != ':') {
			pGroupID=pGroupID+m.charAt(i);
			i++;
		}
		i++;
		while(i < m.length() && m.charAt(i) != ':') {
			pTimestamp=pTimestamp+m.charAt(i);
			i++;
		}
		i++;
		while(i<m.length()) {
			pContent=pContent+m.charAt(i);
		}
		return new GroupMessage(pSenderName, Integer.parseInt(pGroupID),Integer.parseInt(pTimestamp),pContent);
	}
	public static String toString(GroupMessage m) {
		return "GMsg:"+m.getSenderName()+":"+m.getGroupID()+":"+m.getTimeStamp()+":"+m.getContent();
	}

	public int getTimeStamp() {
		// TODO Auto-generated method stub
		return timestamp;
	}
}
