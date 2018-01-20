package chat.client.info;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * Description
 *
 * @version 1.0 from 16/01/2018
 * @author
 */

public class ChatGui extends JFrame {
	// start attributes
	private JComboBox userComboBox = new JComboBox();
	private DefaultComboBoxModel userComboBoxModel = new DefaultComboBoxModel();
	private JTextArea jTextArea1 = new JTextArea("");
	private JScrollPane jTextArea1ScrollPane = new JScrollPane(jTextArea1);
	private JTextField searchUserField = new JTextField();
	private JTextField messageField = new JTextField();
	private JTextField errorBar = new JTextField();
	private JButton userButton = new JButton();
	private ImageIcon userButtonIcon = new ImageIcon("images/Face.png");
	private JButton searchButton = new JButton();
	private ImageIcon searchButtonIcon = new ImageIcon("images/lupe.png");
	private Chatclient client;
	private Chat currentChat;
	// end attributes

	public ChatGui(Chatclient pc) {
		// Frame-Init
		super();
		client=pc;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 400;
		int frameHeight = 600;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setTitle("ChatGui");
		setResizable(false);
		Container cp = getContentPane();
		cp.setLayout(null);
		// start components

		userComboBox.setModel(userComboBoxModel);
		userComboBox.setBounds(0, 0, 150, 35);
		userComboBox.setBackground(new Color(0x112233));
		userComboBox.setForeground(Color.WHITE);
		cp.add(userComboBox);
		cp.setBackground(new Color(0x404040));
		jTextArea1ScrollPane.setBounds(0, 35, 385, 465);
		jTextArea1.setBackground(new Color(0x1F2222));
		jTextArea1.setForeground(Color.WHITE);
		cp.add(jTextArea1ScrollPane);
		searchUserField.setBounds(150, 0, 165, 35);
		searchUserField.setBackground(new Color(0x112233));
		searchUserField.setFont(new Font("Dialog", Font.BOLD, 12));
		searchUserField.setForeground(Color.WHITE);
		cp.add(searchUserField);
		messageField.setBounds(0, 496, 385, 41);
		messageField.setBackground(new Color(0x112233));
		messageField.setFont(new Font("Dialog", Font.BOLD, 12));
		messageField.setForeground(Color.WHITE);
		messageField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				client.send(messageField.getText());
				
			}
		});
		cp.add(messageField);
		errorBar.setBounds(0, 536, 385, 25);
		errorBar.setBackground(new Color(0x1F2222));
		errorBar.setFont(new Font("Dialog", Font.BOLD, 12));
		errorBar.setForeground(new Color(0xFF2F2F));
		cp.add(errorBar);
		userButton.setBounds(350, 0, 35, 35);
		userButton.setText("");
		userButton.setMargin(new Insets(2, 2, 2, 2));
		userButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				userButton_ActionPerformed(evt);
			}
		});
		userButton.setBackground(new Color(0x112233));
		userButton.setFont(new Font("Webdings", Font.PLAIN, 25));
		userButton.setIcon(userButtonIcon);
		cp.add(userButton);
		searchButton.setBounds(315, 0, 35, 35);
		searchButton.setText("");
		searchButton.setMargin(new Insets(2, 2, 2, 2));
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				searchButton_ActionPerformed(evt);
			}
		});
		searchButton.setBackground(new Color(0x112233));
		searchButton.setIcon(searchButtonIcon);
		cp.add(searchButton);
		// end components

		setVisible(true);
	} // end of public ChatGui

	// start methods

	public void userButton_ActionPerformed(ActionEvent evt) {
		// TODO add your code here

	} // end of userButton_ActionPerformed

	public void searchButton_ActionPerformed(ActionEvent evt) {
		// TODO add your code here

	} // end of searchButton_ActionPerformed

	// end methods
} // end of class ChatGui
