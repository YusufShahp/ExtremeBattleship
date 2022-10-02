package com.example.springboot;

public class AttackerContext
{
	public int totalPoints;
	public int oilship;
	public int submarine;
	public int battleship;
	public int aircraft;
	public int triangle;
	boolean wasAttackSuccessful = false;

	static public int numberOfItems = 6;

	public AttackerContext()
	{
		// Default initial values for the AttackContext
		this.totalPoints = 17;
		this.oilship = 2;
		this.submarine = 3;
		this.battleship = 4;
		this.aircraft = 5;
		this.triangle = 3;	
	}

	public AttackerContext(int totalPoints,
						int oilship,
		 				int submarine,
						int battleship,
						int aircraft,
						int triangle)	
	{
		this.totalPoints = totalPoints;
		this.oilship = oilship;
		this.submarine = submarine;
		this.battleship = battleship;
		this.aircraft = aircraft;
		this.triangle = triangle;
	}

	public String SerializeToString()
	{
		String serializedValue = totalPoints + "," +
								 oilship + "," +
								 submarine + "," +
								 battleship + "," +
								 aircraft + "," +
								 triangle;
		return serializedValue;
	}
	
	public static AttackerContext CreateAttackContext(String serializedValue)
	{
		String[] values = serializedValue.split(",");
		if (values.length == numberOfItems)
		{
			return new AttackerContext(/*totalPoints*/ Integer.parseInt(values[0]),
								    /*oilship*/ Integer.parseInt(values[1]),
									/*submarine*/ Integer.parseInt(values[2]),
									/*battleship*/ Integer.parseInt(values[3]),
									/*aircraft*/ Integer.parseInt(values[4]),
									/*triangle)*/ Integer.parseInt(values[5]));
		}

		return null;
	}
}	
