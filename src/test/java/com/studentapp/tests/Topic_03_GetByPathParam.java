package com.studentapp.tests;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.response.Response;

public class Topic_03_GetByPathParam {
	
	@DisplayName("Get student by id")
	@Test
	void getStudentByID() {
		Response response = given().pathParam("id", "2").when().get("http://localhost:8085/student/{id}");
		response.prettyPrint();
		response.then().statusCode(200);

	}
}
