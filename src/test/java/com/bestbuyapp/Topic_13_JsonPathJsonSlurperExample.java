package com.bestbuyapp;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.Severity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class Topic_13_JsonPathJsonSlurperExample {
	static ValidatableResponse validatableResponse;
	
	static void print(String value) {
		System.out.println("-----------------------------");
		System.out.println(value);
		System.out.println("-----------------------------");
	}
	
	@BeforeAll
	public static void initialize() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 3030;
		validatableResponse = given().when().get("/stores").then();
	}
	
	@BeforeEach
	public void startTest() {
		System.out.println("----- START TEST -----");
	}
	
	@AfterEach
	public void endTest() {
		System.out.println("----- END TEST -----");
	}
	
	@DisplayName("Get total value")
	@Test
	public void TC_01_GetTotalValue() {
		int total = validatableResponse.extract().path("total");
		print(String.valueOf(total));
	}
	
	@DisplayName("Get the storeName from the first data element")
	@Test
	public void TC_02_GetStoreNameFromTheFirstDataElement() {
		String name = validatableResponse.extract().path("data[0].name");
		print(name);
	}
	
	@DisplayName("Get the first service name of the first service of the first store")
	@Test
	public void TC_03_GetTheFirstVersionName() {
		String firstService = validatableResponse.extract().path("data[0].services[0].name");
		print(firstService);
	}
	
	@DisplayName("Get all information of store with zip 55901")
	@Test
	public void TC_04_GetAllInformationOfStoreWithSpecificZip() {
		Map<String, ?> storeInformation = validatableResponse.extract().path("data.find{it.zip=='55901'}");
		print(storeInformation.toString());
	}
	
	@DisplayName("Get the specific address of story by the specific zip code")
	@Test
	public void TC_05_GetAddressBasedOnZipCode() {
		String address = validatableResponse.extract().path("data.find{it.zip=='55901'}.address");
		print(address);
	}
	
	@DisplayName("Get all information of store with max and min id")
	@Test
	public void TC_06_GetAllInformationOfStoreWithMaxAndMinID() {
		Map<String, ?> storeInformationWithMaxID = validatableResponse.extract().path("data.max{it.id}");
		print(storeInformationWithMaxID.toString());
		
		Map<String, ?> storeInformationWithMinID = validatableResponse.extract().path("data.min{it.id}");
		print(storeInformationWithMinID.toString());
	}
	
	@DisplayName("Get all information of store with id in range")
	@Test
	public void TC_07_GetAllInformationOfStoreWithIDInRange() {
		List<Map<String, ?>> listStore = validatableResponse.extract().path("data.findAll{it.id<10}");
		print(listStore.toString());
		listStore.stream().forEach(System.out::println);
	}
	
	@DisplayName("Get all the service name for all the stores")
	@Test
	public void TC_08_GetAllServiceNameForAllStores() {
		List<String> serviceList = validatableResponse.extract().path("data.services.name");
		print(serviceList.toString());
	}
	
	@DisplayName("Get the services id with store with specific ID")
	@Test
	public void TC_09_GetServiceIDListForSpecificStore() {
		List<Integer> serviceIDList = validatableResponse.extract().path("data.find{it.id==4}.services.id");
		print(serviceIDList.toString());
	}
}
