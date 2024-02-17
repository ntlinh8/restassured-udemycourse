package com.studentapp.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class Topic_00_CheckEnvironment {
	private void styles() {
		
		RestAssured.given()
					.queryParam("", "")
					.when()
					.get("https://www.google.com/")
					.then();
	}
	
	@DisplayName("Getting all the student from the database")
	@Test
	void getAllStudents01(){
		// This is the unrecommended way when using Restassured
		// You can define each variable from request to response but it'll make you to be boring
		// Let start with other way
		RequestSpecification requestSpec = RestAssured.given();
		Response response = requestSpec.get("http://localhost:8085/student/list");
		response.prettyPrint();
		ValidatableResponse validataResponse =  response.then();
		validataResponse.statusCode(200);
		
		
	}
	
	@DisplayName("Getting all the student from the database")
	@Test
	void getAllStudents02() {
		// There're 2 way to implement but it's not different
		
		RestAssured.given()
		.when()
		.get("http://localhost:8085/student/list")
		.then()
		.statusCode(200);
		
		RestAssured.given()
		.expect()
		.statusCode(200)
		.when()
		.get("http://localhost:8085/student/list");
		
		
	}
}
