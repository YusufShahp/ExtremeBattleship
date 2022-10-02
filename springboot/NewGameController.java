package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class NewGameController {	
	@RequestMapping("/Battleship")
	public String index() {
		BattleShipOnline game = new BattleShipOnline();
		game.InitializeBoard(null, null, null, null);
		return game.getUpdatedHtml();
	}
}
