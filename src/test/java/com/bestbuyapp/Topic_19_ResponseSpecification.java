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
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Topic_19_ResponseSpecification {
	static RequestSpecBuilder builder;
	static RequestSpecification requestSpecification;
	static ResponseSpecBuilder responseBuilder;
	static ResponseSpecification responseSpecification;
	static void print(String value) {
		System.out.println("-----------------------------");
		System.out.println(value);
		System.out.println("-----------------------------");
	}
	
	@BeforeAll
	public static void initialize() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 3030;
		
		// Builder must be added after setting RestAssured base URL and port
		builder = new RequestSpecBuilder();
		builder.addQueryParam("$limit", 8);
		builder.addQueryParam("$skip", 2);
		requestSpecification = builder.build();
		
		responseBuilder = new ResponseSpecBuilder();
		responseBuilder.expectHeader("Content-Type", "application/json; charset=utf-8");
		responseBuilder.expectHeader("Content-Encoding", "gzip");
		responseBuilder.expectHeader("Connection", "keep-alive");
		responseBuilder.expectStatusCode(200);
		responseBuilder.expectBody("data[0].services", hasItems(hasEntry("name", "Geek Squad Services")));
		responseSpecification = responseBuilder.build();
		
	}

	@BeforeEach
	public void startTest() {
		System.out.println("----- START TEST -----");
	}
	
	@AfterEach
	public void endTest() {
		System.out.println("----- END TEST -----");
	}
	
	@DisplayName("TC01: Verify multiple values in the same statement")
	@Test
	public void TC_01_CheckMutipleValues() {
		given()
		.spec(requestSpecification)
		.when()
		.get("/stores")
		.then()
		.spec(responseSpecification);
		
	}
	
	@DisplayName("TC02: Verify multiple values in the same statement")
	@Test
	public void TC_02_CheckMutipleValues() {
		 given()
		.spec(requestSpecification)
		.when()
		.get("/stores")
		.then()
		.spec(responseSpecification)
		.body("data.name", hasItems("Roseville", "Burnsville"));
	}
	
	
}
