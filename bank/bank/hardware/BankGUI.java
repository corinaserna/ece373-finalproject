package bank.hardware;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import bank.accounts.Account;
import bank.people.Administrator;
import bank.people.Person;

public class BankGUI extends JFrame{
	
	private Bank bank;
	private JPanel panel;
	private JButton logoutButton;
	private Person loggedInUser;
	private Account loggedInAccount;
		
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
	
	public BankGUI(String windowName, Bank b1){
		super(windowName);
		
		this.bank = b1;
		this.loggedInAccount = null;
		this.loggedInUser = null;
		
		this.logoutButton = new JButton("Logout");
		this.logoutButton.addActionListener(new Listener());
		setSize(700, 500);
		
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
			else if(action.equals("Withdraw")) {
				handleWithdraw();
			}
			else if(action.equals("Deposit")) {
				handleDeposit();
			}
			else if(action.equals("Pay Credit Card")){
				handlePay();
			}
			else if(action.equals("Transfer Money")) {
				handleTransfer();
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
		loggedInUser = result;
		if(result != null) {
			if(bank.typeAccount(result).equals("customer")) {
				loggedInAccount = bank.getAccount(result);				
				handleCustomerLogin();
			}
			else if(bank.typeAccount(result).equals("admin")) {
				handleAdminLogin();
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "You are not a user, please register!");
		}
	}

	public void handleAdminLogin() {
		System.out.println("admin login");
		clearGUI();
		Administrator user = (Administrator) loggedInUser;
		
		JLabel adminLabel = new JLabel("Bank "+ user.getTitle() + " - " + loggedInUser.getName());
		adminLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		adminLabel.setBounds(50, 10, 400, 40);
		panel.add(adminLabel);
		
		logoutButton.setBounds(550, 10, 100, 25);
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
			data[i][0] = allBankAccounts.get(i).getBelongsTo().getName();
			data[i][1] = allBankAccounts.get(i).getCheckingAcct().getAmount();
			data[i][2] = allBankAccounts.get(i).getSavingsAcct().getTotalAmount();
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

	public void handleCustomerLogin() {
		System.out.println(loggedInAccount.getSavingsAcct().getTotalAmount());
		clearGUI();
		
		JLabel customerLabel = new JLabel(loggedInUser.getName());
		customerLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		customerLabel.setBounds(250, 10, 700, 25);
		panel.add(customerLabel);
		
		JButton menuWithdraw = new JButton("Withdraw");
		menuWithdraw.addActionListener(new Listener());
		menuWithdraw.setBounds(10, 50, 150, 25);	
		panel.add(menuWithdraw);
		
		JButton menuDeposit = new JButton("Deposit");
		menuDeposit.addActionListener(new Listener());
		menuDeposit.setBounds(170, 50, 150, 25);
		panel.add(menuDeposit);
		
		JButton menuPay = new JButton("Pay Credit Card");
		menuPay.addActionListener(new Listener());
		menuPay.setBounds(330, 50, 150, 25);
		panel.add(menuPay);
		
		JButton menuTransfer = new JButton("Transfer Money");
		menuTransfer.addActionListener(new Listener());
		menuTransfer.setBounds(490, 50, 150, 25);
		panel.add(menuTransfer);
		
		JLabel checkingAccountLabel = new JLabel("<HTML><U>Checking Account - " + loggedInAccount.getCheckingAcct().getAccountID() + "</HTML></U>");
		checkingAccountLabel.setBounds(10, 100, 400, 30);
		checkingAccountLabel.setForeground(Color.blue);
		panel.add(checkingAccountLabel);
		
		JLabel checkingInfoLabel = new JLabel("<HTML>Current ..................................... $" + 
				loggedInAccount.getCheckingAcct().getAmount() + "<br> Available ..................................... $" +
				loggedInAccount.getCheckingAcct().getAmount() + "</HTML>");
		checkingInfoLabel.setBounds(10, 130, 400, 30);
		panel.add(checkingInfoLabel);
		
		JLabel savingsAccountLabel = new JLabel("<HTML><U>Savings Account - " + loggedInAccount.getSavingsAcct().getAccountID() + "</HTML></U>");
		savingsAccountLabel.setBounds(10, 170, 200, 25);
		savingsAccountLabel.setForeground(Color.blue);
		panel.add(savingsAccountLabel);
		
		JLabel savingsInfoLabel = new JLabel("<HTML>Current ..................................... $" + 
				loggedInAccount.getSavingsAcct().getAvailableAmount() + "<br> Available ..................................... $" +
				loggedInAccount.getSavingsAcct().getTotalAmount() + "</HTML>");
		savingsInfoLabel.setBounds(10, 200, 400, 30);
		panel.add(savingsInfoLabel);
		
		JLabel creditCardLabel = new JLabel("<HTML><U>Credit Card - " + loggedInAccount.getCreditCard().getCardNumber() + "</HTML></U>");
		creditCardLabel.setBounds(10, 240, 400, 25);
		creditCardLabel.setForeground(Color.blue);
		panel.add(creditCardLabel);
		
		JLabel creditInfoLabel = new JLabel("<HTML>Balance ..................................... $" + 
				loggedInAccount.getCreditCard().getCreditBill() + "<br> Available ..................................... $" +
				loggedInAccount.getCreditCard().getAvailableCredit() + "</HTML>");
		creditInfoLabel.setBounds(10, 270, 400, 30);
		panel.add(creditInfoLabel);
		
		logoutButton.setBounds(250, 350, 100, 25);
		panel.add(logoutButton);
		
		panel.repaint();
		this.add(panel);
		getContentPane().repaint();
		return;
	}
	
	public void handleWithdraw() {
		boolean success = false;
		String[] items = {"Savings", "Checking"};
        JComboBox<String> combo = new JComboBox<>(items);
        JTextField inputAmount = new JTextField(100);
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("From: "));
        panel.add(combo);
        panel.add(new JLabel("Amount: "));
        panel.add(inputAmount);
        int result = JOptionPane.showConfirmDialog(null, panel, "Withdraw",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
        	double amount = Double.parseDouble(inputAmount.getText());
        	if(combo.getSelectedItem().equals("Savings")) {
        		success = loggedInAccount.withdrawSavings(amount);
        		if(!success) {
        			JOptionPane.showMessageDialog(this,"You do not have enough money in savings account.", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
            	}
        	}
        	else if(combo.getSelectedItem().equals("Checking")) {
        		success = loggedInAccount.withdrawCheckings(amount);
        		if(!success) {
        			JOptionPane.showMessageDialog(this,"You do not have enough money in checkings account", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
            	}
        	}
        } 
        clearGUI();
        handleCustomerLogin();
        return;
	}
	
	public void handleDeposit() {
		boolean success = false;
		String[] items = {"Savings", "Checking"};
        JComboBox<String> combo = new JComboBox<>(items);
        JTextField inputAmount = new JTextField(100);
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("To: "));
        panel.add(combo);
        panel.add(new JLabel("Amount: "));
        panel.add(inputAmount);
        int result = JOptionPane.showConfirmDialog(null, panel, "Deposit",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
        	double amount = Double.parseDouble(inputAmount.getText());
        	
        	if(combo.getSelectedItem().equals("Savings")) {
        		success = loggedInAccount.depositSavings(amount);
        	}
        	else if(combo.getSelectedItem().equals("Checking")) {
        		success = loggedInAccount.depositCheckings(amount);
        	}
        	if(!success) {
    			JOptionPane.showMessageDialog(this,"You do not have enough money on hand.", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
        	}
        }
        clearGUI();
        handleCustomerLogin();
        return;
	}

	public void handlePay() {
		boolean success = false;
		String[] items = {"Savings", "Checking", "Money on Hand"};
        JComboBox<String> combo = new JComboBox<>(items);
        JTextField inputAmount = new JTextField(100);
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("From: "));
        panel.add(combo);
        panel.add(new JLabel("Amount: "));
        panel.add(inputAmount);
        int result = JOptionPane.showConfirmDialog(null, panel, "Pay Credit Card",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
        	double amount = Double.parseDouble(inputAmount.getText());
        	if(combo.getSelectedItem().equals("Savings")) {
        		success = loggedInAccount.payCreditFromSavings(amount);
        		if(!success) {
        			JOptionPane.showMessageDialog(this,"You do not have enough money in savings account for payment.", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
            	}
        	}
        	else if(combo.getSelectedItem().equals("Checking")) {
        		success = loggedInAccount.payCreditFromChecking(amount);
        		if(!success) {
        			JOptionPane.showMessageDialog(this,"You do not have enough money in checking account for payment.", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
            	}
        	}
        	else if(combo.getSelectedItem().equals("Money on Hand")) {
        		success = loggedInAccount.payCreditFromMoney(amount);
        		if(!success) {
        			JOptionPane.showMessageDialog(this,"You do not have enough money on hand for payment.", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
            	}
        	}
        }
        clearGUI();
        handleCustomerLogin();
        return;
	}
	
	public void handleTransfer() {
		boolean success = false;
		String[] items = {"Savings to Checking", "Checking to Savings"};
        JComboBox<String> combo = new JComboBox<>(items);
        JTextField inputAmount = new JTextField(100);
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("From: "));
        panel.add(combo);
        panel.add(new JLabel("Amount: "));
        panel.add(inputAmount);
        int result = JOptionPane.showConfirmDialog(null, panel, "Transfer",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
        	double amount = Double.parseDouble(inputAmount.getText());
        	if(combo.getSelectedItem().equals("Savings to Checking")) {
        		success = loggedInAccount.transferFromSavings(amount);
        		if(!success) {
        			JOptionPane.showMessageDialog(this,"There is not enough money in savings to transfer to checkings.", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
            	}
        	}
        	else if(combo.getSelectedItem().equals("Checking to Savings")) {
        		success = loggedInAccount.transferFromChecking(amount);
        		if(!success) {
        			JOptionPane.showMessageDialog(this,"There is not enough money in checking to transfer to savings.", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
            	}
        	}
        }
        clearGUI();
        handleCustomerLogin();
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
		this.loggedInUser = null;
		this.loggedInAccount = null;
		clearGUI();
		buildGUI();
	}
}


