package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class WaitingRoomController {	
	@RequestMapping("/Battleship2/Wait")
	public String index(@RequestParam int gameID, @RequestParam String playerNumber) {
        GameRecord gameRecord = GameStateManager.getInstance().GetGameRecord(gameID);
        gameRecord.setPhase(playerNumber, "attackingPhase");

        if(gameRecord.getPhase("one").equals(gameRecord.getPhase("two")))
        {
            DatabaseHandler.update(gameRecord.ID, gameRecord.completeSerialization()); 
            return "ready";
        }
        else
        {
            String temp = "" + gameRecord.getID();
			OppSelecDom starter = new OppSelecDom();
			starter.initialize("waitingRoom.html");
			starter.putText("gameID", temp);
			starter.putText("playerNumber", playerNumber);
			starter.putText("message", "Wait for the other player to finnish setting their ships");
			DatabaseHandler.update(gameRecord.ID, gameRecord.completeSerialization()); 
            return (starter.getHtml());
        }
	}
}
