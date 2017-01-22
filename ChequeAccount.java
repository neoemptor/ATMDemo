package javabank;
/**
 * The ChequeAccount class handles the issuing of cheques. It is inherited from Account.
 * @author Darren Bailey
 * @version 1.0
 */
class ChequeAccount extends Account
{
	// ---------------------Data----------------------------
	private int nextChequeNumber;
	private int withdawalLimit = 1000;
	private int pendingAmount;
	 
	// -----------------Constructors------------------------
	/**
	 ** The preferred constructor
	 **
	 **/	
	ChequeAccount(String newCustomer, int amount)
	{
		super(newCustomer, amount, AccountType.CHEQUE);
	}
	ChequeAccount(int amount, String newCustomer) 
	{
		super(newCustomer, amount, AccountType.CHEQUE);
	}
	
	/**
	 ** The writeCheque method adds a new amount to the balance.
	 ** @param amount contains the new amount to add to the balance.
	 **
	 **/
	public void writeCheque(int amount)
	{
		// check customers can only take out $1000 at a time.
		if(amount <= withdawalLimit && amount <= getBalance())
		{
			nextChequeNumber++;
			pendingAmount = amount;
			System.out.println("\t" + super.getCustomer() + " has written a cheque for " + "$" + amount + ".00");
		}
		else
		{
			System.out.println("\t" + super.getCustomer() + " can not write a cheque for more than $1000.");
		}
	} // End of writeCheque method
	/**
	 ** The print method outputs a string to the console showing the type of account.
	 **
	 **/
	public void print()
	{
		super.print();
		System.out.println("\t\tType:\t\t" + super.getAccountType());
	}
} // End of ChequeAccount