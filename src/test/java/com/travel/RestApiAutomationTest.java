package com.travel;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class RestApiAutomationTest {
	
	@Test
	public void restAutomation()
	{
		given().
		when().
			get("http://demoqa.com/utilities/weather/city/Hyderabad").
		then().
		assertThat().statusCode(200).log().all();
	}

}
