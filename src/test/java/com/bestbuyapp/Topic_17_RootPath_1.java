package com.bestbuyapp;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class Topic_17_RootPath_1 {
	static ValidatableResponse validateResponse;
	
	static void print(String value) {
		System.out.println("-----------------------------");
		System.out.println(value);
		System.out.println("-----------------------------");
	}
	
	@BeforeAll
	public static void initialize() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 3030;
		validateResponse = given()
				.queryParam("$limit", 8)
				.queryParam("$skip", 2)
				.when()
				.get("/stores")
				.then();
	}
	
	@BeforeEach
	public void startTest() {
		System.out.println("----- START TEST -----");
	}
	
	@AfterEach
	public void endTest() {
		System.out.println("----- END TEST -----");
	}
	
	@DisplayName("Verify multiple values in the same statement")
	@Test
	public void TC_01_CheckMutipleValues() {
		validateResponse
		.root("data[0].services")
		.body("", hasItems(hasEntry("name", "Geek Squad Services")))
		.body("[0].storeservices", hasKey("createdAt"))
		.root("data.name")
		.body("", hasItems("Roseville", "Burnsville"))
		.body("", hasItem("Roseville"))
		.statusCode(200);
		
		// In this session, we learn using root path to set the constant path for the element.
		// You also can override the default root path anywhere in the verifying process.
	}
	
	
}
