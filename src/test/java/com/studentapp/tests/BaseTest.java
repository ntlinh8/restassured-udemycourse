package com.studentapp.tests;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class BaseTest {
	@BeforeAll
	public static void init() {
		baseURI = "http://localhost";
		port = 8085;
		basePath = "/student";
	}
}
