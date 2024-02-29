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

public class Topic_14_Assert {
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
	
	@DisplayName("Verify the total value")
	@Test
	public void TC_01_VerifyTotal() {
		validateResponse.body("total", equalTo(1561));
	}
	
	@DisplayName("Verify name of store with ignore case")
	@Test
	public void TC_02_VerifyNameOfStore() {
		validateResponse.body("data[0].name", equalToIgnoringCase("roseville"));
	}
	
	@DisplayName("Check Single name in Array List")
	@Test
	public void TC_03_VerifySingleNameInArrayLists() {
		validateResponse.body("data.name", hasItem("Roseville"));
	}
	
	@DisplayName("Check multiple name in Array List")
	@Test
	public void TC_04_VerifyMultipleNameInArrayList() {
		validateResponse.body("data.name", hasItems("Roseville", "Burnsville"));
	}
	
	@DisplayName("Check the list of response consist of a key")
	@Test
	public void TC_05_VerifyMapContainKeyValue() {
		validateResponse.body("data[0].services[0].storeservices", hasKey("createdAt"));
	}
	
	@DisplayName("Check hasmap value inline a list")
	@Test
	public void TC_06_VerifyHashMapInlineAList() {
		validateResponse.body("data[0].services", hasItems(hasEntry("name", "Geek Squad Services")));
	}
	
	@DisplayName("Verify multiple values in the same statement")
	@Test
	public void TC_07_VerifyMultipleValueInTheSameStatement() {
		validateResponse
		.body("data[0].services", hasItems(hasEntry("name", "Geek Squad Services")))
		.body("data[0].services[0].storeservices", hasKey("createdAt"))
		.body("data.name", hasItems("Roseville", "Burnsville"))
		.body("data.name", hasItem("Roseville"))
		.statusCode(200);
		
	}
	
	@DisplayName("Check the Logical Assertions")
	@Test
	public void TC_08_VerifyTheLogicalAssertions() {
		validateResponse
		.body("total", equalTo(1561))
		.body("total", greaterThan(100))
		.body("total", lessThan(1562))
		.body("total", lessThanOrEqualTo(1561))
		.body("total", greaterThanOrEqualTo(1561));
		
		
		
	}
	
}
