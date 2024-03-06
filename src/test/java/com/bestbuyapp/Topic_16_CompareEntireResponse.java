package com.bestbuyapp;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import io.restassured.RestAssured;

public class Topic_16_CompareEntireResponse {
	static String actualResponse;
	static String expectedResponse;
	
	static void print(String value) {
		System.out.println("-----------------------------");
		System.out.println(value);
		System.out.println("-----------------------------");
	}
	
	@BeforeAll
	public static void initialize() throws IOException {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 3030;
		actualResponse = given()
				.queryParam("$limit", 8)
				.queryParam("$skip", 2)
				.when()
				.get("/stores").asString();
		expectedResponse = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "ExpectedResponse.txt")));
	}
	
	@BeforeEach
	public void startTest() {
		System.out.println("----- START TEST -----");
	}
	
	@AfterEach
	public void endTest() {
		System.out.println("----- END TEST -----");
	}
	
	@DisplayName("Normal way => Using assert function from JUnit")
	@Test
	public void TC_01_VerifyTheLogicalAssertions() throws IOException {
		Assertions.assertEquals(expectedResponse, actualResponse);
		// It's ok until the problem occurs  
		// -> cannot show the specific character which are differed
	}
	
	@DisplayName("Using library to compare the json")
	@Test
	public void TC_02_VerifyTheLogicalAssertions() throws JSONException {
		JSONAssert.assertEquals(expectedResponse, actualResponse, JSONCompareMode.LENIENT);
	}
	
}
