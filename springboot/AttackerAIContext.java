package com.example.springboot;

import java.util.HashMap;

public class AttackerAIContext extends AttackerContext
{
    public HashMap<Integer, Point> points;
    public int pointNumber = 0;

    private static String serializedValueConcat = "Z";
    private static String serializedValueAIPoints = "p";

    public AttackerAIContext()
    {
        super();
        this.points = new HashMap<Integer, Point>();
        this.pointNumber = 0;
    }

    public AttackerAIContext(AttackerContext superContext,
                        HashMap<Integer, Point> points,
                        int pointNumber)	
	{
        super(superContext.totalPoints, 
            superContext.oilship,
            superContext.submarine,
            superContext.battleship,
            superContext.aircraft,
            superContext.triangle);
        
        this.points = points;
        this.pointNumber = pointNumber;
    }

    public String SerializeToString()
	{
        String baseValue = super.SerializeToString();
        
        // Serialize to this format: pointNumber;x.y;x.y;x.y
        // Final format <super's serialized value>-<points serialized value>
        String pointsSerializedValue = this.pointNumber + ";";
        for (int i = 0; i < this.pointNumber; i++)
        {
            Point temp = this.points.get(i);
            if (i > 0)
            {
                pointsSerializedValue = pointsSerializedValue + ";";
            }

            pointsSerializedValue = pointsSerializedValue + temp.X + serializedValueAIPoints + temp.Y;
        }

		return baseValue + serializedValueConcat + pointsSerializedValue;
	}
	
	public static AttackerAIContext CreateAttackAIContext(String serializedValue)
	{
		String[] values = serializedValue.split(serializedValueConcat);
		if (values.length == 2)
		{
            AttackerContext tempSuperContext = AttackerContext.CreateAttackContext(values[0]);

            String[] pointsValues = values[1].split(";");
            int tempPointsNumber = Integer.parseInt(pointsValues[0]);

            HashMap<Integer, Point> tempPoints= new HashMap<Integer, Point>();
            for (int i = 0; i < tempPointsNumber; i++)
            {
                String[] coordinates = pointsValues[i+1].split(serializedValueAIPoints);
                Point tempPoint = new Point();
                tempPoint.X = Integer.parseInt(coordinates[0]);
                tempPoint.Y = Integer.parseInt(coordinates[1]);

                tempPoints.put(i, tempPoint);
            }

            return new AttackerAIContext(tempSuperContext,
                                        tempPoints,
                                        tempPointsNumber);
		}

		return null;
	}
}