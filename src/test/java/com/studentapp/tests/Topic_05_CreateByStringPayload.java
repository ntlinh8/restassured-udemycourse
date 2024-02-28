package com.studentapp.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.studentapp.model.StudentPojo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Topic_05_CreateByStringPayload extends BaseTest{
	
	@DisplayName("Create a new student by payload as a string")
	@Test
	void createStudentByPayloadAsString() {
		// Using https://jsonviewer.stack.hu/ to format json to string
		String payload = "{\"firstName\":\"Sunny\",\"lastName\":\"Nguyen\",\"email\":\"thuylinhnguyen.hust@gmail.com\",\"programme\":\"Financial Analysis\",\"courses\":[\"Accounting\",\"Statistics\"]}";
		RestAssured.given()
		.when()
		.contentType(ContentType.JSON)
		.when()
		.body(payload)
		.post()
		.then()
		.statusCode(201);
	}
	
	@DisplayName("Create a new student by payload as an object")
	@Test
	void createStudentByPayloadAsObject() {
		StudentPojo student = new StudentPojo();
		List<String> courses = new ArrayList<String>();
		courses.add("Math");
		courses.add("Finance");
		
		student.setFirstName("Jenny");
		student.setLastName("Nguyen");
		student.setEmail("jenny@gmail.com");
		student.setProgramme("Computer Science");
		student.setCourses(courses);
		
		RestAssured.given()
		.when()
		.contentType(ContentType.JSON)
		.when()
		.body(student)
		.post()
		.then()
		.statusCode(201);
	}
}
