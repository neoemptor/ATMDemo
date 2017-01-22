package javabank;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;


/**
 ** This is the main Application and demonstrates the Teller class.
 ** @Author: Darren Bailey
 ** @Version 1.0
 **/
class Teller extends JFrame
{
	// --------------------- Data ----------------------------
	private Account[] theAccounts;
	private int iAccountsLength = 0;
	private int iAccountIndex = 0;
	private ArrayList<Account> accounts = new ArrayList<Account>();

	private Account selectedAccount;
	
	static final long serialVersionUID = 1L;
	
		JPanel 	pnlDepositWithdrawPrint 	= new JPanel();
		JPanel 	pnlNewPrintAllClose 		= new JPanel();
		JPanel 	pnlAccountDetails 			= new JPanel();
		
		JButton 	btnDeposit 				= new JButton("Deposit");
		JButton 	btnWithdraw 			= new JButton("Withdraw");
		JButton 	btnPrintAccount 		= new JButton("Print Account...");
		JButton 	btnNewAccount 			= new JButton("New Account...");
		JButton 	btnPrintAllAccounts 	= new JButton("Print All Accounts...");	
		JButton 	btnCloseBank 			= new JButton("Close");
		JLabel 		lblTransaction 			= new JLabel("Transaction:");
		JTextField 	txtTransaction 			= new JTextField(10);
		JLabel 		lblAccountID 			= new JLabel("AccountID:");
		JLabel 		lblAccountIDContent 	= new JLabel("0000");
		JLabel 		lblCustomerName 		= new JLabel("Customer:");
		JLabel 		lblCustomerNameContent 	= new JLabel("None Chosen");
		JLabel 		lblBalance 				= new JLabel("Balance:");
		JLabel 		lblBalanceContent 		= new JLabel("0000");
		
		// current version of Java only works with generics.
		DefaultListModel<String>	lmdAccounts				= new DefaultListModel<String>();
		JList<String> 				lstCustomer 			= new JList<String>(lmdAccounts);
		JScrollPane 				lstScrollerCustomer 	= new JScrollPane(lstCustomer);
		
	// -----------------Constructors------------------------
	/**
	 ** The preferred constructor
	 **
	 **/	
	Teller(String titleName)
	{
		super(titleName);

		// Application close  settings.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btnCloseBank.addActionListener(new ApplicationClose()); // This uses the separate ApplicationClose class.
		btnNewAccount.addActionListener(new AccountCreator());
		lstCustomer.addListSelectionListener(new UpdateDetailView());
		
		createDepositWithdrawPrintPanel();

		createNewPrintAllClosePanel();

		createAccountsDetailsPanel();
		
		// add panels and controls to main application window.
		add(pnlDepositWithdrawPrint,	BorderLayout.PAGE_START);
		add(pnlAccountDetails,			BorderLayout.CENTER);
		add(pnlNewPrintAllClose,		BorderLayout.PAGE_END);

		// Set the minimum size for the main application window.
		setMinimumSize(new Dimension(550,370));
		setSize(550,370);
		
		// Centre the application.
		setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - getBounds().width / 2 ,
					Toolkit.getDefaultToolkit().getScreenSize().height / 2 - getBounds().height / 2 );
		
		setVisible(true);
	}	// Teller constructor END

	// -----------------------Methods-----------------------
	/**
	 ** This sets up the constraints and then adds the component to the main JFrame.
	 ** @param gridBag
	 ** @param gridComponent
	 ** @param gridPosX
	 ** @param gridPosY
	 ** @param gridWidth
	 ** @param gridHeight
	 ** @param gridWeightX
	 ** @param gridWeightY
	 ** @param gridFill
	 ** @param gridAnchor
	 ** @param gridPaddingX
	 ** @param gridPaddingY
	 **/	
	private void addComponent(	GridBagLayout gridBag, 	Component gridComponent, 	int gridPosX, 			int gridPosY,
						int gridWidth, 			int gridHeight, 			int gridWeightX,		int gridWeightY,
						int gridFill, 			int gridAnchor, 			int gridPaddingX, 		int gridPaddingY
					 ) 
	{
		GridBagConstraints constraint = new GridBagConstraints();
		
		constraint.gridx 					= gridPosX;
		constraint.gridy 					= gridPosY;
		constraint.gridwidth 				= gridWidth;
		constraint.gridheight 				= gridHeight;
		constraint.weightx 					= gridWeightX;
		constraint.weighty 					= gridWeightY;
		constraint.fill 					= gridFill;
		constraint.anchor 					= gridAnchor;
		constraint.ipadx 					= gridPaddingX;
		constraint.ipady 					= gridPaddingY;
		
		gridBag.setConstraints(gridComponent, constraint);
		add(gridComponent);
	} 	// addComponent END

	/**
	 ** The createDepositWithdrawPrintPanel method creates the deposit withdraw and print panel.
	 **
	 **/	
	void createDepositWithdrawPrintPanel()
	{
		pnlDepositWithdrawPrint.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		
		pnlDepositWithdrawPrint.add(btnDeposit);
		pnlDepositWithdrawPrint.add(btnWithdraw);
		pnlDepositWithdrawPrint.add(btnPrintAccount);
	} // createDepositWithdrawPrintPanel END

	/**
	 ** The createNewPrintAllClosePanel method creates the new print all and close panel.
	 **
	 **/	
	void createNewPrintAllClosePanel()
	{
		pnlNewPrintAllClose.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		pnlNewPrintAllClose.add(btnNewAccount);
		pnlNewPrintAllClose.add(btnPrintAllAccounts);
		pnlNewPrintAllClose.add(btnCloseBank);
	} // createNewPrintAllClosePanel END
	
	/**
	 ** The  createAccountsDetailsPanel method creates the accounts details panel.
	 **
	 **/	
	void createAccountsDetailsPanel()
	{
		GridBagLayout layAccountDetails = new GridBagLayout();
		pnlAccountDetails.setLayout(layAccountDetails);
		pnlAccountDetails.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// Initialise list.
		lstCustomer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstCustomer.setVisibleRowCount(-1);
		lstCustomer.setBorder(BorderFactory.createLineBorder(Color.BLUE));

			
		// Set borders for labels to stand out.
		lblAccountIDContent.setBorder(		BorderFactory.createLineBorder(Color.BLUE));				
		lblCustomerNameContent.setBorder(	BorderFactory.createLineBorder(Color.BLUE));
		lblBalanceContent.setBorder(		BorderFactory.createLineBorder(Color.BLUE));
			
		addComponent(	layAccountDetails, 				lstScrollerCustomer,					0,	0,	1,	4,	100,	100,
						GridBagConstraints.BOTH,		GridBagConstraints.FIRST_LINE_START,	10,	0);
		addComponent(	layAccountDetails, 				lblTransaction,							1,	0,	1,	1,	50,		100,
						GridBagConstraints.NONE,		GridBagConstraints.FIRST_LINE_END,		10,	0);
		addComponent(	layAccountDetails, 				txtTransaction,							2,	0,	1,	1,	50,		100,
						GridBagConstraints.HORIZONTAL,	GridBagConstraints.FIRST_LINE_START,	10,	0);			
		addComponent(	layAccountDetails, 				lblAccountID,							1,	1,	1,	1,	50,		100,
						GridBagConstraints.NONE,		GridBagConstraints.FIRST_LINE_END,		10,	0);	
		addComponent(	layAccountDetails, 				lblAccountIDContent,					2,	1,	1,	1,	50,		100,
						GridBagConstraints.HORIZONTAL,	GridBagConstraints.FIRST_LINE_START,	10,	0);
		addComponent(	layAccountDetails, 				lblCustomerName,						1,	2,	1,	1,	50,		100,
						GridBagConstraints.NONE,		GridBagConstraints.FIRST_LINE_END,		10,	0);
		addComponent(	layAccountDetails, 				lblCustomerNameContent,					2,	2,	1,	1,	50,		100,
						GridBagConstraints.HORIZONTAL,	GridBagConstraints.FIRST_LINE_START,	10,	0);			
		addComponent(	layAccountDetails, 				lblBalance,								1,	3,	1,	1,	50,		100,
						GridBagConstraints.NONE,		GridBagConstraints.FIRST_LINE_END,		10,	0);
		addComponent(	layAccountDetails, 				lblBalanceContent,						2,	3,	1,	1,	50,		100,
						GridBagConstraints.HORIZONTAL,	GridBagConstraints.FIRST_LINE_START,	10,	0);
		// Set constraints for account details gridbag layout. END
		
		// Set focus and justify appropriate components.
		txtTransaction.setHorizontalAlignment(		JTextField.RIGHT);
		lblAccountIDContent.setHorizontalAlignment(	JLabel.RIGHT);
		lblBalanceContent.setHorizontalAlignment(	JLabel.RIGHT);		
		
		// set the window focus to the transaction entry field.
		addWindowFocusListener(new SetFocusWindowAdapter(txtTransaction)); // addWindowFocusListener END
		
		// add components to account details panel.
		pnlAccountDetails.add(lstScrollerCustomer);
		pnlAccountDetails.add(lblTransaction);
		pnlAccountDetails.add(txtTransaction);
		pnlAccountDetails.add(lblAccountID);		
		pnlAccountDetails.add(lblAccountIDContent);
		pnlAccountDetails.add(lblCustomerName);
		pnlAccountDetails.add(lblCustomerNameContent);		
		pnlAccountDetails.add(lblBalance);		
		pnlAccountDetails.add(lblBalanceContent);
	}	// createAccountsDetailsPanel END
	
	// ----------------Main Application Method--------------
	public static void main(String[] noInput)
	{
		new Teller("Teller");
	} 	// main method END
	
	// ------------------- Inner Classes -------------------
	/**
	 ** The Depositer inner class listens for the deposit button press and deposits the transaction field amount into the current account.
	 **
	 **/	
	class Depositer implements ActionListener
	{
		public void actionPerformed(ActionEvent x)
		{
			
		}
	}

	/**
	 ** The Withdrawer inner class listens for the withdraw button press and withdraws the transaction field amount from the current account.
	 **
	 **/	
	class Withdrawer implements ActionListener
	{
		public void actionPerformed(ActionEvent x)
		{
			
		}
	}
	
	/**
	 ** The Printer` inner class listens for the print button press and prints the details for the current account.
	 **
	 **/	
	class Printer implements ActionListener
	{
		public void actionPerformed(ActionEvent x)
		{
			
		}
	}

	/**
	 ** The AccountCreator inner class listens for the deposit button press and deposits the transaction field amount into the current account.
	 **
	 **/	
	class AccountCreator implements ActionListener
	{
		public void actionPerformed(ActionEvent x)
		{
			NewAccountDialog newAccountDialog = new NewAccountDialog("Account Creator", true);
			selectedAccount = newAccountDialog.getNewAccount();
			accounts.add(selectedAccount);
			lmdAccounts.addElement(selectedAccount.toString());
		}
	}
	
	/**
	 ** The AllPrinter inner class listens for the All print  button press and prints all customers details.
	 **
	 **/	
	class AllPrinter implements ActionListener
	{
		public void actionPerformed(ActionEvent x)
		{
			
		}
	}

	/**
	 ** The UpdateDetailView inner class listens for the list to change and updates the current customer details to display.
	 **
	 **/	
	class UpdateDetailView implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent eCustomer)
		{
			if (eCustomer.getValueIsAdjusting() == false)
			{
				if (lstCustomer.getSelectedIndex() == -1) {

				} else
				{
					// Selection, display details.
					lblAccountIDContent.setText(accounts.get(lstCustomer.getSelectedIndex()).getAccountID().toString());
					lblCustomerNameContent.setText(accounts.get(lstCustomer.getSelectedIndex()).getCustomerName());
					lblBalanceContent.setText(accounts.get(lstCustomer.getSelectedIndex()).getBalance().toString());
				}
			}
		}		
	}
} 		// class Teller END
