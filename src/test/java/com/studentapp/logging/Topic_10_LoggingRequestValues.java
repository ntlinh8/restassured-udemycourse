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

public class Topic_10_LoggingRequestValues extends BaseTest{
	
	@DisplayName("Log Request Header")
	@Test
	public void TC_01_LogRequestHeader() {
		System.out.println("---Print Request Header---");
		given()
		.when()
		.log()
		.headers()
		.get("/1")
		.then()
		.statusCode(200);
	}
	
	@DisplayName("Log Request Paramters")
	@Test
	public void TC_02_LogRequestParameters() {
		System.out.println("---Print Request Parameters---");
		given()
		.when()
		.param("programe", "Computer Science")
		.param("limit", 1)
		.log()
		.params()
		.get("/1")
		.then()
		.statusCode(200);
	}
	
	@DisplayName("Log Request Body")
	@Test
	public void TC_03_LogRequestBody() {
		System.out.println("---Print Request Body---");
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
		.log()
		.body()
		.put("/{id}")
		.then()
		.statusCode(200);
	}
	
	@DisplayName("Log Request Details")
	@Test
	public void TC_04_LogRequestDetails() {
		System.out.println("---Print Request Details---");
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
		.log()
		.all()
		.put("/{id}")
		.then()
		.statusCode(200);
	}
	
	@DisplayName("Log Request If Test Case Failed")
	@Test
	public void TC_05_LogRequestIfTestCaseFailed() {
		System.out.println("---Print Request Details If Test Case Failed---");
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
		.log()
		.ifValidationFails()
		.put("/{id}")
		.then()
		.statusCode(201);
	}
	
}
