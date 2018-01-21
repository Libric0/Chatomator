package chat.server.info;

import java.util.ArrayList;

public class ChatServer extends Server {
	DatabaseManager dbm = new DatabaseManager();
	ArrayList<ActiveUser> activeUsers = new ArrayList<ActiveUser>();

	public ChatServer() {
		super(200);
		// TODO Auto-generated constructor stub
	}

	public void processNewConnection(String pClientIP, int pClientPort) {
		boolean exists = false;
		for (int i = 0; i < activeUsers.size(); i++) {
			if (activeUsers.get(i).getIP() == pClientIP && activeUsers.get(i).getPort() == pClientPort) {
				send(pClientIP, pClientPort, "Err:0");
			}
		}
		activeUsers.add(new ActiveUser(pClientIP, pClientPort));
	}

	public void processMessage(String pClientIP, int pClientPort, String pMessage) {
		if (pMessage.startsWith("Login:")) {

			login(pMessage.substring(6), pClientIP, pClientPort);

		} else if (pMessage.startsWith("SignUp:")) {
			signUp(pMessage.substring(7), pClientIP, pClientPort);
		} else if (pMessage.startsWith("Msg:")) {
			redirectMessage(pMessage);
		} else if (pMessage.startsWith("GMsg:")) {
			redirectGroupMessage(pMessage);
		} else if (pMessage.startsWith("ToGroup:")){
			addUserToGroup(pClientIP,pClientPort,Integer.parseInt(pMessage.substring(7)));
		} else if (pMessage.startsWith("GetAllMessages:")){
			getAllMessages(pClientIP,pClientPort);
		} else if (pMessage.startsWith("GetAllGroupMessages:")){
			getAllGroupMessages(pClientIP,pClientPort);
		}
	}
	private void addUserToGroup(String pClientIP, int pClientPort, int pGroupID){
	dbm.addUserToGroup(getActiveUsername(pClientIP,pClientPort), pGroupID);
	send(pClientIP,pClientPort,"Added:"+pGroupID);
	}
	private void getAllMessages(String pClientIP, int pClientPort){
		for( Message m : dbm.getAllMessages(getActiveUsername(pClientIP,pClientPort))){
			send(pClientIP, pClientPort, m.toString());
		}
	}
	private void getAllGroupMessages(String pClientIP, int pClientPort){
		for( GroupMessage m : dbm.getAllGroupMessages(getActiveUsername(pClientIP,pClientPort))){
			send(pClientIP, pClientPort, m.toString());
		}
	}
	private void redirectGroupMessage(String pMessage) {
		// TODO das is ne aufgabe für zukunfts-Philipp
		GroupMessage msg = GroupMessage.fromString(pMessage);
		dbm.addGroupMessage(msg);
		String[] groupMemberUsernames = dbm.getGroupMemberUsernames(msg.getGroupID());
		for (int i = 0; i < groupMemberUsernames.length; i++) {
			send(getActiveUser(groupMemberUsernames[i]).getIP(), getActiveUser(groupMemberUsernames[i]).getPort(),
					pMessage);
		}
	}

	private void redirectMessage(String pMessage) {
		// TODO Auto-generated method stub
		Message msg = Message.fromString(pMessage);
		if (getActiveUser(msg.getRecieverName()) != null) {
			send(getActiveUser(msg.getRecieverName()).getIP(), getActiveUser(msg.getRecieverName()).getPort(),
					pMessage);
		}
		dbm.addMessage(msg);
	}

	private void signUp(String substring, String pClientIP, int pClientPort) {
		if (isValidCredentials(substring)) {
			String[] credentials = substring.split(":");
			String username = credentials[0], password = credentials[1];
			if (isUniqueUsername(username)) {
				if (isSafePassword(password)) {
					dbm.addUser(username, password);
					getActiveUser(pClientIP, pClientPort).logIn(username);
					send(pClientIP, pClientPort, username);
				} else
					send(pClientIP, pClientPort, "Err:4");
			} else
				send(pClientIP, pClientPort, "Err:3");
		} else
			send(pClientIP, pClientPort, "Err:1");

	}

	private boolean isSafePassword(String password) {
		// TODO Auto-generated method stub
		if (password.length() < 8 || password.equals(password.toLowerCase()) || password.equals(password.toUpperCase()))
			return false;
		switch (password) {
		case "password":
			return false;
		case "penis1234":
			return false;
		case "Password":
			return false; // copy paste
		case "":
			return false; // copy paste
		default:
			return true;
		}
	}

	private boolean isUniqueUsername(String pUsername) {
		// TODO Auto-generated method stub
		return !dbm.usernameExists(pUsername);
	}

	private boolean isValidCredentials(String pCredentials) {
		int numcol = 0;
		for (int i = 0; i < pCredentials.length(); i++) {
			if (pCredentials.charAt(i) == ':')
				numcol++;
		}
		return numcol == 1;
	}

	private String getActiveUsername(String pClientIP, int pClientPort) {
		int i = 0;
		while (i < activeUsers.size() && !activeUsers.get(i).getIP().equals(pClientIP)
				&& activeUsers.get(i).getPort() != pClientPort) {
			i++;
		}
		return activeUsers.get(i).getUsername();
	}

	private ActiveUser getActiveUser(String username) {
		for (int i = 0; i < activeUsers.size(); i++) {
			if (activeUsers.get(i).getUsername().equals(username)) {
				return activeUsers.get(i);

			}
		}
		return null;
	}

	private void login(String pCredentials, String pClientIP, int pClientPort) {
		if (isValidCredentials(pCredentials)) {
			String[] credentials = pCredentials.split(":");
			String username = credentials[0];
			String password = credentials[1];
			if (!isUniqueUsername(username)) {
				if (dbm.getPassword(username).equals(password)) {
					int i = 0;
					while (!activeUsers.get(i).getIP().equals(pClientIP) && i < activeUsers.size()
							&& activeUsers.get(i).getPort() != pClientPort) {
						i++;
					}
					activeUsers.get(i).logIn(username);
					send(pClientIP, pClientPort, username);
				} else {
					send(pClientIP, pClientPort, "Err:2");
				}
			} else
				send(pClientIP, pClientPort, "Err:1");
		}else
			send(pClientIP, pClientPort, "Err:5");

	}

	public void processClosedConnection(String pClientIP, int pClientPort) {
		removeActiveUser(getActiveUser(pClientIP, pClientPort));
	}

	private void removeActiveUser(ActiveUser activeUser) {
		// TODO Auto-generated method stub
		for (int i = 0; i < activeUsers.size(); i++) {
			if (activeUsers.get(i).equals(activeUser)) {
				activeUsers.remove(i);
				break;
			}
		}
	}

	private ActiveUser getActiveUser(String pClientIP, int pClientPort) {
		for (int i = 0; i < activeUsers.size(); i++) {
			if (activeUsers.get(i).getIP().equals(pClientIP) && activeUsers.get(i).getPort() == pClientPort) {
				return activeUsers.get(i);
			}
		}
		return null;
	}

	// Err:0 Device already connected to server
	// Err:1 Username/ Password are invalid
	// Err:2 Username and Password dont match
	// Err:3 Username not unique
	// Err:4 Password is unsafe
	// Err:5 No user with this username exists

}
