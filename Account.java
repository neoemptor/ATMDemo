package javabank;
/**
 * The Account class handles the id number, balance and customer and account type.
 * @author Darren Bailey
 * @version 1.0
 */
public abstract class Account
{
	// ---------------------Data----------------------------
	private AccountType accountType = AccountType.GENERAL; 	// The account type.
	private Integer	number = new Integer(0);	// The account number is unique.
	private Integer	balance = new Integer(0);	// Negative balances are not allowed.
	private String	customer = null;

	// -----------------Constructors------------------------
	/**
	 * The preferred constructor
	 *
	 */
	Account(String customerName,Integer newBalance)
	{
		number = AccountIDGenerator.getNextAccountID();
		customer = customerName;
		balance = newBalance;
		accountType = AccountType.GENERAL;
		System.out.println("\t" + "ID:" + number + " : " + customer + " has opened a new account and deposited $" + balance + ".00.");
	} // End of preferred Account constructor

	Account(Integer newBalance, String customerName)
	{
		this(customerName, newBalance);
	}
	
	Account(String customerName,Integer newBalance, AccountType newAccountType)
	{
		this(customerName, newBalance);
		setAccountType(newAccountType);
	}
	Account(Integer newBalance, String customerName, AccountType newAccountType)
	{
		this(customerName, newBalance, newAccountType);
	}
	Account(AccountType newAccountType, String customerName, Integer newBalance)
	{
		this(customerName, newBalance, newAccountType);
	}
	
	// -----------------Getters & Setters-------------------
	/** Sets the customer name.
	 ** @param newCustomer Sets a new customer.
	 **/
	void setCustomer(String newCustomer)
	{
		System.out.println("\t" + customer + " has changed their name to " + newCustomer);
		customer = newCustomer;
	}
	
	/** Change the account number.
	 ** The new account number must be a 5 digit number.
	 ** @param newNumber Sets a new account number.
	 **/
	void setNumber(Integer newNumber)
	{
		if(newNumber > 9999 && newNumber < 100000) 
		{
			System.out.println("\t" + customer + "'s account number has been set to " + newNumber);
			number = newNumber;
		}
		else 
		{
			System.out.println("\tThe account number " + newNumber + " for " + customer + " is out of range.");
		}
	} // End of setNumber method
	
	/** Retrieve the account number.
	 ** @return number Account number
	 **/
	public Integer getNumber()
	{
		System.out.println("\tRetrieving account number for " + customer + ".");
		return number;
	}

	/** This is an alternative to getNumber method (Retrieve the account number).
	 ** @return getNumber() Account number
	 ** @see getNumber
	 **/
	public Integer getAccountID()
	{
		return getNumber();
	}
	
	/** Retrieve the balance of the account.
	 * @return balance The current balance.
	 */
	public Integer getBalance()
	{
		System.out.println("\tRetrieving account balance for " + customer + ".");
		return balance;
	}
	
	/** Retrieve the customer name.
	 ** @return customer customer name.
	 **/
	public String getCustomer()
	{
		System.out.println("\tRetrieving customer name.");
		return customer;
	}

	/** Retrieve the customer name.
	 ** @return getCustomer() customer name.
	 ** @see getCustomer
	 **/
	public String getCustomerName()
	{
		return getCustomer();
	}
	
	
	// -----------------Methods-------------------
	/** The print method handles output to the printer.
	 */
	public void print()
	{
		System.out.println("\tAccount:");
		System.out.println("\t\tnumber:\t\t" + number);
		System.out.println("\t\tcustomer:\t" + customer);
		System.out.println("\t\tbalance:\t$" + balance + ".00");
	}
	
	/** The deposit method handles input to the the account.
	 ** @param amount Sets a new deposit amount.
	 **/
	public void deposit(Integer amount)
	{
		System.out.println("\t" + customer + " has an opening balance of $" + balance + ".00\n\tand has deposited $" + amount + ".00  into their account.");
		balance += amount;
		System.out.println("\tThe closing balance is $" + balance + ".00.");
	}
	
	/** The withdraw method handles output from the account.
	 ** @param amount Sets a new withdrawal amount.
	 **/
	public void withdraw(Integer amount)
	{
		if(validateWithdrawal(amount)) 
		{
			balance -= amount;
		}
		else
		{
			if(balance < amount) 
			{
				System.out.println("\tYou do not have enough funds in your account");
			}
			else
			{
				System.out.println("\t" + customer + " has an opening balance of $" + balance + ".00\n\tand has withdrawn $" + amount + ".00  out of their account.");
				balance -= amount;
				System.out.println("\tThe closing balance is $" + balance + ".00.");
			}
		}
	} // End of withdraw method
	
	public boolean validateWithdrawal(Integer amount)
	{
		boolean result = false;
		if(balance > amount)
			result = true;
		return result;
	}
	/**
	 ** The toString method here overrides the super toString method for Object class.
	 ** @return String Returns a formatted string.
	 **/
	@Override
	public String toString()
	{
		// e.g.   123456: Fred Flintstone (DepositAccount)
		String meInTextFormat = number + ": " + customer + " (" + accountType.toString() + ")";
		return meInTextFormat;
	}
	
	// ------------------------- Getters & Setters ----------------------------
	/**
	 ** The getAccountType method returns the account type.
	 ** @return AccountType
	 **/
	public AccountType getAccountType()
	{
		return accountType;
	}
	
	/**
	 ** The setAccountType method sets the account type.
	 ** @param newAccountType holds the new account type.
	 **/
	public void setAccountType(AccountType newAccountType)
	{
		accountType = newAccountType;
		System.out.println("\t" + customer + "'s account type has been set to " + accountType.toString());
	}
} // End of Account class
