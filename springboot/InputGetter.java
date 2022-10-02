package com.example.springboot;
import javax.swing.JOptionPane;

public class InputGetter
{
	String[] topOptions = {"1","2","3","4","5"};
	String[] bottomOptions = {"6", "7", "8", "9", "10"};
	int val; 
	boolean works;
	boolean tryTwo;

	/// <summary>
	/// This is an intellisense code documentation.
	/// </summary>
	public int getY(String side, String prompt)
	{
		val = 0;
		works = false;
		tryTwo = false;
        while(!works)
		{
			works = panelY(side, prompt);
			tryTwo = true;
		}
		System.out.println(val);
		return val;
	}	
	
	public boolean panelY (String side, String prompt)
	{
		String in;
		if(!tryTwo)
		{
			if(side.equals("top"))
			{
				in = (String)JOptionPane.showInputDialog(null, prompt, "BATTLESHIP: Pick a number", JOptionPane.QUESTION_MESSAGE, null, topOptions, null);
			}
			else
			{
				in = (String)JOptionPane.showInputDialog(null, prompt, "BATTLESHIP: Pick a number", JOptionPane.QUESTION_MESSAGE, null, bottomOptions, null);
			}
		}
		else
		{
			if(side.equals("top"))
			{
				in = (String)JOptionPane.showInputDialog(null, "TRY AGAIN: " + prompt, "BATTLESHIP: You must pick a number", JOptionPane.ERROR_MESSAGE, null, topOptions, null);
			}
			else
			{
				in = (String)JOptionPane.showInputDialog(null, "TRY AGAIN: " + prompt, "BATTLESHIP: You must pick a number", JOptionPane.ERROR_MESSAGE, null, bottomOptions, null);
			}
		}	
		
		if(in == null)
		{
			return false;
		}
		else
		{
			val = Integer.parseInt(in);
			val = val -1;
			return true;
		}	
	}
	
	int val1;
	String[] options2 = {"a","b","c","d","e","f","g","h","i","j"};
	public int getX(String prompt)
	{
		val1 = 0;
		works = false;
		tryTwo = false;
        while(!works)
		{
			works = panelX(prompt);
			tryTwo = true;
		}
		return val1;
	}
	
	char x;
	public boolean panelX (String prompt)
	{
		String in;
		if(!tryTwo)
		{
			in = (String)JOptionPane.showInputDialog(null, prompt, "BATTLESHIP: Pick a letter", JOptionPane.QUESTION_MESSAGE, null, options2, null);
		}
		else
		{
			in = (String)JOptionPane.showInputDialog(null, "TRY AGAIN: " + prompt, "BATTLESHIP: You must pick a letter", JOptionPane.ERROR_MESSAGE, null, options2, null);
		}	
		
		if(in == null)
		{
			return false;
		}
		else
		{
			x = in.charAt(0);
			val1 = x - 'a' + 1;
			val1 = val1 -1;
			System.out.println(val1);
			return true;
		}
	}	
	
	int val2;
	String[] options3 = {"Horizontal", "Vertical"};
	public int getDirection (String prompt)
	{
		val2 = 0;
		works = false;
		tryTwo = false;
        while(!works)
		{
			works = panelDirection(prompt);
			tryTwo = true;
		}
		return val2;
	}	
	
	public boolean panelDirection (String prompt)
	{
		String in;
		if(!tryTwo)
		{
			in = (String)JOptionPane.showInputDialog(null, prompt, "BATTLESHIP: Pick a direction", JOptionPane.QUESTION_MESSAGE, null, options3, null);
		}
		else
		{
			in = (String)JOptionPane.showInputDialog(null, "TRY AGAIN: " + prompt, "BATTLESHIP: You must pick a direction", JOptionPane.ERROR_MESSAGE, null, options3, null);
		}	
		
		if(in == null)
		{
			return false;
		}
		else
		{
			if(in.equals("Horizontal"))
			{
				val2 = 0;
			}
			else
			{
				val2 = 90;
			}	
			System.out.println(val2);
			return true;
		}
	}	
}	