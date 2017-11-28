package bank.hardware;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import bank.people.Person;

public class BankGUI extends JFrame{
	
	private Bank bank;
	private JPanel panel;
		
	// Login fields
	private JButton loginButton;
	private JButton registerButton;
	private JButton forgotUserButton;
	private JButton forgotPassButton;	
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JTextField usernameEntry;
	private JPasswordField passwordEntry;
	
	
	// Register fields
	private JLabel regNameLabel;
	private JLabel regPasswordLabel;
	private JLabel regUsernameLabel;
	private JTextField regName;
	private JTextField regPassword;
	private JTextField regUsername;
	private JRadioButton businessAccountButton;
	private JRadioButton personalAccountButton;
	private JButton regRegisterButton;
	
	public BankGUI(String windowName, Bank b1){
		super(windowName);
		
		this.bank = b1;
		
		setSize(500, 500);
		
		//setLayout(new FlowLayout());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		buildGUI();
		
		setVisible(true);
		
	}
	
	public void buildGUI() {		
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
		
		registerButton = new JButton("Open Account?");
		registerButton.addActionListener(new Listener());
		registerButton.setBounds(110, 80, 150, 25);
		panel.add(registerButton);
		
		this.add(panel);
		return;		
	}
	
	public void clearGUI() {
		panel.removeAll();		
		getContentPane().removeAll();
		panel.repaint();
		getContentPane().repaint();
		return;
	}
	
	public class Listener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			
			if(action.equals("Login")) {
				handleLogin();
			}
			else if(action.equals("Open Account?")) {
				handleRegister();
			}
			else if(action.equals("Forgot Username?")) {
				
			}
			else if(action.equals("Forgot Password?")) {
				
			}
			else if(action.equals("Register")) {
				String name = regName.getText();
				System.out.println(name);
				String username = regUsername.getText();
				String password = regPassword.getText();
				if(name.isEmpty()) {
					handleRegisterOptions(1);
				}
				else if(username.isEmpty()) {
					handleRegisterOptions(2);
				}
				else if(password.isEmpty()) {
					handleRegisterOptions(3);
				}
				else if(personalAccountButton.isSelected()) {
					System.out.println("Create personal account");
					bank.createPersonalAccount(name, username, password);
					handleRegisterOptions(5);
					clearGUI();
					buildGUI();
				}
				else if(businessAccountButton.isSelected()) {
					System.out.println("Create business account");
					bank.createBusinessAccount(name, username, password);
					handleRegisterOptions(5);
					clearGUI();
					buildGUI();
				}
				else {
					handleRegisterOptions(4);
				}
			}
		}
	}
	
	public void handleRegisterOptions(int id) {
		switch(id) {
			case 1:
				JOptionPane.showMessageDialog(this, "You must enter your name!");
				break;
			case 2:
				JOptionPane.showMessageDialog(this, "You must enter a username!");
				break;
			case 3:
				JOptionPane.showMessageDialog(this, "You must enter a password!");
				break;
			case 4:
				JOptionPane.showMessageDialog(this, "You must select one type of account!");
				break;
			case 5:				
				JOptionPane.showMessageDialog(this, "Registered! Please Login.");
				break;
		}		
	}
	

	public void handleLogin() {
		System.out.println(usernameEntry.getText());
		System.out.println(new String(passwordEntry.getPassword()));
		Person result = bank.validateUser(usernameEntry.getText(), new String(passwordEntry.getPassword()));
		
		if(result != null) {
			System.out.println("Login successful");
			
		}
		else {
			JOptionPane.showMessageDialog(this, "You are not a user, please register!");
		}
	}

	public void handleRegister() {
		panel.removeAll();		
		getContentPane().removeAll();		
		
		regNameLabel = new JLabel("First and Last Name: ");
		regNameLabel.setBounds(10, 10, 120, 25);
		panel.add(regNameLabel);
		
		regName = new JTextField(20);
		regName.setBounds(150, 10, 160, 25);
		panel.add(regName);
		
		regUsernameLabel = new JLabel("Enter a username: ");
		regUsernameLabel.setBounds(10, 40, 120, 25);
		panel.add(regUsernameLabel);

		regUsername = new JTextField(20);
		regUsername.setBounds(150, 40, 160, 25);
		panel.add(regUsername);
		
		regPasswordLabel = new JLabel("Enter a password: ");
		regPasswordLabel.setBounds(10, 70, 120, 25);
		panel.add(regPasswordLabel);
		
		regPassword = new JTextField(20);
		regPassword.setBounds(150, 70, 160, 25);
		panel.add(regPassword);
				
		businessAccountButton = new JRadioButton("Business Account");
		businessAccountButton.setBounds(10, 100, 150, 25);
		
		
		personalAccountButton = new JRadioButton("Personal Account");
		personalAccountButton.setBounds(10, 130, 150, 25);
		
		panel.add(businessAccountButton);
		panel.add(personalAccountButton);
		
		regRegisterButton = new JButton("Register");
		regRegisterButton.setBounds(10, 170, 120, 25);
		regRegisterButton.addActionListener(new Listener());
		panel.add(regRegisterButton);
		
		this.add(panel);
		getContentPane().repaint();
		return;
	}
}


