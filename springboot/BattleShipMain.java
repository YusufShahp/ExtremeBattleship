package com.example.springboot;
import javax.swing.JOptionPane;
public class BattleShipMain
{ 
	/*public static void main(String args[])
	{	
		/*Dom test = new Dom();
		test.initialize();
		test.updateCellColor(0, 0, "land");
		test.outputHtml();
	 	BattleShipMain.run();    
	}
	
	static void run()
	{		
    	Grid player1 = new Grid(true, false);
		Grid player2 = new Grid(false, true);

		if (JOptionPane.showConfirmDialog(null, "Welcome to Battleship, do you want to autoplace your ships", "BATTLESHIP", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
		{
			player1.selfSet = true;
		} 
		else 
		{
			player1.selfSet = false;
		}

		player2.selfSet = true;
		
		player1.setBoard();
		player2.setBoard();

		player1.printBoard(player2.board);
		//player2.printBoard(player1.board);

		//player2.printBoard(player1.board);
		Ship batTwo = new Battleship(player2);
		Ship subTwo = new Submarine(player2);
		Ship oilTwo = new Oilship(player2);
		Ship airTwo = new Aircraft(player2);
		Ship triTwo = new Triangle(player2);
		//player2.printBoard(player1.board);

		if (player1.selfSet)
		{
			player1.printBoard(player2.board);
			Ship batOne = new Battleship(player1);
			Ship subOne = new Submarine(player1);
			Ship oilOne = new Oilship(player1);
			Ship airOne = new Aircraft(player1);
			Ship triOne = new Triangle(player1);
			player1.printBoard(player2.board);

		}

		if (!player1.selfSet)
		{
			player1.printBoard(player2.board);
			Ship batOne = new Battleship(player1);
			player1.printBoard(player2.board);

			Ship subOne = new Submarine(player1);
			player1.printBoard(player2.board);
			
			Ship oilOne = new Oilship(player1);
			player1.printBoard(player2.board);
			
			Ship airOne = new Aircraft(player1);
			player1.printBoard(player2.board);
			
			Ship triOne = new Triangle(player1);
			player1.printBoard(player2.board);
		}

		
		player1.printBoard(player2.board);
	    // player2.printBoard(player1.board); 
		
		System.out.println("");
		System.out.println("Now each player will get to attack");
		
		Attacker attacker1 = new Attacker();
		AttackerAI attacker2 = new AttackerAI();
		
		while (attacker1.totalPoints >= 1 && attacker2.totalPoints >= 1)
		{
			boolean attackSuccessful;
			do
			{
				attackSuccessful = attacker1.takeAttack(player1, player2);
			} 
			while (!attackSuccessful);
			
			player1.printBoard(player2.board);
		    // player2.printBoard(player1.board);
			
			do
			{
				attackSuccessful = attacker2.takeAttack(player2, player1);
			} 
			while (!attackSuccessful);
			
			player1.printBoard(player2.board);
		    // player2.printBoard(player1.board);
		}
		
		if (attacker1.totalPoints == 0)
		{
			player1.endGame(true);
		}
		else
		{
			player1.endGame(false);
		} 

		player1.printBoard(player2.board);
	}
	*/
}	