package com.example.springboot;
import javax.swing.JOptionPane;

public class BattleShipOnline
{ 
	Grid player1 = null; // Corresponds to human player
	Grid player2 = null; // Corresponds to computer
	AttackerContext attackerContext = null;	// For human player
	AttackerAIContext attackerAIContext = null; // For computer player
	boolean isSecondTurn;

	public void InitializeBoard(Grid contextPlayer1, 
								Grid contextPlayer2,
								AttackerContext attackerContext,
								AttackerAIContext attackerAIContext)
	{
		if (contextPlayer1 == null 
		    && contextPlayer2 == null)
		{
			this.player1 = new Grid(true, false, null, true, "test.html");
			this.player2 = new Grid(false, true, null, true, "test.html");	
			
			// sets land and water
			this.player1.setBoard();
			this.player2.setBoard();
			
			// For now we will always self-set (i.e. let computer set for me)
			// Need additional UI and input flows to enable self-set.
			this.player1.selfSet = true;
			this.SetBoard(player1);

			this.player2.selfSet = true;
			this.SetBoard(player2);

			isSecondTurn = false;
		}
		else
		{
			this.player1 = contextPlayer1;
			this.player2 = contextPlayer2;

			isSecondTurn = true;
		}

		if (attackerContext == null
			&& attackerAIContext == null)
		{
			this.attackerContext = new AttackerContext();
			this.attackerAIContext = new AttackerAIContext();
		}
		else
		{
			this.attackerContext = attackerContext;
			this.attackerAIContext = attackerAIContext;
		}
	}

	public String PlayOneRound(String coordinate)
	{
		int x = Integer.parseInt(coordinate.substring(1,2));
		int y = Integer.parseInt(coordinate.substring(0,1));
		this.DoUserAttack(x, y);
		boolean playOn = this.updateGameStatus();
		if(playOn)
		{
			this.DoComputerAttack();
			this.updateGameStatus();
		}
		return this.getUpdatedHtml();
	}

	public String getUpdatedHtml()
	{
		// Print board to ensure the HTML DOM is updated
		player1.printBoard(player2.board);

		player1.printer.putContextToHtml("playerGrid", GridContext.serializeGrid(player1));
		player1.printer.putContextToHtml("computerGrid", GridContext.serializeGrid(player2));
		player1.printer.putContextToHtml("attacker", this.attackerContext.SerializeToString());
		player1.printer.putContextToHtml("attackerAI", this.attackerAIContext.SerializeToString());
		player1.printer.putContextToHtml("boardPanel", player1.returnContext());

		if(isSecondTurn)
		{
			player1.printer.putGlow(getX(), getY());
			return player1.printer.getHtmlBody();
		}
		
		return player1.printer.getHtml();
	}

	private void DoUserAttack(int x, int y)
	{
		Attacker attacker = new Attacker(this.attackerContext,
						/*isOnlineMode*/ true);
		
		attacker.takeAttack(this.player1, 
							this.player2,
							x, y);
	}

	AttackerAI attackerAI;
	private void DoComputerAttack()
	{
		boolean attackSuccessful;
		attackerAI = new AttackerAI(this.attackerAIContext,
								/*isOnlineMode*/ true);
		do
		{
			attackSuccessful = attackerAI.takeAttack(player2, player1);
		} 
		while (!attackSuccessful);
	}	

	private int getX()
	{
		return attackerAI.attackerGuessX;
	}

	private int getY()
	{
		return attackerAI.attackerGuessY;
	}

	private boolean updateGameStatus()
	{	
		if (this.attackerAIContext.totalPoints == 0)
		{
			// Computer has won
			player1.endGame(false);
			isSecondTurn = false;
			return true;
		} 
		if (this.attackerContext.totalPoints == 0)
		{
			// Human player has won
			player1.endGame(true);
			isSecondTurn = false;
			return false;
		}
		return true;
	}

	private void SetBoard(Grid player)
	{
		Ship batTwo = new Battleship(player);
		Ship subTwo = new Submarine(player);
		Ship oilTwo = new Oilship(player);
		Ship airTwo = new Aircraft(player);
		Ship triTwo = new Triangle(player);
	}
}	