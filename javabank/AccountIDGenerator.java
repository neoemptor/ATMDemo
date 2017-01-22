package javabank;
/**
 * The AccountIDGenerator class handles the account id number generation(incrementation).
 * @author Darren Bailey
 * @version 1.0
 */
abstract class  AccountIDGenerator
{
	// ---------------------Data----------------------------
	private static int accountID = 1000;

	// ----------------------- Get and Setters -----------------------
	/** Gets the customer ID.
	 ** @return accountID returns an incremented account ID.
	 **/	
	public static int getNextAccountID()
	{
		accountID++;
		return accountID;
	}

} // End of AccountIDGenerator abstract class