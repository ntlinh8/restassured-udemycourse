package com.bestbuyapp;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;

public class Topic_12_JsonPathJaywayExample {
	static String jsonResponse;
	
	static void print(String value) {
		System.out.println("-----------------------------");
		System.out.println(value);
		System.out.println("-----------------------------");
	}
	
	@BeforeAll
	public static void initialize() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 3030;
		jsonResponse = given().when().get("/products").asString();
	}
	
	@BeforeEach
	public void startTest() {
		System.out.println("----- START TEST -----");
	}
	
	@AfterEach
	public void endTest() {
		System.out.println("----- END TEST -----");
	}
	
	@DisplayName("Get the root element")
	@Test
	public void TC_01_GetRootElement() {
		Map<String,?> rootElement = JsonPath.read(jsonResponse, "$");
		print(rootElement.toString());
	}
	
	@DisplayName("Get the total value")
	@Test
	public void TC_02_GetTotalValue() {
		int totalValue = JsonPath.read(jsonResponse, "$.total");
		print(String.valueOf(totalValue));
	}
	
	
	@DisplayName("Get data information")
	@Test
	public void TC_03_GetDataInformation() {
		List<HashMap<String, Object>> dataElements = JsonPath.read(jsonResponse, "$.data");
		print(dataElements.toString());
		dataElements.stream().forEach(System.out::println);
	}
	
	@DisplayName("Get the first data element")
	@Test
	public void TC_04_GetTheFirstDataElement() {
		Map<String, ?> firstDataElement = JsonPath.read(jsonResponse, "$.data[0]");
		print(firstDataElement.toString());
	}
	
	@DisplayName("Get the last data element")
	@Test
	public void TC_05_GetTheLastDataElement() {
		Map<String, ?> lastDataElement = JsonPath.read(jsonResponse, "$.data[-1]");
		print(lastDataElement.toString());
	}
	
	@DisplayName("Get all id of data elements")
	@Test
	public void TC_06_GetAllIDsOfDataElements() {
		List<String> allIDs = JsonPath.read(jsonResponse, "$.data[*].id");
		print(allIDs.toString());
	}
	
	@DisplayName("Get all id (consist of category id)")
	@Test
	public void TC_07_GetAllIDs() {
		List<String> allIDs = JsonPath.read(jsonResponse, "$..id");
		print(allIDs.toString());
	}
	
	@DisplayName("Get the name of the products whose price is less than 5")
	@Test
	public void TC_08_GetNameOfProductWithPriceLessThan5() {
		List<String> allProductNames = JsonPath.read(jsonResponse, "$.data[?(@.price>5)].name");
		print(allProductNames.toString());
		allProductNames.stream().forEach(System.out::println);
	}
	
	
	
}
