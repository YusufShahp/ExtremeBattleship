package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {	
	@RequestMapping("/")
	public String index() {
		WelcomePageDom starter = new WelcomePageDom();
		System.out.println("Created WelcomePageDom");

		starter.initialize();
        System.out.println("initialized WelcomePageDom");

		/*
		TestAzureTable controller = new TestAzureTable();
		controller.addEntity();
		System.out.println("has run");
		*/

		// DatabaseHandler.makeNewGame(16, "String1");
		// DatabaseHandler.update(16, "String3");
		// ArrayList<GameRecord> trial = new ArrayList<GameRecord>();
		// DatabaseHandler.setGameRecordData(trial);
		// System.out.println(trial.get(1).ID);
		// System.out.println(trial.get(1).completeSerialization());

		GameRecord test = new GameRecord(15);
		test.bothConnected = true;
		test.turn = "1";
		test.latestGuessX = 7;
		test.latestGuessY = 9;
		test.latestPlayerToGuess = "one";
		test.setPhase("one", "attack");
		test.setPhase("two", "attack2");
		test.setGrid("one", "grid1");
		test.setGrid("two", "grid2");
		test.setAttacker("one", "attacker1");
		test.setAttacker("two", "attack2");
		test.setPanel("one", "testtest");
		test.setPanel("one", "test");
		System.out.println(test.completeSerialization());
		System.out.println("test");

		GameRecord test1 = new GameRecord(1);
		test1.completePopulate(test.completeSerialization());

		// try{
		// 	DemoApplication demo = new DemoApplication();
		// }
		// catch(Exception e)
		// {
		// 	System.out.println(e);
		// }

        System.out.println("Going to return HTML for WelcomePageDom hi hi hi 5");
		return starter.getHtml();
	}
}
