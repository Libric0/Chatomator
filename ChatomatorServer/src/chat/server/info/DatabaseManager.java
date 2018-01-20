package chat.server.info;

import java.time.Instant;
import java.util.ArrayList;

public class DatabaseManager {
	private DatabaseConnector db;

	public DatabaseManager() {
		db = new LocalDatabaseConnector("./res/chatdb.db");
		db.executeStatement("CREATE TABLE IF NOT EXISTS `users` (" + "`name`TEXT NOT NULL,"
				+ "`password`TEXT NOT NULL," + "PRIMARY KEY(`name`)" + ");"
				+ "CREATE TABLE IF NOT EXISTS `user_group` (" + "`username`TEXT NOT NULL,"
				+ "`groupid`INTEGER NOT NULL" + ");\"); ");
		db.executeStatement("CREATE TABLE IF NOT EXISTS `message` ("
				+ "`sender`TEXT NOT NULL," + "`reciever`TEXT NOT NULL," + "`message`TEXT NOT NULL,"
				+ "`timestamp`INTEGER NOT NULL" + ");\"); ");
		db.executeStatement("CREATE TABLE IF NOT EXISTS `groupmessage` ("
				+ "`sender`TEXT NOT NULL," + "`groupid`INTEGER NOT NULL," + "`message`TEXT NOT NULL,"
				+ "`timestamp`INTEGER NOT NULL" + ");\"); ");
		db.executeStatement("CREATE TABLE IF NOT EXISTS `group` ("
				+ "`name`TEXT NOT NULL" + ");");
	}
	public String[] getGroupMemberUsernames(int pGroupID) {
		db.executeStatement("SELECT username FROM user_group WHERE groupid="+pGroupID);
		String[] toReturn = new String[db.getCurrentQueryResult().getRowCount()];
		for(int i=0;i<toReturn.length;i++) {
			toReturn[i] = db.getCurrentQueryResult().getData()[i][0];
		}
		return toReturn;
	}
	public void addUserToGroup(String pUsername, int pGroupID) {
		db.executeStatement("INSERT INTO user_group VALUES ('"+pUsername+", "+pGroupID+");");
	}
	public void addUser(String pUsername, String pPassword) {
		db.executeStatement("SELECT * from users where username='" + pUsername + "'");
		// id nicht nötig da sqlite automatisch ids vergibt
		// int newID= db.getCurrentQueryResult().getRowCount();
		if (db.getCurrentQueryResult().getRowCount() == 0)
			db.executeStatement("INSERT INTO Users VALUES(" + pUsername + "," + pPassword + ")");
	}

	public void addMessage(String sender, String reciever, String message) {
		db.executeStatement("Insert INTO message VALUES ('" + sender + "', '" + reciever + "', '" + message + "', "
				+ Instant.now().getEpochSecond() + ")");
	}

	public Message[] getMessages(String user1, String user2) {
		db.executeStatement("SELECT * from message where (sender='" + user1 + "' AND reciever='" + user2
				+ "') OR  (sender='" + user1 + "' AND reciever='" + user2 + "')");
		Message[] toReturn = new Message[db.getCurrentQueryResult().getRowCount()];
		String[][] data = db.getCurrentQueryResult().getData();
		for (int i = 0; i < db.getCurrentQueryResult().getRowCount(); i++) {
			toReturn[i] = new Message(data[i][0], data[i][1],Integer.parseInt(data[i][3]), data[i][2]);
		}
		return toReturn;
	}

	public void addGroupMessage(String sender, int groupid, String message) {
		db.executeStatement("Insert INTO groupmessage VALUES ('" + sender + "', '" + groupid + "', '" + message + "', "
				+ Instant.now().getEpochSecond() + ")");
	}

	public GroupMessage[] getGroupMessages(int groupid) {
		db.executeStatement("SELECT * from groupmessage where groupid=" + groupid);
		GroupMessage[] toReturn = new GroupMessage[db.getCurrentQueryResult().getRowCount()];
		String[][] data = db.getCurrentQueryResult().getData();
		for (int i = 0; i < db.getCurrentQueryResult().getRowCount(); i++) {
			toReturn[i] = new GroupMessage(data[i][0], Integer.parseInt(data[i][1]),Integer.parseInt(data[i][3]), data[i][2]);
		}
		return toReturn;
	}

	public String getPassword(String pUsername) {
		db.executeStatement("Select password from user where Username='" + pUsername + "';");
		if (db.getCurrentQueryResult().getRowCount() != 0) {
			// System.out.println(db.getCurrentQueryResult().getData()[0][0]);
			return db.getCurrentQueryResult().getData()[0][0];
		} else
			return null;
	}

	public boolean usernameExists(String pUsername) {
		db.executeStatement("select username from users where username='" + pUsername + "';");
		return db.getCurrentQueryResult().getRowCount()!=0;
	}

	public void addMessage(Message pMessage) {
		addMessage(pMessage.getSenderName(), pMessage.getRecieverName(), pMessage.getContent());
		
	}
	public int addGroup(String name) {
		db.executeStatement("INSERT INTO group VALUES ('"+name+"');");
		db.executeStatement("SELECT rowid FROM group");
		return Integer.parseInt(db.getCurrentQueryResult().getData()[db.getCurrentQueryResult().getRowCount()-1][0]);
		//return 0;
	}
	public void addGroupMessage(GroupMessage msg) {
		// TODO Auto-generated method stub
		addGroupMessage(msg.getSenderName(),msg.getGroupID(),msg.getContent());
	}

}
