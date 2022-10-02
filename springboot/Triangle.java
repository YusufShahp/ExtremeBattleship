package com.example.springboot;
public class Triangle extends Ship
{
	protected int shipID = 6;
	protected int hitPoints = 3;
	protected int spotNumber = 3;
	protected String name = "Command Module (Triangle)";
	
	public Triangle(Grid grid)
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
				if(i<2)
				{					
					points[i].X = cordX;
					points[i].Y = cordY + i;
				}	
				else
				{
					points[i].X = cordX - 1;
					points[i].Y = cordY;
				}
			}
		}	
		else 
		{
			for (int i = 0; i < points.length; i++)
			{
				points[i] = new Point();
				if(i<2)
				{
				points[i].X = cordX + i;
				points[i].Y = cordY;
				}
				else
				{
					points[i].X = cordX ;
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