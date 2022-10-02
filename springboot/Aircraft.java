package com.example.springboot;
public class Aircraft extends Ship
{
	protected int shipID = 5;
	protected int hitPoints = 5;
	protected int spotNumber = 5;
	protected String name = "Aircraft Carrier";
	
	public Aircraft(Grid grid)
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
				if(i<3)
				{					
					points[i].X = cordX;
					points[i].Y = cordY + i;
				}	
				else
				{
					points[i].X = cordX + 1;
					points[i].Y = cordY + i -3;
				}
			}
		}	
		else 
		{
			for (int i = 0; i < points.length; i++)
			{
				points[i] = new Point();
				if(i<3)
				{
				points[i].X = cordX + i;
				points[i].Y = cordY;
				}
				else
				{
					points[i].X = cordX + i -3;
					points[i].Y = cordY + 1;
				}
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