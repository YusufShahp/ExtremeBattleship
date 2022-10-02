package com.example.springboot;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.Random;
public abstract class Ship
{
	Scanner myObj = new Scanner(System.in); 
	Random rand = new Random();
	InputGetter scan = new InputGetter();
	
	protected Point[] points;
	
	public void takePoints(Grid grid) //Takes user input for where boat wil go
	{
		points = new Point[getSpotNumber()];
		String whichHalf;
		String sideSelector;

		if(grid.isPlayerOne)
		{
			//System.out.println("Player 1: Your " + getName() + " must be on the bottom half");
			whichHalf = " (bottom half)";
			sideSelector = "bottom";
		}
		else
		{
			//System.out.println("Player 2: Your " + getName() + " must be on the top half");
			whichHalf = " (top half)";
			sideSelector = "top";
		}

		int bat1;
		int bat2;
		int bat3;
		int temporary;
		if(grid.selfSet)
		{
			bat1 = rand.nextInt(10);

			if(grid.isPlayerOne)
			{
				bat2 = rand.nextInt(5) + 5;
			}
			else
			{
				bat2 = rand.nextInt(5);
			}

			temporary = rand.nextInt(2);
			if(temporary== 0 )
			{
				bat3 = 90;
			}
			else
			{
				bat3 = 0;
			}
		}
		else
		{
			bat1 = scan.getX("What will the be the horizontal letter for the " + getName() + " ?" + whichHalf);
			
			bat2 = scan.getY(sideSelector, "What will the be the vertical number for the " + getName() + " ?" + whichHalf);
			
			bat3 = scan.getDirection("What will the be the direction for the " + getName() + " ?");
		}	

		setPoints(bat1, bat2, bat3, grid);
	}	
	
	public abstract void setPoints(int cordX, int cordY, int degree, Grid grid);
	
	
	protected void checkPoints(Grid grid) //Checks if the points given be the user works and assigns them to the board
	{
		boolean pointsWork = true;
		boolean pointsWork2 = true;
				
		// Player One => water is on bottom
		int minY = 5;
		int maxY = 9;
		
		if (!grid.isPlayerOne)
		{
			// Player Two => water in on top
			minY = 0;
			maxY = 4;
		}
		
		for(int i = 0; i < points.length ; i++)
		{
			if (points[i].X < 0 || points[i].X  > 9
				|| points[i].Y <  minY || points[i].Y > maxY)
			{
				pointsWork = false;
				if(!grid.selfSet)
				{	
					System.out.println( "Point " + (i + 1) + " of the ship is off of the board");
				}
			}
		}  
		
		
		if(pointsWork)
		{
			for(int i =0; i<points.length; i++)
			{
				if(grid.board[points[i].Y][points[i].X] != 0)
				{
					pointsWork2 = false;
					if(!grid.selfSet)
					{
						System.out.println( "Point " + i + " of the ship already has a point on it");
					}
				}	
			}	
		}	
		else
		{
			if(!grid.selfSet)
			{
				JOptionPane.showMessageDialog(null, "One or more of the points of your ship is out of bounds. Check horizontal and vertical", "Redo message", JOptionPane.ERROR_MESSAGE);
			}
			takePoints(grid);
		}
		
		if (pointsWork2)
		{
			for(int i = 0; i < points.length ; i++)
			{
				grid.board[points[i].Y][points[i].X] = getID(); // The Y and X valuea are flipped (look at the way the grid is set i is the y value)
			}
		}
		else
		{	
			if(!grid.selfSet)
			{
			JOptionPane.showMessageDialog(null, "One or more of the points of your ship already has a ship on it.", "Redo message", JOptionPane.ERROR_MESSAGE);
			}
			takePoints(grid);
		}	
	}
	
	public abstract String getName();
	
	public abstract int getID();
	
	public abstract int getSpotNumber();
	
}