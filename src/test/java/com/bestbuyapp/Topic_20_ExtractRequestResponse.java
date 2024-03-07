package com.bestbuyapp;

import java.io.PrintStream;
import java.io.StringWriter;

import org.apache.commons.io.output.WriterOutputStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class Topic_20_ExtractRequestResponse {
	static StringWriter requestWriter;
	static PrintStream requestCapture;
	static StringWriter responseWriter;
	static PrintStream responseCapture;
	
	
	@BeforeAll
	public static void beforeAll() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 3030;
	}
	
	@AfterAll
	public static void afterAll() {
		RestAssured.reset();
	}
	
	@BeforeEach
	public void beforeTest() {
		System.out.println("------------Start Test---------------");
		requestWriter = new StringWriter();
		requestCapture = new PrintStream(new WriterOutputStream(requestWriter), true);
		responseWriter = new StringWriter();
		responseCapture = new PrintStream(new WriterOutputStream(responseWriter), true);
	}
	
	@AfterEach
	public void afterTest() {
		System.out.println("------------End Test---------------");
	}
	
	@DisplayName("TC01 - Extract Request Information")
	@Test
	public void TC_01_ExtractRequestInformation() {
		RestAssured
		.given()
		.filter(new RequestLoggingFilter(requestCapture))
		.filter(new ResponseLoggingFilter(responseCapture))
		.when()
		.get("/products");
		System.out.println(requestWriter);
		System.out.println(responseWriter);
	}
	
	
	
	
	
	
}
