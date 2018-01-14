package chat.server.info;

import java.util.ArrayList;

public class ChatServer extends Server {
	DatabaseManager dbm = new DatabaseManager();
	ArrayList<User> users = new ArrayList<User>();

	public ChatServer() {
		super(200);
		// TODO Auto-generated constructor stub
	}

	public void processNewConnection(String pClientIP, int pClientPort) {
		users.add(new User(pClientIP, pClientPort));
	}

	public void processMessage(String pClientIP, int pClientPort, String pMessage) {
		if (pMessage.startsWith("login:")) {
			login(pMessage.substring(6), pClientIP, pClientPort);
		}else if(pMessage.startsWith("Message:")) {
			sendToAll(":"+getUsername(pClientIP, pClientPort)+": "+pMessage.substring(8));
		}
	}

	private String getUsername(String pClientIP, int pClientPort) {
		int i=0;
		while(i<users.size()&&!users.get(i).getIP().equals(pClientIP)&& users.get(i).getPort()!=pClientPort) {
			i++;
		}
		return users.get(i).getUsername();
	}

	private void login(String creds, String ip, int port) {
		String[] credentials = creds.split(":");
		String username= credentials[0];
		String password= credentials[1];
		if (dbm.getPassword(username).equals(password)) {
			int i = 0;
			while (!users.get(i).getIP().equals(ip) && i < users.size()&&users.get(i).getPort()!=port) {
				i++;
			}
			users.get(i).logIn(username);
			send(ip,port,password);
		} else {
			send(ip, port, "Invalid");
		}

	}

	public void processClosedConnection(String pClientIP, int pClientPort) {
	}

}
