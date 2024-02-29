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

public class Topic_15_HardAssertAndSoftAssert {
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
	
	@DisplayName("This is a hard assert -> If having errors, only showing the first error -> Stop test -> Not show all error")
	@Test
	public void TC_01_VerifyTheLogicalAssertions() {
		validateResponse
		.body("total", equalTo(1562))	//failure
		.body("total", greaterThan(100))
		.body("total", lessThan(15))	//failure
		.body("total", lessThanOrEqualTo(1561))
		.body("total", greaterThanOrEqualTo(1561));
	}
	
	@DisplayName("This is a soft assert -> all statements always run, show all errors")
	@Test
	public void TC_02_VerifyTheLogicalAssertions() {
		validateResponse
		.body("total", equalTo(1562),	//failure
			  "total", greaterThan(100), 
			  "total", lessThan(15), 	//failure
			  "total", lessThanOrEqualTo(1561), 
			  "total", greaterThanOrEqualTo(1561));
	}
	
}
