package com.studentapp.tests;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Topic_02_GetByQueryParam {
	
	@DisplayName("Get a single CS student in the list")
	@Test
	void getCSStudent() {
		// If you want to get data by query parameters
		// You can use the queryParam() or queryParams() methods
		// Below is an example
		
		// case 1: using queryParam() method
		given()
		.queryParam("programe", "Computer Science")
		.queryParam("limit", "1")
		.when()
		.get("http://localhost:8085/student/list")
		.then()
		.statusCode(200);
		
		// using queryParams() method
		given()
		.queryParams("programe", "Computer Science","limit", "1")
		.when()
		.get("http://localhost:8085/student/list")
		.then()
		.statusCode(200);
		
		// using queryParams with a map is an argument
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		given()
		.queryParams(parameterMap)
		.when()
		.get("http://localhost:8085/student/list")
		.then()
		.statusCode(200);
		
	}
}
