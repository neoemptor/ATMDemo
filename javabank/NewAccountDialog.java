package javabank;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.io.Serializable;
/**
 * The NewAccountDialog class handles creation of a new account via a dialog window.
 * @author Darren Bailey
 * @version 1.0
 */
class NewAccountDialog extends JDialog
{
	
	// ---------------------Data----------------------------	
	private AccountType accountChosen = AccountType.DEPOSIT;
	static final long serialVersionUID = 1L;
	private JButton btnCreate = new JButton("Create Account");
	private JButton btnCancel = new JButton("Cancel");
	private JTextField txtAccountName = new JTextField();
	private JTextField txtAccountBalance = new JTextField();
	private ButtonGroup bgrpAccountType = new ButtonGroup();
	private JRadioButton rbStudent = new JRadioButton("Student");
	private JRadioButton rbDeposit = new JRadioButton("Deposit",true);
	private JRadioButton rbCheque = new JRadioButton("Cheque");
	private String sStudent = new String("Student");
	private String sDeposit = new String("Deposit");
	private String sCheque = new String("Cheque");
	
	
	private JLabel lblAccountName = new JLabel("Account Name:");
	private JLabel lblAccountBalance = new JLabel("Initial Balance:");
	private JLabel lblAccountType = new JLabel("Account Type:");
	private GridBagLayout layAccountCreator = new GridBagLayout();
	private JPanel rgrpAccountType = new JPanel(new GridLayout(0, 1));
	
	private Account newAccount;

	// -----------------Constructors------------------------
	/**
	 ** The preferred constructor
	 **
	 **/	
	NewAccountDialog(String theTitle, boolean isModal)
	{
		super();
		setTitle(theTitle);
		setModal(isModal);

		// Set the minimum size for the main application window.
		setMinimumSize(new Dimension(300,170));
		setSize(300,170);
		
		// Centre the application.
		setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - getBounds().width / 2 ,
					Toolkit.getDefaultToolkit().getScreenSize().height / 2 - getBounds().height / 2 );
		

		btnCancel.addActionListener(new CancelThisDialog());
		btnCreate.addActionListener(new CreateAccount());
		setLayout(layAccountCreator);
		
		rbStudent.setActionCommand(sStudent);
		rbStudent.setMnemonic(KeyEvent.VK_S);
		rbDeposit.setActionCommand(sDeposit);
		rbDeposit.setSelected(true);
		rbDeposit.setMnemonic(KeyEvent.VK_D);

		rbCheque.setActionCommand(sCheque);
		rbCheque.setMnemonic(KeyEvent.VK_C);
	
		// Add radio buttons to a button group
		bgrpAccountType.add(rbStudent);
		bgrpAccountType.add(rbDeposit);
		bgrpAccountType.add(rbCheque);

		rbStudent.addActionListener(new StudentChosen());
		rbDeposit.addActionListener(new DepositChosen());
		rbCheque.addActionListener(new ChequeChosen());
		
		// Add radio buttons to a panel
		rgrpAccountType.add(rbStudent);
		rgrpAccountType.add(rbDeposit);
		rgrpAccountType.add(rbCheque);

		
		addComponent(layAccountCreator,lblAccountName, 	0,0,1,1,100,100,GridBagConstraints.BOTH,GridBagConstraints.FIRST_LINE_START,10,0);
		addComponent(layAccountCreator,txtAccountName, 	1,0,1,1,100,100,GridBagConstraints.HORIZONTAL,GridBagConstraints.FIRST_LINE_END,10,0);
		
		addComponent(layAccountCreator,lblAccountBalance,0,1,1,1,100,100,GridBagConstraints.BOTH,GridBagConstraints.FIRST_LINE_START,10,0);
		addComponent(layAccountCreator,txtAccountBalance,1,1,1,1,100,100,GridBagConstraints.HORIZONTAL,GridBagConstraints.FIRST_LINE_END,10,0);		
		addComponent(layAccountCreator,lblAccountType, 	0,2,1,1,100,100,GridBagConstraints.BOTH,GridBagConstraints.LAST_LINE_START,10,0);
		addComponent(layAccountCreator,rgrpAccountType, 	1,2,1,1,100,100,GridBagConstraints.BOTH,GridBagConstraints.LAST_LINE_END,10,0);
		addComponent(layAccountCreator,btnCreate, 		0,3,1,1,100,100,GridBagConstraints.HORIZONTAL,GridBagConstraints.PAGE_END,10,0);
		addComponent(layAccountCreator,btnCancel, 		1,3,1,1,100,100,GridBagConstraints.HORIZONTAL,GridBagConstraints.PAGE_END,10,0);
		setVisible(true);	//only make visible when a new account is required.
		
} // End of NewAccountDialog constructor

	/**
	 ** The addComponent method initialises the variables for the gridbag layout.
	 **
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
	} 	// End of addComponent method
	
	/**
	 ** The getNewAccount method provides a return path for the currently created account to the main application. 
	 ** @return newAccount returns the current account which was created.
	 **/
	Account getNewAccount()
	{
		return newAccount;
	}
// ----------------------- Inner Classes ----------------------
	/**
	 ** The CancelThisDialog inner class listens for the cancel button press and hides the dialog window.
	 **
	 **/
	class CancelThisDialog implements ActionListener
	{
		public void actionPerformed(ActionEvent notUsed)
		{
			setVisible(false);
		}
	}
	/**
	 ** The CreateAccount inner class listens for the create button press and creates the new account.
	 **
	 **/
	class CreateAccount implements ActionListener
	{
		public void actionPerformed(ActionEvent notUsed)
		{
			switch(accountChosen)
			{
				case DEPOSIT:
					newAccount = new DepositAccount(txtAccountName.getText(),Integer.parseInt(txtAccountBalance.getText()));

					break;
				case STUDENT:
					newAccount = new StudentAccount(txtAccountName.getText(),Integer.parseInt(txtAccountBalance.getText()));
					break;
				default:
					newAccount = new ChequeAccount(txtAccountName.getText(),Integer.parseInt(txtAccountBalance.getText()));
			}
			setVisible(false);
		} // End of actionPerformed method
	
	} // End of CreateAccount inner class
	/**
	 ** The DepositChosen inner class listens for the type Deposit type being chosen.
	 **
	 **/
	class DepositChosen implements ActionListener
	{
		public void actionPerformed(ActionEvent depositChosen)
		{
			accountChosen = AccountType.DEPOSIT;
		}
	}

	/**
	 ** The StudentChosen inner class listens for the type student type being chosen.
	 **
	 **/
	class StudentChosen implements ActionListener
	{
		public void actionPerformed(ActionEvent studentChosen)
		{
			accountChosen = AccountType.STUDENT;
		}
	}
	
	/**
	 ** The ChequeChosen inner class listens for the type cheque type being chosen.
	 **
	 **/
	class ChequeChosen implements ActionListener
	{
		public void actionPerformed(ActionEvent chequeChosen)
		{
			accountChosen = AccountType.CHEQUE;
		}
	}

} // End of NewAccountDialog class