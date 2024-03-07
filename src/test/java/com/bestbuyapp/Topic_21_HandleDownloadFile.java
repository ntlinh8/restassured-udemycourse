package com.bestbuyapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;

public class Topic_21_HandleDownloadFile {
	
	@BeforeEach
	public void beforeTest() {
		System.out.println("------------Start Test---------------");
	}
	
	@AfterEach
	public void afterTest() {
		System.out.println("------------End Test---------------");
	}
	
	@DisplayName("Check download file success and length of file")
	@Test
	public void TC_01_Download() {
		File inputFile = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + 
				"geckodriver-v0.34.0-win32.zip");
		
		// Check file size
		int expectedSize = (int) inputFile.length();
		
		System.out.println("The size of the expected file is " + expectedSize + " bytes");
		
		// Read the downloaded file
		byte[] actualValue = RestAssured
								.given()
								.when()
								.get("https://github.com/mozilla/geckodriver/releases/download/v0.34.0/geckodriver-v0.34.0-win32.zip")
								.then()
								.extract()
								.asByteArray();
		
		System.out.println("The size of the actual file is " + actualValue.length + " bytes");
		assertEquals(expectedSize, actualValue.length);
	}
}
