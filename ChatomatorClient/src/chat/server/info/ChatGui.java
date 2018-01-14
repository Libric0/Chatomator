package chat.server.info;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatGui extends JFrame{
	Chatclient c;
	JTextArea chat=new JTextArea();
	JScrollPane csp= new JScrollPane(chat);
	JTextField t=new JTextField();
	public ChatGui(Chatclient pc) {
		super();
		c=pc;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		int frameWidth = 400;
		int frameHeight = 600;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setTitle("Chatomator");
		setResizable(false);
		Container cp= getContentPane();
		cp.setLayout(null);
		chat.setLineWrap(true);
		chat.setWrapStyleWord(false);
		chat.setEditable(false);
		csp.setBounds(5,5,375,490);
		cp.add(csp);
		t.setBounds(5,495,375,50);
		t.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				c.sendMessage(t.getText());
				t.setText("");
				
			}
		});
		cp.add(t);
		setVisible(true);
		
	}
	public void addMessage(String substring) {
		chat.setText(chat.getText()+substring+"\n");
		
	}
}
