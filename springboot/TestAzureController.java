package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class TestAzureController {
	@RequestMapping("/TestAzure")
	public String index() {
        /*
        TestAzureTable test = new TestAzureTable();
		test.createTable();
        test.addEntity();
        */
        return "ThisWorks";
	}
}