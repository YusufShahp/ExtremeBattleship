package com.example.springboot;

import java.util.Random;

public class AttackerAI
{
	AttackerAIContext context = null;
	boolean onlineMode = false;

	public AttackerAI(AttackerAIContext context, boolean onlineMode)
	{
		if (context == null)
		{
			this.context = new AttackerAIContext();
		}
		else
		{
			this.context = context;
		}

		this.onlineMode = onlineMode;
	}

	private boolean GetIsLockedIn()
	{
		return this.context.pointNumber > 0;
	}

	public boolean takeAttack(Grid primary, Grid secondary)
	{
		Random rand = new Random();

		int minY = 5;
		int maxY = 9;

		int maybeX;
		int maybeY;
		
		int temp;

		if (this.GetIsLockedIn())
		{
			temp = rand.nextInt(this.context.pointNumber);
			System.out.println(temp);

			maybeX = this.context.points.get(temp).X;
			maybeY = this.context.points.get(temp).Y;
		}
		else
		{
			maybeX = rand.nextInt(10);
			maybeY = rand.nextInt(5)+5;
		}
		
		if (maybeX < 0 || maybeX > 9 || maybeY< minY || maybeY > maxY)
		{
			System.out.println("First");
			this.context.wasAttackSuccessful = false;
			return this.context.wasAttackSuccessful;
		}
		// -------------------------------
		
		if (primary.board[maybeY][maybeX] == 8 || primary.board[maybeY][maybeX] == 9) //8 and 9 is the number for a miss
		{
			System.out.println("Second");
			this.context.wasAttackSuccessful = false;
			return this.context.wasAttackSuccessful;
		}
		
		putAttack(maybeX, maybeY, primary, secondary);
		this.context.wasAttackSuccessful = true;
		return this.context.wasAttackSuccessful;
	}

	int attackerGuessX;
	int attackerGuessY;
	
	// Note: Expect that X and Y inputs are confirmed to be valid
	private void putAttack(int guessX, int guessY, Grid primary, Grid secondary)
	{
		String name = "Player Two";
		boolean hit = false;
		int code = 0;
		boolean lockedInDone = false;
		if(primary.isPlayerOne)
		{	
			name = "Player One";
		}	

		attackerGuessX = guessX;
		attackerGuessY = guessY;

		
		System.out.println(name + " is guessing " + guessX + " left, and " + guessY + " down");
		System.out.println(name + " has " + this.context.totalPoints + " points left to hit out of 21");
		
		if(secondary.board[guessY][guessX] == 2)
		{
			this.context.oilship--;
			this.context.totalPoints--;
			hit = true;
			code = 12;
			if(this.context.oilship == 0)
			{
				System.out.println( name + " Has sunk an oilship");
				primary.shipGotSunk(1);
				lockedInDone = true;
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
				lockedInDone = true;
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
				lockedInDone = true;
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
				lockedInDone = true;
			}
		}	
		else if(secondary.board[guessY][guessX] ==6)
		{
			this.context.triangle--;
			this.context.totalPoints--;
			hit = true;
			code = 16;
			if(this.context.triangle == 0)
			{
				System.out.println(name + " Has sunk a command module (triangle)");
				primary.shipGotSunk(5);
				lockedInDone = true;
			}
		}	

		
		if (hit)
		{
			System.out.println(name +" got a hit!");
			primary.board[guessY][guessX] = 9;  //The value indicating that spot was a hit
			secondary.board[guessY][guessX] = code;

			Point point1 = new Point();
			point1.X = guessX + 1;
			point1.Y = guessY;
			this.context.points.put(this.context.pointNumber, point1);
			this.context.pointNumber++;

			Point point2 = new Point();
			point2.X = guessX - 1;
			point2.Y = guessY;
			this.context.points.put(this.context.pointNumber, point2);
			this.context.pointNumber++;

			Point point3 = new Point();
			point3.X = guessX;
			point3.Y = guessY + 1;
			this.context.points.put(this.context.pointNumber, point3);
			this.context.pointNumber++;

			Point point4 = new Point();
			point4.X = guessX;
			point4.Y = guessY - 1;
			this.context.points.put(this.context.pointNumber, point4);
			this.context.pointNumber++;
		}
		else
		{
			System.out.println(name + " missed their target");
			primary.board[guessY][guessX] = 8; //This shows that the spot was a miss
			secondary.board[guessY][guessX] = 18;
		} 

		if (lockedInDone)
		{
			this.context.points.clear();
			this.context.pointNumber = 0;

			int code1 =99;
			int code2 = 99;
			int code3 = 99;
			int code4 = 99;
			int code5 = 99;

			if(this.context.oilship == 0)
			{
				code1 = 12;
			}

			if(this.context.submarine == 0)
			{
				code2 = 13;
			}

			if(this.context.battleship == 0)
			{
				code3 = 14;
			}

			if(this.context.aircraft == 0)
			{
				code4 = 15;
			}

			if(this.context.triangle == 0)
			{
				code5 = 16;
			}

			for(int i = 0; i < primary.board.length ; i++)
			{
				for(int j = 0; j <primary.board[i].length; j++)
				{
					if(primary.board[i][j] == 9)
					{
						if(secondary.board[i][j] != code1 
							&& secondary.board[i][j] != code2 
							&& secondary.board[i][j] != code3 
							&& secondary.board[i][j] != code4 
							&& secondary.board[i][j] != code5)
						{
							System.out.println("Entered");

							Point point1 = new Point();
							point1.X = j + 1;
							point1.Y = i;
							this.context.points.put(this.context.pointNumber, point1);
							this.context.pointNumber++;

							Point point2 = new Point();
							point2.X = j - 1;
							point2.Y = i;
							this.context.points.put(this.context.pointNumber, point2);
							this.context.pointNumber++;

							Point point3 = new Point();
							point3.X = j;
							point3.Y = i + 1;
							this.context.points.put(this.context.pointNumber, point3);
							this.context.pointNumber++;

							Point point4 = new Point();
							point4.X = j;
							point4.Y = i - 1;
							this.context.points.put(this.context.pointNumber, point4);
							this.context.pointNumber++;
						}
					}
				}
			}		
		}
	}	
}	