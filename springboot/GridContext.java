package com.example.springboot;

public class GridContext 
{
    // Sample serialized text:
    // If Board has [a][b]
    // 11,12,13....1b:21,22,23....2b:.....:a1,a2,a3....ab;[0|1]
    public static String serializeGrid(Grid grid)
    {
        String serialize = "";
        for(int i = 0; i < grid.board.length ; i++)
		{
			for (int j = 0; j < grid.board[i].length; j++)
			{
                serialize = serialize + grid.board[i][j];
                if (j != grid.board[i].length - 1)
                {
                    serialize = serialize + ",";
                }
            }

            if (i != grid.board.length - 1)
            {
                serialize = serialize + ":";
            }
        }

        serialize = serialize + ";";
        if(grid.isPlayerOne)
        {
            serialize = serialize + "1";
        }
        else
        {
            serialize = serialize + "0";
        }

        return serialize;        
    }

    // Sample serialized text:
    // If Board has [a][b]
    // 11,12,13....1b:21,22,23....2b:.....:a1,a2,a3....ab;[0|1]
    public static Grid deSerializeGrid(String serialized, String boardPanelInfo, String file)
    {
        Grid temp;
        String[] twoParts = serialized.split(";");

        int[][] boardT = new int[10][10];

        if (twoParts[1].equals("1"))
        {
            temp = new Grid(true, false, boardPanelInfo, false, file);
        }
        else
        {
            if(file.equals("test.html"))
            {
                temp = new Grid(false, true, boardPanelInfo, false, file);
            }
            else
            {
                temp = new Grid(false, false, boardPanelInfo, false, file);
            }
        }

        String[] gridSections = twoParts[0].split(":");
        for(int i = 0; i < gridSections.length; i++)
        {
            String[] row =gridSections[i].split(",");

            for (int j = 0; j< row.length; j++)
            {
                boardT[i][j] = Integer.parseInt(row[j]);
            }
        }

        temp.board = boardT;

        return temp;
    }
}