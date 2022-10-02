package com.example.springboot;
public class GridAI
{	
	int[][] board = new int[10][10];
	public boolean isPlayerOne;
	public boolean selfSet;
	public BoardPanel printer;
	public GridAI (boolean isPlayerOne, BoardPanel printer)
	{
		this.isPlayerOne = isPlayerOne;
		this.printer = printer;
	}
	
    // Initializes the board with land and water
	public void setBoard()
	{
		for(int i = 0; i < board.length ; i++)
		{
			for(int j = 0; j < board[i].length; j++)
			{
				if (isPlayerOne)
				{
					if (i < 5)
					{
						board[i][j] = 1;      
					}
				}
				
				else
				{
					if (i >= 5)
					{
						board[i][j] = 1;      
					}
				}		
			}
		}
	}	
	
	public void printBoardC()
	{
		if(isPlayerOne)
		{
			System.out.println("This is Player One's board");
		}
		else
		{
			System.out.println("This is Player Two's board");
		}
		
		for(int i = 0; i < board.length ; i++)
		{
			System.out.print("        ");
			for(int j = 0; j < board[i].length; j++)
			{	
				System.out.print(board[i][j]);
				System.out.print(" ");
			}
            
            System.out.println("");		
		}
	}		
	
	public void printBoard(int[][] otherPlayer)
	{

	}	

	public void shipGotSunk(int shipSunk)
	{
		
	}

	public void endGame(boolean win)
	{
		
	}
}	