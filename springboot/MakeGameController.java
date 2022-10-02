package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MakeGameController {	
	@RequestMapping("/Battleship/Make")
	public String index(@RequestParam String input) 
	{
		try
		{
			int id = Integer.parseInt(input);

			GameRecord gameRecord = GameStateManager.getInstance().GetGameRecord(id);

			if(gameRecord.bothConnected)
			{
				gameRecord.setPhase("one", "settingShips");
				DatabaseHandler.update(gameRecord.ID, gameRecord.completeSerialization()); 
				return "ready";
			}
			else
			{
				String temp = "" + gameRecord.getID();
				OppSelecDom starter = new OppSelecDom();
				starter.initialize("OppSelecPage.html");
				starter.putText("gameID", temp);
				starter.putText("playerNumber", "one");
				starter.putText("message", "Please wait for someone else to join the game");
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
