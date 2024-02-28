package com.studentapp.tests;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

public class Topic_09_DeleteStudentById extends BaseTest{
	
	@DisplayName("Delete student by id")
	@Test
	void deleteStudentById() {
		given()
		.when()
		.contentType(ContentType.JSON)
		.when()
		.pathParam("id", 100)
		.delete("/{id}")
		.then()
		.statusCode(204);
	}
}
