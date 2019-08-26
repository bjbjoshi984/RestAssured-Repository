package specBuilder;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Test_ResponseSpecBuilder {

	
	ResponseSpecification responseSpec;
	
	@BeforeClass
	public void setUp()
	{
		ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder();
		responseSpecBuilder.expectStatusCode(200);
		responseSpecBuilder.expectHeader("Content-Type", "application/json; charset=utf-8");
		responseSpecBuilder.expectHeader("Cache-Control","public, max-age=14400");
		responseSpec=responseSpecBuilder.build();
		
	}
	
	@Test
	public void testReponse1()
	{
	 when().get("https://jsonplaceholder.typicode.com/photos/1").then().spec(responseSpec).time(lessThan(4000L));
	}

}
