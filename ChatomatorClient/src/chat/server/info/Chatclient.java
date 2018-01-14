package chat.server.info;

import javax.swing.JFrame;

public class Chatclient extends Client {
	GUI g;
	ChatGui cg;
	int clientState = 0;
	private String username;

	public Chatclient() {
		super("localhost", 200);
		g = new GUI(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processMessage(String pMessage) {
		// TODO Auto-generated method stub
		if (clientState == 0) {
			if (!pMessage.equals("Invalid")&&!pMessage.startsWith(":")) {
				clientState = 1;
				username = pMessage;
				g.setVisible(false);
				g.dispose();
				cg= new ChatGui(this);
			}
			else {
				g.loginError();
			}
		}
		if(clientState==1) {
			if(pMessage.startsWith(":")) {
				cg.addMessage(pMessage.substring(1));
			}
		}
	}

	public void sendMessage(String text) {
		// TODO Auto-generated method stub
		send("Message:"+text);
		
	}

}
