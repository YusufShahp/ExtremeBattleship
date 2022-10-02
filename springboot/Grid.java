package com.example.springboot;
public class Grid
{	
	int[][] board = new int[10][10];

	public boolean isPlayerOne;
	public boolean selfSet;
	public boolean computer;
	public BoardPanel printer;
	public String BoardPanelContext;
	boolean justStarting;
	public String file;

	public Grid(boolean isPlayerOne, boolean computer, String boardPanelContext, boolean justStarting, String file)
	{
		this.isPlayerOne = isPlayerOne;
		this.computer = computer;
		this.justStarting = justStarting;
		this.file = file;
		if(!computer)
		{
			printer = BoardPanel.CreateNewBoardPanel(file);
			printer.setState(boardPanelContext);
		}
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
		if(!computer)
		{
			printer.paintAgain(board,otherPlayer, justStarting);
		}
	}	

	public void shipGotSunk(int shipSunk)
	{
		if(!computer)
		{
			printer.shipSunk(shipSunk);
		}
	}

	public void endGame(boolean win)
	{
		if(!computer)
		{
			printer.gameOver(win);
		}
	}

	public String returnContext()
	{
		if(!computer)
		{
			return printer.returnState();
		}
		else
		{
			return null;
		}
	}

	private BoardPanel getPrinter()
	{
		if (printer == null)
		{
			printer = BoardPanel.CreateNewBoardPanel(file);
		}

		return printer;
	}
}	
	
	