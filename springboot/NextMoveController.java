package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class NextMoveController {
	@RequestMapping("/Battleship/NextMove")
	public String index(
		@RequestParam String move,
		@RequestParam String attacker,
		@RequestParam String attackerAI,
		@RequestParam String playerGrid,
		@RequestParam String computerGrid,
		@RequestParam String boardPanel)
	{
		Grid player1 = GridContext.deSerializeGrid(playerGrid, boardPanel, "test.html");
		Grid player2 = GridContext.deSerializeGrid(computerGrid, boardPanel, "test.html");
		AttackerContext attackerContext = AttackerContext.CreateAttackContext(attacker);
		AttackerAIContext attackerAIContext = AttackerAIContext.CreateAttackAIContext(attackerAI);
		
		BattleShipOnline game = new BattleShipOnline();
		game.InitializeBoard(player1, player2, attackerContext, attackerAIContext);
		game.PlayOneRound(move);
		return game.getUpdatedHtml();
	}
}
