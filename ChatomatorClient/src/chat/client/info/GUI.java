package chat.client.info;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * Description
 *
 * @version 1.0 from 14/01/2018
 * @author
 */

public class GUI extends JFrame {
	// start attributes
	Chatclient c;				
	int ClientState=0;
	private JLabel logIn = new JLabel();
	private JTextField username = new JTextField();
	private JTextField password = new JTextField();
	private JTextField errorBar = new JTextField();
	Container cp;
	// end attributes

	public GUI(Chatclient pc) {
		// Frame-Init
		super();
		c=pc;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 400;
		int frameHeight = 600;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setTitle("Log-In");
		setResizable(false);
		cp = getContentPane();
		cp.setLayout(null);
		// start components
		cp.setBackground(new Color(0x404040));
		logIn.setBounds(144, 24, 128, 40);
		logIn.setText("Log-In");
		logIn.setFont(new Font("Dialog", Font.BOLD, 30));
		logIn.setForeground(Color.WHITE);
		cp.add(logIn);
		// nimmt den ersten focus, hat keinen anderen zweck
		JTextField a = new JTextField();
		cp.add(a);
		username.setBounds(50, 80, 300, 25);
		username.setText("Username");
		username.setFont(new Font("Dialog", Font.BOLD, 12));
		username.setForeground(Color.WHITE);
		username.setBackground(new Color(0x303030));
		username.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (username.getText().equals(""))
					username.setText("Username");

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				password.setForeground(Color.WHITE);
				if (username.getText().equals("Username"))
					username.setText("");

			}
		});
		cp.add(username);
		password.setBounds(50, 120, 300, 25);
		password.setText("Password");
		password.setFont(new Font("Dialog", Font.BOLD, 12));
		password.setForeground(Color.WHITE);
		password.setBackground(new Color(0x303030));
		password.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (password.getText().equals(""))
					password.setText("Password");

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				password.setForeground(Color.WHITE);
				if (password.getText().equals("Password"))
					password.setText("");

			}
		});
		password.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				c.send("login:"+username.getText()+":"+password.getText());
			}
		});
		cp.add(password);
		errorBar.setBounds(0, 536, 385, 25);
		errorBar.setBackground(new Color(0x1F2222));
		errorBar.setFont(new Font("Dialog", Font.BOLD, 12));
		errorBar.setForeground(new Color(0xFF2F2F));
		cp.add(errorBar);

		// end components

		setVisible(true);
	} // end of public GUI

	
	// start methods

	public void loginError() {
		password.setForeground(new Color(255,10,10));
		username.setForeground(new Color(255,10,10));
	}




	// end methods
} // end of class GUI
