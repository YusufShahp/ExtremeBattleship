package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class JoinGameController {	
	@RequestMapping("/Battleship/Join")
    public String index(@RequestParam String input) 
    {
        try
        {
            int id = Integer.parseInt(input);
            GameRecord gameRecord = GameStateManager.getInstance().GetGameRecord(id);
            if(gameRecord == null)
            {
                OppSelecDom starter = new OppSelecDom();
                starter.initialize("ErrorPage.html");
                DatabaseHandler.update(gameRecord.ID, gameRecord.completeSerialization()); 
                return (starter.getHtml());
            }
            
            if(gameRecord.getPhase("two") == null)
            {
                gameRecord.bothConnected = true;
                
                String temp = "" + gameRecord.getID();
                OppSelecDom starter = new OppSelecDom();
                starter.initialize("TemporaryPage.html");
                starter.putText("gameID", temp);
                starter.putText("playerNumber", "two");
                gameRecord.setPhase("two", "settingShips");
                DatabaseHandler.update(gameRecord.ID, gameRecord.completeSerialization()); 
                return (starter.getHtml());
            }
            else
            {
                OppSelecDom starter = new OppSelecDom();
                starter.initialize("ErrorPage.html");
                DatabaseHandler.update(gameRecord.ID, gameRecord.completeSerialization()); 
                return (starter.getHtml());
            }
	    }
        catch(Exception e)
        {
            OppSelecDom starter = new OppSelecDom();
            starter.initialize("ErrorPage.html");
            starter.putText("gameID", "Error was Hit:");
            starter.putText("message", e.toString());
            return (starter.getHtml());
        }
    }
}
