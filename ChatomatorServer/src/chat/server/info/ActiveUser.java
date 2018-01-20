package chat.server.info;

public class ActiveUser {
	private String ip;
	private int port;
	private String username="";
	public ActiveUser(String pip, int port) {
		ip=pip;
	}
	public void logIn(String pusername) {
		username=pusername;
	}
	public String getIP() {
		return ip;
	}
	public int getPort() {
		return port;
	}
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
}
