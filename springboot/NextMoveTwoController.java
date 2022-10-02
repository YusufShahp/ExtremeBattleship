package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class NextMoveTwoController 
{	
	@RequestMapping("/Battleship2/NextMove")
    public String index(@RequestParam int gameID,
                        @RequestParam String playerNumber,
                        @RequestParam String move) 
    {
        try
        {
            GameRecord gameRecord = GameStateManager.getInstance().GetGameRecord(gameID);
            if(gameRecord == null)
            {
                OppSelecDom starter = new OppSelecDom();
                starter.initialize("ErrorPage.html" /*, errorMessage */);
                DatabaseHandler.update(gameRecord.ID, gameRecord.completeSerialization()); 
                return (starter.getHtml());
            }
            gameRecord.setPhase(playerNumber, "attackingPhase");
            String returnVal;

            String otherPlayerNumber;
            if(playerNumber.equals("one"))
            {
                otherPlayerNumber = "two";
            }
            else
            {
                otherPlayerNumber = "one";
            }

            String temp1 = gameRecord.getPhase("one");
            // System.out.println("temp1");
            String temp2 = gameRecord.getPhase("two");
            // System.out.println("temp2");
            DatabaseHandler.update(gameRecord.ID, gameRecord.completeSerialization()); 

            // DEBUG DEBUG
            if(temp1.equals("settingShips") || temp2.equals("settingShips"))
            {
                returnVal = "wait";
            }
            else if(!gameRecord.turn.equals(playerNumber))
            {
                returnVal = "notTurn";
            }
            else
            {
                BattleShipOnlineTwo game = new BattleShipOnlineTwo();
                game.InitializeBoard(gameID, playerNumber);
                if(move.equals("-100"))
                {
                    returnVal = game.getUpdatedHtml(2);
                    if(gameRecord.getPhase(playerNumber).equals("hasWon"))
                    {
                        returnVal = "win";
                    }
                    if(gameRecord.getPhase(otherPlayerNumber).equals("hasWon"))
                    {
                        returnVal = "loss";
                    }
                }
                else
                {
                    game.PlayOneRound(move);
                    returnVal = game.getUpdatedHtml(3);
                    GameRecord gameRecord1 = game.gameRecord;
                    System.out.println(gameRecord1.turn);
                    if(gameRecord1.turn.equals("one"))
                    {
                        gameRecord1.turn = "two";
                    }
                    else
                    {
                        gameRecord1.turn = "one";
                    }
                    System.out.println("now is "  + gameRecord1.turn);

                    // try
                    // {
                    //     TimeUnit.SECONDS.sleep(1);
                    // }
                    // catch(InterruptedException ex)
                    // {
                    //     Thread.currentThread().interrupt();
                    // }
                    //TimeUnit.SECONDS.sleep(1);
                    DatabaseHandler.update(gameRecord1.ID, gameRecord1.completeSerialization()); 
                }
            }
            // DatabaseHandler.update(gameRecord.ID, gameRecord.completeSerialization()); 
            return returnVal;
        }
        catch(Exception e)
        {
            OppSelecDom starter = new OppSelecDom();
            starter.initialize("ErrorPage.html");
            starter.putText("gameID", "Error was Hit:");
            starter.putText("message", e.toString());
            return (starter.getHtml());
        }

		/*BattleShipOnlineTwo game = new BattleShipOnlineTwo();
		game.InitializeBoard(gameID, playerNumber);
        return game.getUpdatedHtml();*/
	}
}
