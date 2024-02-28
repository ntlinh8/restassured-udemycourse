package com.studentapp.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class Topic_04_CreateBaseTest extends BaseTest{
	@DisplayName("Get student by id")
	@Test
	void getStudentByID() {
		given().pathParam("id", 20).get("/{id}").then().statusCode(200);
	}
}
