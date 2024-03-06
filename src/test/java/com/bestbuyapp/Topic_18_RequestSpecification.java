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
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Topic_18_RequestSpecification {
	static RequestSpecBuilder builder;
	static RequestSpecification requestSpecification;
	
	static void print(String value) {
		System.out.println("-----------------------------");
		System.out.println(value);
		System.out.println("-----------------------------");
	}
	
	@BeforeAll
	public static void initialize() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 3030;
		
		// Builder must be added after setting Restassured base URL and port
		builder = new RequestSpecBuilder();
		builder.addQueryParam("$limit", 8);
		builder.addQueryParam("$skip", 2);
		requestSpecification = builder.build();
		
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
		 given()
		.spec(requestSpecification)
		.when()
		.get("/stores")
		.then()
		.body("data[0].services", hasItems(hasEntry("name", "Geek Squad Services")))
		.body("data[0].services[0].storeservices", hasKey("createdAt"))
		.body("data.name", hasItems("Roseville", "Burnsville"))
		.body("data.name", hasItem("Roseville"))
		.statusCode(200);
	}
	
	
}
