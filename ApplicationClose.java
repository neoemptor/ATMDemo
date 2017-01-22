package javabank;


import java.awt.event.*;

/**
 * The ApplicationClose class provides the close action for the Teller application. It implements ActionListener.
 * @Author: Darren Bailey
 * @Version 1.0
*/
class ApplicationClose implements ActionListener
{
	// ----------------------- Methods -----------------------
	/**
	 ** The actionPerformed method closes the application.
	 ** @param closeApplicationEvent does nothing in this method.
	 **/
	public void actionPerformed(ActionEvent closeApplicationEvent)
	{
			System.exit(0);    
	}
} // End of class ApplicationClose
