package com.example.springboot;
import javax.swing.JOptionPane;

public class BattleShipOnlineTwo
{ 
	Grid player = null; // Corresponds to human player
	Grid otherPlayer = null; // Corresponds to computer
	AttackerContext attackerContext = null;	// For human player
	AttackerContext otherAttackerContext = null; // For computer player
    boolean isSecondTurn;
	int gameID;
	String playerNumber;
	String otherPlayerNumber;
	GameRecord gameRecord;
	boolean oneRoundPlayed = false;

	public void InitializeBoard(int gameID, String playerNumber)						
	{
		this.playerNumber = playerNumber;
		this.gameID = gameID;
		this.gameRecord = GameStateManager.getInstance().GetGameRecord(gameID);
		if(this.playerNumber.equals("one"))
		{
			otherPlayerNumber = "two";
		}
		else
		{
			otherPlayerNumber = "one";
		}

		if (gameRecord.getPhase(playerNumber).equals("settingShips"))
		{
			if(this.playerNumber.equals("one"))
			{
				this.player = new Grid(true, false, null, true, "twoPlayer.html");
			}
			else
			{
				this.player = new Grid(false, false, null, true, "twoPlayer.html");
			}
			//this.player2 = new Grid(false, true, null, true);	
			
			// sets land and water
			this.player.setBoard();
			//this.player2.setBoard();
			
			// For now we will always self-set (i.e. let computer set for me)
			// Need additional UI and input flows to enable self-set.
			this.player.selfSet = true;
			this.SetBoard(player);

			//this.player2.selfSet = true;
			//this.SetBoard(player2);

			isSecondTurn = false;
		}
		else
		{
			this.player = GridContext.deSerializeGrid(gameRecord.getGrid(playerNumber), gameRecord.getPanel(playerNumber), "twoPlayer.html");
			this.otherPlayer = GridContext.deSerializeGrid(gameRecord.getGrid(otherPlayerNumber), gameRecord.getPanel(otherPlayerNumber), "twoPlayer.html");

			isSecondTurn = true;
		}

		if (gameRecord.getPhase(playerNumber).equals("settingShips"))
		{
			this.attackerContext = new AttackerContext();
			//this.attackerAIContext = new AttackerAIContext();
			DatabaseHandler.update(gameRecord.ID, gameRecord.completeSerialization()); 
		}
		else
		{
			this.attackerContext = AttackerContext.CreateAttackContext(gameRecord.getAttacker(playerNumber));
			this.otherAttackerContext = AttackerContext.CreateAttackContext(gameRecord.getAttacker(otherPlayerNumber));
			DatabaseHandler.update(gameRecord.ID, gameRecord.completeSerialization()); 
		}
	}

	public String PlayOneRound(String coordinate)
	{
		int x = Integer.parseInt(coordinate.substring(1,2));
		int y = Integer.parseInt(coordinate.substring(0,1));
		this.DoUserAttack(x, y);
		/*boolean playOn = this.updateGameStatus();
		if(playOn)
		{
			//this.DoComputerAttack();
			this.updateGameStatus();
		}*/
		updateGameStatus();
		oneRoundPlayed = true;
		// DatabaseHandler.update(gameRecord.ID, gameRecord.completeSerialization()); 
		return this.getUpdatedHtml(3);
	}

	public String getUpdatedHtml(int option)
	{
		if(option == 1)
		{
			int[][] temp = new int[10][10];
			player.printBoard(temp);
		}
		else
		{
			player.printBoard(otherPlayer.board);
		}
        // Print board to ensure the HTML DOM is updated
        //normally expects player2.board

		/*player1.printer.putContextToHtml("playerGrid", GridContext.serializeGrid(player1));
		player1.printer.putContextToHtml("computerGrid", GridContext.serializeGrid(player2));
		player1.printer.putContextToHtml("attacker", this.attackerContext.SerializeToString());
		player1.printer.putContextToHtml("attackerAI", this.attackerAIContext.SerializeToString());
		player1.printer.putContextToHtml("boardPanel", player1.returnContext());*/
		if(option != 2)
		{
			gameRecord.setGrid(playerNumber, GridContext.serializeGrid(player));
			gameRecord.setAttacker(playerNumber, this.attackerContext.SerializeToString());
			gameRecord.setPanel(playerNumber, player.returnContext());
			
			//If other player exists the state has to persisted as well
			if(otherPlayer != null)
			{
				gameRecord.setGrid(otherPlayerNumber, GridContext.serializeGrid(otherPlayer));
				gameRecord.setAttacker(otherPlayerNumber, this.otherAttackerContext.SerializeToString());
				gameRecord.setPanel(otherPlayerNumber, otherPlayer.returnContext());
			}
		}	
		
		String temp1 = "" + gameID;
		player.printer.putContextToHtml("gameID", temp1);
		player.printer.putContextToHtml("playerNumber", playerNumber);
		
		if(option != 1)
		{
			if(this.attackerContext.totalPoints == 0)
			{
				player.endGame(true);
				isSecondTurn = false;
				player.printer.putContextToHtml("status", "");
			}
			if(this.otherAttackerContext.totalPoints == 0)
			{
				player.endGame(false);
				isSecondTurn = false;
				player.printer.putContextToHtml("status", "");
			}
		}

		if(isSecondTurn)
		{
			if(gameRecord.latestPlayerToGuess != null)
			{
				if(gameRecord.latestPlayerToGuess.equals(otherPlayerNumber))
				{
					player.printer.putGlow(gameRecord.latestGuessX, gameRecord.latestGuessY);
				}	
			}	
		}

		if(!oneRoundPlayed)
		{
			DatabaseHandler.update(gameRecord.ID, gameRecord.completeSerialization()); 
		}
		return player.printer.getHtml();
	}

	private void DoUserAttack(int x, int y)
	{
		Attacker attacker = new Attacker(this.attackerContext,
						/*isOnlineMode*/ true);
		
		attacker.takeAttack(this.player, 
							this.otherPlayer,
							x, y);
		gameRecord.latestGuessX = x;
		gameRecord.latestGuessY = y;
		gameRecord.latestPlayerToGuess = playerNumber;
		// DatabaseHandler.update(gameRecord.ID, gameRecord.completeSerialization()); 
	}

	/*AttackerAI attackerAI;
	private void DoComputerAttack()
	{
		boolean attackSuccessful;
		attackerAI = new AttackerAI(this.attackerAIContext,
								/*isOnlineMode true);
		do
		{
			attackSuccessful = attackerAI.takeAttack(otherPlayer, player);
		} 
		while (!attackSuccessful);
	}*/

	/*private int getX()
	{
		return attackerAI.attackerGuessX;
	}

	private int getY()
	{
		return attackerAI.attackerGuessY;
	}*/

	/*private boolean updateGameStatus()
	{	
		/*if (this.otherAttackerContext.totalPoints == 0)
		{
			// Computer has won
			player.endGame(false);
			isSecondTurn = false;
			return true;
		}
		if (this.attackerContext.totalPoints == 0)
		{
			// Human player has won
			player.endGame(true);
			isSecondTurn = false;
			gameRecord.setPhase(playerNumber, "hasWon");
			return false;
		}
		return true;
	}*/

	private void updateGameStatus()
	{
		if (this.attackerContext.totalPoints == 0)
		{
			// Human player has won
			gameRecord.setPhase(playerNumber, "hasWon");
		}
		// DatabaseHandler.update(gameRecord.ID, gameRecord.completeSerialization()); 
	}

	private void SetBoard(Grid player)
	{
		Ship batTwo = new Battleship(player);
		Ship subTwo = new Submarine(player);
		Ship oilTwo = new Oilship(player);
		Ship airTwo = new Aircraft(player);
		Ship triTwo = new Triangle(player);
		// DatabaseHandler.update(gameRecord.ID, gameRecord.completeSerialization()); 
	}
}	
