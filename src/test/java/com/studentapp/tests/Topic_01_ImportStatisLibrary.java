package com.studentapp.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class Topic_01_ImportStatisLibrary {
	
	@DisplayName("Getting all the student from the database")
	@Test
	void getAllStudents() {
		// To avoid the getting static methods from RestAssured library, 
		// you can import them by static methods and using them directly
		// Let check the 6th line and you will see the import static method statement
		// And when using these methods, you can use them directly instated of calling by library
		// Let check the 18th statement, RestAssured.given() is replaced by given()
		given()
		.when()
		.get("http://localhost:8085/student/list")
		.then()
		.statusCode(200);
		
		
	}
}
