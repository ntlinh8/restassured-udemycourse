package com.studentapp.tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;
import com.studentapp.model.StudentPojo;

import io.restassured.http.ContentType;

public class Topic_06_JavaFakerLibrary extends BaseTest{
	
	@DisplayName("Update the Student information")
	@Test
	void updateStudentInformationByPutRequest() {
		
		StudentPojo student = new StudentPojo();
		List<String> courses = new ArrayList<String>();
		courses.add("Math");
		courses.add("Finance");
		
		Faker faker = new Faker();
		
		student.setFirstName(faker.name().firstName());
		student.setLastName(faker.name().lastName());
		student.setEmail(faker.internet().emailAddress());
		student.setProgramme("Computer Science");
		student.setCourses(courses);
		
		given()
		.when()
		.contentType(ContentType.JSON)
		.when()
		.pathParam("id", 100)
		.body(student)
		.put("/{id}")
		.then()
		.statusCode(200);
	}
}
