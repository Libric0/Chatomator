package chat.client.info;

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
			if (!pMessage.startsWith("Msg:")&&!pMessage.startsWith("Err:")&&
					!pMessage.startsWith("GMsg:")) {
				clientState = 1;
				username = pMessage;
				g.setVisible(false);
				g.dispose();
				cg= new ChatGui(this);
			}
			else {
				if(pMessage.startsWith("Err:"))
				g.loginError();
			}
		}
		if(clientState==1) {
			if(pMessage.startsWith("Msg:")) {
				addMessage(pMessage);
			}else if(pMessage.startsWith("GMsg:")) {
				addGroupMessage(pMessage);
			}
		}
	}

	private void addGroupMessage(String pMessage) {
		// TODO Auto-generated method stub
		
	}

	private void addMessage(String pMessage) {
		// TODO Auto-generated method stub
		
	}

	public void sendMessage(String text) {
		// TODO Auto-generated method stub
		send("Message:"+text);
		
	}

}
