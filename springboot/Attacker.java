package com.example.springboot;
import javax.swing.JOptionPane;

public class Attacker
{
	AttackerContext context = null;
	boolean onlineMode = false;
	
	public Attacker(AttackerContext context, boolean onlineMode)
	{
		if (context == null)
		{
			this.context = new AttackerContext();
		}
		else
		{
			this.context = context;
		}
		
		this.onlineMode = onlineMode;
	}

	public boolean takeAttack(Grid primary, 
							  Grid secondary,
							  int attackX,
							  int attackY)
	{
		int minY = 0;
		int maxY = 4;
		String message;
		String sideSelector;

		if(primary.isPlayerOne)
		{
			System.out.println("Player 1: you must guess in the top half");
			message = " (Top half)";
			sideSelector = "top";
		}
		else
		{
			System.out.println("Player 2: you must guess in the bottom half");
			minY = 5;
			maxY = 9;
			message = " (Bottom half)";
			sideSelector = "bottom";
		}
		
		int maybeX = attackX;
		int maybeY = attackY;

		if (!onlineMode)
		{
			// -------------------------------
			// InputPanel.GetGuessCoords(minY, maxY, ref guessX, ref guessY);
			// -------------------------------		
			InputGetter scan = new InputGetter();
			maybeX = scan.getX("What is the letter of your attack?" + message);			
			maybeY = scan.getY(sideSelector, "What is the number of your guess: " + message);
		}
		

		if (maybeX < 0 || maybeX > 9 || maybeY< minY || maybeY > maxY)
		{
			if (!onlineMode)
			{
				JOptionPane.showMessageDialog(null, "This guess is out of bounds", "Redo message", JOptionPane.ERROR_MESSAGE);
			}

			this.context.wasAttackSuccessful = false;
			return this.context.wasAttackSuccessful;
		}

		// -------------------------------
		
		if (primary.board[maybeY][maybeX] == 8 || primary.board[maybeY][maybeX] == 9) //8 and 9 is the number for a miss
		{
			if (!onlineMode)
			{	
				JOptionPane.showMessageDialog(null, "You have already guessed this place", "Redo message", JOptionPane.ERROR_MESSAGE);
			}

			this.context.wasAttackSuccessful = false;
			return this.context.wasAttackSuccessful;
		}
		
		putAttack(maybeX, maybeY, primary, secondary);
		this.context.wasAttackSuccessful = true;
		return this.context.wasAttackSuccessful;
	}
	
	// Note: Expect that X and Y inputs are confirmed to be valid
	private void putAttack(int guessX, int guessY, Grid primary, Grid secondary)
	{
		String name = "Player Two";
		boolean hit = false;
		int code = 0;
		if(primary.isPlayerOne)
		{	
			name = "Player One";
		}	
		
		System.out.println(name + " is guessing " + guessX + " left, and " + guessY + " down");
		System.out.println(name + " has " + this.context.totalPoints + " points left to hit out of 21");
		
		if(secondary.board[guessY][guessX] == 2)
		{
			this.context.oilship--;
			this.context.totalPoints--;
			hit = true;
			code = 12;
			if (this.context.oilship == 0)
			{
				System.out.println( name + " Has sunk an oilship");
				primary.shipGotSunk(1);
			}	
		}	
		else if(secondary.board[guessY][guessX] ==3)
		{
			this.context.submarine--;
			this.context.totalPoints--;
			hit = true;
			code = 13;
			if(this.context.submarine == 0)
			{
				System.out.println(name + " Has sunk a submarine");
				primary.shipGotSunk(2);
			}
		}	
		else if(secondary.board[guessY][guessX] ==4)
		{
			this.context.battleship--;
			this.context.totalPoints--;
			hit = true;
			code = 14;
			if(this.context.battleship == 0)
			{
				System.out.println(name + " Has sunk a battleship");
				primary.shipGotSunk(3);
			}
		}	
		else if(secondary.board[guessY][guessX] ==5)
		{
			this.context.aircraft--;
			this.context.totalPoints--;
			hit = true;
			code =15;
			if(this.context.aircraft == 0)
			{
				System.out.println(name + " Has sunk an aircraft carrier");
				primary.shipGotSunk(4);
			}
		}	
		else if (secondary.board[guessY][guessX] ==6)
		{
			this.context.triangle--;
			this.context.totalPoints--;
			hit = true;
			code = 16;
			if(this.context.triangle == 0)
			{
				System.out.println(name + " Has sunk a command module (triangle)");
				primary.shipGotSunk(5);
			}
		}	

		
		if (hit)
		{
			System.out.println(name +" got a hit!");
			primary.board[guessY][guessX] = 9;  //The value indicating that spot was a hit
			secondary.board[guessY][guessX] = code;
		}
		else
		{
			System.out.println(name + " missed their target");
			primary.board[guessY][guessX] = 8; //This shows that the spot was a miss
			secondary.board[guessY][guessX] = 18;
		} 
	}	
}