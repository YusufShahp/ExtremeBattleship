package com.example.springboot;
public class Battleship extends Ship
{
	protected int shipID = 4;
	protected int hitPoints = 4;
	protected int spotNumber = 4;
	protected String name = "Battleship";
	
	public Battleship(Grid grid)
	{
		takePoints(grid);
	}		
	public void setPoints(int cordX, int cordY, int degree, Grid grid) //sets the input to the array of points
	{
		if (degree==90)
		{
			for (int i = 0; i < points.length; i++)
			{
				points[i] = new Point();
				points[i].X = cordX;
				points[i].Y = cordY + i;
			}
		}	
		else 
		{
			for (int i = 0; i < points.length; i++)
			{
				points[i] = new Point();
				points[i].X = cordX + i;
				points[i].Y = cordY;
			}
		}
		
		checkPoints(grid);
	}	
	
	public String getName()
	{
		return name;
	}

	public int getID()
	{
		return shipID;
	}

	public int getSpotNumber()
	{
		return spotNumber;
	}	
}			
	