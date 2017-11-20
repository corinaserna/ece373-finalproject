package bank.hardware;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class BankGUI extends JFrame{
	
	private Bank bank;
		
	private JButton loginButton;
	private JButton registerButton;
	private JButton forgotUserButton;
	private JButton forgotPassButton;
	
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JTextField usernameEntry;
	private JPasswordField passwordEntry;
	
	public BankGUI(String windowName, Bank b1){
		super(windowName);
		
		this.bank = b1;
		
		setSize(500, 500);
		
		//setLayout(new FlowLayout());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		this.add(panel);
		buildGUI(panel);
		
		setVisible(true);
		
	}
	
	public void buildGUI(JPanel panel) {	
		panel.setLayout(null);

		usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(10, 10, 80, 25);
		panel.add(usernameLabel);

		usernameEntry = new JTextField(20);
		usernameEntry.setBounds(100, 10, 160, 25);
		panel.add(usernameEntry);

		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		passwordEntry = new JPasswordField(20);
		passwordEntry.setBounds(100, 40, 160, 25);
		panel.add(passwordEntry);

		loginButton = new JButton("Login");
		loginButton.addActionListener(new Listener());
		loginButton.setBounds(10, 80, 80, 25);
		panel.add(loginButton);
		
		registerButton = new JButton("Register");
		registerButton.addActionListener(new Listener());
		registerButton.setBounds(175, 80, 85, 25);
		panel.add(registerButton);
		return;		
	}
	
	public class Listener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			
			if(action.equals("Login")) {
				handleLogin();
			}
			else if(action.equals("Register")) {
				handleRegister();
			}
			else if(action.equals("Forgot Username?")) {
				
			}
			else if(action.equals("Forgot Password?")) {
				
			}
		}
	}

	public void handleLogin() {
		System.out.println(usernameEntry.getText());
		System.out.println(new String(passwordEntry.getPassword()));
		boolean result = bank.validateUser(usernameEntry.getText(), new String(passwordEntry.getPassword()));
		
		if(result == true) {
			System.out.println("Login successful");
		}
		else {
			System.out.println("you are not a user, please register");
		}
	}

	public void handleRegister() {
		// TODO Auto-generated method stub
		
	}
}


