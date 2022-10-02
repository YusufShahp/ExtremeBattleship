package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class NewTwoGameController {	
	@RequestMapping("/Battleship2")
	public String index(@RequestParam int gameID, @RequestParam String playerNumber) {
		BattleShipOnlineTwo game = new BattleShipOnlineTwo();
		game.InitializeBoard(gameID, playerNumber);
        return game.getUpdatedHtml(1);
	}
}