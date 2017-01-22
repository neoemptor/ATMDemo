package javabank;
/**
 ** The StudentAccount class specifically handles Overdraft limits for students.
 ** @author Darren Bailey
 ** @version 1.0
 **/
class StudentAccount extends Account
{
	// ---------------------Data----------------------------
	private int overdraftLimit = 1000;
	private int overdraftWithdrawn = 0;
	private boolean canOverdraft = true;
	// -----------------Constructors------------------------
	/**
	 * The preferred constructor
	 *
	 */
	StudentAccount(String newStudent, int amount)
	{
		// Student name, amount of deposit, Type of account.
		super(newStudent, amount, AccountType.STUDENT);
	}
	
	StudentAccount(String newStudent, int amount, boolean wantOverdraft)
	{
		this(newStudent, amount);
		setCanOverdraft(wantOverdraft);
	}
	StudentAccount(int amount, String newStudent, boolean wantOverdraft)
	{
		this(newStudent, amount, wantOverdraft);
	}
	
	// --------------------------- Methods -------------------------------
	/**
	 ** The withdraw method take a set amount from the balance.
	 ** @param amount is the set amount to take from the balance.
	 **/
	public void withdraw(int amount)
	{
		super.withdraw(amount);
		if(!canOverdraft)
		{
			System.out.println("\tYou do not have an overdraft facility.");
		}
		else
		{
			System.out.println("\t\tbut you can have an overdraft of ");
		}
	}

	/**
	 ** The validateWithdrawal method checks to make sure the account doesn't go into overdraft.
	 ** @param amount is the amount to validate.
	 **/
	public boolean validateWithdrawal(int amount)
	{
		boolean result = false;
		if(overdraftLimit + getBalance() > amount)
		{
			result = true;
		}
		else
		{
			System.out.println("You can not withdraw any more from your overdraft.");
		}
		return result;
	} // End of validateWithdrawal

	/**
	 ** The setCanOverdraft method sets the overdraft flag to true or false.
	 ** @param wantsOverdraft holds the new true or false condition.
	 **/
	public void setCanOverdraft(boolean wantsOverdraft)
	{
		canOverdraft = wantsOverdraft;
	}
	
	/**
	 ** The print method outputs the account type and overdraft limit.
	 ** 
	 **/
	public void print()
	{
		super.print();
		System.out.println("\t\tType:\t\t" + super.getAccountType().toString());
		System.out.println("\t\tOverdraft:\t" + overdraftLimit);
	}
} // End of StudentAccount class
