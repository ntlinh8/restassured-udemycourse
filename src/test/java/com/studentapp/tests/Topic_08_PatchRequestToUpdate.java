package com.studentapp.tests;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;
import com.studentapp.model.StudentPojo;

import io.restassured.http.ContentType;

public class Topic_08_PatchRequestToUpdate extends BaseTest{
	
	@DisplayName("Update the student information by patch request")
	@Test
	void updateStudentInformationByPatchRequest() {
		StudentPojo student = new StudentPojo();
		Faker faker = new Faker();
		student.setEmail(faker.internet().emailAddress());
		
		given()
		.when()
		.contentType(ContentType.JSON)
		.when()
		.pathParam("id", 100)
		.body(student)
		.patch("/{id}")
		.then()
		.statusCode(201);
	}
}
