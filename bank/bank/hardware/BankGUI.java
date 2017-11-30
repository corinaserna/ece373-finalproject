package bank.hardware;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.sun.xml.internal.ws.api.Component;

import bank.accounts.Account;
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
	private JButton regReturnButton;
	
	private JButton logoutButton;
	
	public BankGUI(String windowName, Bank b1){
		super(windowName);
		
		this.bank = b1;
		
		this.logoutButton = new JButton("Logout");
		this.logoutButton.addActionListener(new Listener());
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
			else if(action.equals("Logout") || action.equals("Nevermind")) {
				handleLogout();
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
			if(bank.typeAccount(result).equals("customer")) {
				Account account = bank.getAccount(result);
				handleCustomerLogin(result, account);
			}
			else if(bank.typeAccount(result).equals("admin")) {
				handleAdminLogin(result);
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "You are not a user, please register!");
		}
	}

	public void handleAdminLogin(Person result) {
		System.out.println("admin login");
		clearGUI();
		
		JLabel adminLabel = new JLabel("Bank Administrator - " + result.getName());
		adminLabel.setBounds(50, 10, 200, 25);
		panel.add(adminLabel);
		
		logoutButton.setBounds(300, 10, 100, 25);
		panel.add(logoutButton);
		
		
		JLabel bankInformation = new JLabel("Bank Total Balance ...................... $" + bank.getTotalMoney());
		bankInformation.setBounds(10, 50, 500, 25);
		bankInformation.setForeground(Color.blue);
		panel.add(bankInformation);
		
		String[] columnNames = { "Customer Name", "Checking", "Savings", "Credit Card Bill" };
		
		int totalAcctNum = bank.getPersonalAccounts().size() + bank.getBusinessAccounts().size();
		ArrayList<Account> allBankAccounts = bank.getAllAccounts();
		
		Object[][] data = new Object[totalAcctNum][4];
		
		for(int i = 0; i < totalAcctNum; i++) {
			for(int j = 0; j < 4; j++) {
				data[i][j] = new Object();
			}
		}
		
		for(int i = 0; i < totalAcctNum; i++) {
			System.out.println(allBankAccounts.get(i).getBelongsTo().getName());
			data[i][0] = allBankAccounts.get(i).getBelongsTo().getName();
			data[i][1] = allBankAccounts.get(i).getCheckingAcct().getAmount();
			data[i][2] = allBankAccounts.get(i).getSavingsAcct().getAmount();
			data[i][3] = allBankAccounts.get(i).getCreditCard().getCreditBill();			
		}		
		
		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		scrollPane.setBounds(10, 100, 400, (int) (table.getRowHeight() * (totalAcctNum + 1.45)));		
		panel.add(scrollPane);
		
		panel.repaint();
		this.add(panel);
		getContentPane().repaint();
		return;
	}

	public void handleCustomerLogin(Person person, Account account) {
		clearGUI();
		
		JLabel customerLabel = new JLabel(person.getName());
		customerLabel.setBounds(50, 10, 200, 25);
		panel.add(customerLabel);
		
		logoutButton.setBounds(300, 10, 100, 25);
		panel.add(logoutButton);
		
		
		
		panel.repaint();
		this.add(panel);
		getContentPane().repaint();
		return;
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
		
		regReturnButton = new JButton("Nevermind");
		regReturnButton.setBounds(150, 170, 120, 25);
		regReturnButton.addActionListener(new Listener());
		panel.add(regReturnButton);
		
		this.add(panel);
		getContentPane().repaint();
		return;
	}
	
	public void handleLogout(){
		clearGUI();
		buildGUI();
	}
}


