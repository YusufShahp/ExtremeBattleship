package com.example.springboot;

public class BoardPanel //implements ActionListener
{	
    private BoardPanel()
	{}
	
	boolean ready = false;
	SampleHmlDom sampleDom = new SampleHmlDom();

	public static BoardPanel CreateNewBoardPanel(String file)
	{
		BoardPanel panel = new BoardPanel();
		
		/****************************
		// Initialize the JPanel UI items
		JFrame frame = new JFrame("Battleship Extreme");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
		if (print)
		{			
			frame.setLocation(0,0);
		}
		**********************************/		
		
		// Also intialize the HTML UI items
		panel.start(file);
		return panel;
	}

	public void setState(String state)
	{
		if(state != null)
		{
			String[] values = state.split(",");
			if(values[0].equals("1"))
			{
				isOil = true;
			}
			else
			{
				isOil = false;
			}

			if(values[1].equals("1"))
			{
				isSub = true;
			}
			else
			{
				isSub = false;
			}

			if(values[2].equals("1"))
			{
				isBat = true;
			}
			else
			{
				isBat = false;
			}

			if(values[3].equals("1"))
			{
				isAir = true;
			}
			else
			{
				isAir = false;
			}

			if(values[4].equals("1"))
			{
				isTri = true;
			}
			else
			{
				isTri = false;
			}
		}
	}

	public String returnState()
	{
		String value = "";
		if(isOil)
		{
			value = value + "1,";
		}
		else
		{
			value = value + "0,";
		}

		if(isSub)
		{
			value = value + "1,";
		}
		else
		{
			value = value + "0,";
		}

		if(isBat)
		{
			value = value + "1,";
		}
		else
		{
			value = value + "0,";
		}

		if(isAir)
		{
			value = value + "1,";
		}
		else
		{
			value = value + "0,";
		}

		if(isTri)
		{
			value = value + "1";
		}
		else
		{
			value = value + "0";
		}

		return value;
	}

	private void start(String file)
	{
		try
		{
			sampleDom.initialize(file);
		}
		catch(Exception e)
		{
			// Swallow any exceptions
		}
		finally
		{}
	}

	public void putContextToHtml(String id, String context)
	{
		sampleDom.putContext(id, context);
	}
	
	int[][] board;
	int[][] otherBoard;
	boolean firstTime;
	public void paintAgain(int[][] board, int[][] otherBoard, boolean firstTime)
	{
		ready = true;
		this.board = board;    
		this.otherBoard = otherBoard;    
		this.firstTime = firstTime;
		paint();
	}	

	boolean isBat;
	boolean isSub;
	boolean isOil;
	boolean isAir;
	boolean isTri;

	public void shipSunk(int sunkShip)
	{
		if(sunkShip == 1)
		{
			isOil = true;
		}
		if(sunkShip == 2)
		{
			isSub = true;
		}
		if(sunkShip == 3)
		{
			isBat = true;
		}
		if(sunkShip == 4)
		{
			isAir = true;
		}
		if(sunkShip == 5)
		{
			isTri = true;
		}
	}

	boolean gameHasEnded;
	boolean win;
	public void gameOver(boolean win)
	{
		gameHasEnded = true;		
		this.win = win;
	}
	
	
	boolean isHit;
	boolean isMiss;
	boolean isShip;
	boolean shipHit;
	boolean shipMiss;
	boolean sunkShip;

	boolean attackingPhase;

	boolean placedBat;
	boolean placedSub;
	boolean placedOil;
	boolean placedAir;
	boolean placedTri;

	NextMoveController controller = new NextMoveController();
	
	public void paint()
	{
		if(ready)
		{	
			if(firstTime)
			{
				sampleDom.showOption();
			}
			for(int i = 0; i < board.length ; i++)
			{
				for(int j = 0; j < board[i].length; j++)
				{
					isHit = false;
					isMiss = false;
					isShip = false;
					shipHit = false;
					shipMiss = false;
					sunkShip = false;
					attackingPhase = true;
					if(board[i][j] ==0)
					{
						sampleDom.updateCellColor(i, j, "water");
					}	
					if(board[i][j] ==1)
					{
						sampleDom.updateCellColor(i, j, "land");
					}	
					if(board[i][j] ==2)
					{
						isShip = true;
						placedOil =true;
						sampleDom.updateCellColor(i, j, "oilship");
					}	
					if(board[i][j] ==3)
					{
						isShip = true;
						placedSub =true;
						sampleDom.updateCellColor(i, j, "submarine");
					}	
					if(board[i][j] ==4)
					{
						isShip = true;
						placedBat =true;
						sampleDom.updateCellColor(i, j, "battleship");
					}	
					if(board[i][j] ==5)
					{
						isShip = true;
						placedAir = true;
						sampleDom.updateCellColor(i, j, "aircraft");
					}	
					if(board[i][j] ==6)
					{
						isShip = true;
						placedTri = true;
						attackingPhase = true;
						sampleDom.updateCellColor(i, j, "triangle");
					}	
					if(board[i][j] ==7)
					{
						//
					}	
					if(board[i][j] ==8)
					{
						isMiss = true;
						sampleDom.updateCellColor(i, j, "land");
						sampleDom.updateCellStatus(i, j, "missSolid");
					}	
					if(board[i][j] ==9)
					{
						isHit = true;
						sampleDom.updateCellColor(i, j, "land");
						sampleDom.updateCellStatus(i, j, "hitSolid");
						if(isOil)
						{
							if(otherBoard[i][j] ==12)
							{
								sunkShip = true;
								sampleDom.updateCellColor(i, j, "sunk");
								sampleDom.deleteCellStatus(i, j);
							}
						}
						if(isSub)
						{
							if(otherBoard[i][j] ==13)
							{
								sunkShip = true;
								sampleDom.updateCellColor(i, j, "sunk");
								sampleDom.deleteCellStatus(i, j);
							}
						}
						if(isBat)
						{
							if(otherBoard[i][j] ==14)
							{
								sunkShip = true;
								sampleDom.updateCellColor(i, j, "sunk");
								sampleDom.deleteCellStatus(i, j);
							}
						}
						if(isAir)
						{
							if(otherBoard[i][j] ==15)
							{
								sunkShip = true;
								sampleDom.updateCellColor(i, j, "sunk");
								sampleDom.deleteCellStatus(i, j);
							}
						}
						if(isTri)
						{
							if(otherBoard[i][j] ==16)
							{
								sunkShip = true;
								sampleDom.updateCellColor(i, j, "sunk");
								sampleDom.deleteCellStatus(i, j);
							}
						}
					}	
					if(board[i][j] ==12)
					{
						shipHit = true;
						isShip = true;
						sampleDom.updateCellColor(i, j, "oilship");
						sampleDom.updateCellStatus(i, j, "hitLine");
					}
					if(board[i][j] ==13)
					{
						shipHit = true;
						isShip = true;
						sampleDom.updateCellColor(i, j, "submarine");
						sampleDom.updateCellStatus(i, j, "hitLine");
					}
					if(board[i][j] ==14)
					{
						shipHit = true;
						isShip = true;
						sampleDom.updateCellColor(i, j, "battleship");
						sampleDom.updateCellStatus(i, j, "hitLine");
					}
					if(board[i][j] ==15)
					{
						shipHit = true;
						isShip = true;
						sampleDom.updateCellColor(i, j, "aircraft");
						sampleDom.updateCellStatus(i, j, "hitLine");
					}
					if(board[i][j] ==16)
					{
						shipHit = true;
						isShip = true;
						sampleDom.updateCellColor(i, j, "triangle");
						sampleDom.updateCellStatus(i, j, "hitLine");
					}
					if(board[i][j] ==18)
					{
						shipMiss = true;
						sampleDom.updateCellColor(i, j, "water");
						sampleDom.updateCellStatus(i, j, "missLine");
					}
					/*g.fillRect(j*40+30, i*40+30, 40, 40);
					
					if(!isShip)
					{	
						g.setColor(Color.white);
						g.drawRect(j*40+30, i*40+30, 40, 40);
					}
					if(isHit)
					{
						g.setColor(hit);
						g.fillOval(j*40+32, i*40+32, 35, 35);
					}
					
					if(isMiss)
					{
						g.setColor(miss);
						g.fillOval(j*40+32, i*40+32, 35, 35);
					}	
					
					if(shipHit)
					{
						g.setColor(hit);
						g.drawOval(j*40+32, i*40+32, 35, 35);
					}	
					
					if(shipMiss)
					{
						g.setColor(miss);
						g.drawOval(j*40+32, i*40+32, 35, 35);
					}	
					if(sunkShip)
					{
						g.setColor(sunk);
						g.fillRect(j*40+30, i*40+30, 40, 40);
					}*/
				}
			}	
			/*String temp;
			for(int i=0; i<10 ; i++)
			{
				temp = String.valueOf(i + 1);
				g.drawString( temp, 5, i*40+55);
			}	
			
			char temp1;
			for(int i=0; i<10; i++)
			{
				temp1 = (char)((int)'A' + i);
				temp = String.valueOf(temp1);
				g.drawString(temp, i*40+45, 25);
			}	*/

			sampleDom.deleteStrike("oil");
			sampleDom.deleteStrike("sub");
			sampleDom.deleteStrike("bat");
			sampleDom.deleteStrike("air");
			sampleDom.deleteStrike("tri");

			//Info section
			/*if(!attackingPhase)
			{
				sampleDom.updateText(false);
			}
			else
			{
				sampleDom.updateText(true);
			}*/

			if(isBat)
			{
				sampleDom.putStrike("bat");
			}

			if(isSub)
			{
				sampleDom.putStrike("sub");
			}

			if(isOil)
			{
				sampleDom.putStrike("oil");
			}

			if(isAir)
			{
				sampleDom.putStrike("air");
			}

			if(isTri)
			{
				sampleDom.putStrike("tri");
			}

			/*if(!attackingPhase)
			{
				if(placedBat)
				{
					sampleDom.putStrike("bat");
				}

				if(placedSub)
				{
					sampleDom.putStrike("sub");
				}

				if(placedOil)
				{
					sampleDom.putStrike("oil");
				}

				if(placedAir)
				{
					sampleDom.putStrike("air");
				}

				if(placedTri)
				{
					sampleDom.putStrike("tri");
				}
			}*/
		}

		if(gameHasEnded)
		{
			if(win)
			{
				sampleDom.over(true);
			}
			else
			{
				sampleDom.over(false);
			}
		}
		sampleDom.writeHtmlToFile();
	} 
	
	public void update ()
	{
		paint();
	}

	public String getHtml()
	{
		return sampleDom.getHtml();
	}
	//new
	public String getHtmlBody()
	{
		return sampleDom.getHtmlBody();
	}
	//new
	public void putGlow(int x, int y)
	{
		sampleDom.putGlow(x, y);
	}
}




















