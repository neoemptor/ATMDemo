package javabank;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 ** The SetFocusWindowAdapter Class Provides the set focus action for the Teller application.
 ** @Author: Darren Bailey
 ** @Version 1.0
 **/
class SetFocusWindowAdapter extends WindowAdapter
{
	// ---------------------Data----------------------------
	JTextField theFirstField;
	
	// -----------------Constructors------------------------
	/**
	 ** The preferred constructor
	 **
	 **/	
	SetFocusWindowAdapter(JTextField firstField)
	{
		theFirstField = firstField;
	}
	
	/**
	 ** The windowGainedFocus method sets the cursor at the first field.
	 **
	 **/
	public void windowGainedFocus(WindowEvent focusTransactionTextField)
	{
		theFirstField.requestFocusInWindow();
	}
} // End of SetFocusWindowAdapter class
