package com.studentapp.logging;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;
import com.studentapp.model.StudentPojo;
import com.studentapp.tests.BaseTest;

import io.restassured.http.ContentType;

public class Topic_11_LoggingResponseValues extends BaseTest{
	
	@DisplayName("Log Response Details")
	@Test
	public void TC_01_LogResponseDetails() {
		System.out.println("---Print Response Details---");
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
		.log()
		.all()
		.statusCode(200);
	}
	
	@DisplayName("Log Response Details If test case failed")
	@Test
	public void TC_02_LogResponseDetailsIfTestCaseFailed() {
		System.out.println("---Print Response Details If Test Case Failed---");
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
		.pathParam("id", 1000)
		.body(student)
		.put("/{id}")
		.then()
		.log()
		.ifError()
		.statusCode(200);
	}
	
}
