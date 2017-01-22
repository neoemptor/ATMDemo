package javabank;
/**
 * The DepositAccount class handles depositing and withdrawal of money. It inherits from Account class.
 * @author Darren Bailey
 * @version 1.0
 */
 class DepositAccount extends Account
{
	// ---------------------Data----------------------------
	private int interestRate = 5;

	// -----------------Constructors------------------------
	/**
	 ** The preferred constructor
	 **
	 **/ 	
	DepositAccount(String customerName, int amount)
	{
		super(customerName, amount, AccountType.DEPOSIT);
	}
	
	DepositAccount(int amount, String customerName)
	{
		super(customerName, amount, AccountType.DEPOSIT);
	}
	
	public void deposit(int amount) 
	{
		
		// now add the interest
		amount += (amount * (interestRate / 100));
		super.deposit(amount);
	}
	/**
	 ** The print method outputs the account type to the console.
	 **
	 **/
	public void print()
	{
		super.print();
		System.out.println("\t\tType:\t\t" + super.getAccountType().toString());
	}
}
