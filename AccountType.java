package javabank;
/**
 ** The AccountType enumerator gives a list of labelled constants to identify the account type (I just 
 ** placed this in to get the hang of using enums).
 ** @author Darren Bailey
 ** @version 1.0
 **/
public enum AccountType
{
	// ---------------------Data----------------------------
	GENERAL,
	DEPOSIT,
	STUDENT,
	CHEQUE;

	// -----------------------Methods-----------------------
	/**
	 ** toString overrides the super toString method from Object class.
	 ** @return theType returns a formatted label of the type.
	 **/
	@Override public String toString() 
	{
		// Only capitalize the first letter
		String theType = super.toString();
		return theType.substring(0, 1) + theType.substring(1).toLowerCase();
	}	
} // End of AccountType enum