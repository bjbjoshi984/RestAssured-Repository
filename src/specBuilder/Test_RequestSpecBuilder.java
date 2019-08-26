package specBuilder;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;


public class Test_RequestSpecBuilder {
	
    RequestSpecification requestSpec;
	
    /* Checking for 400 API, don't have any good API for QA*/
	@BeforeClass
	public void setUp()
	{
		RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
		//requestSpecBuilder.addParam("paramter1","parameterValue");
		requestSpecBuilder.addHeader("accept-encoding","gzip, deflate, br");
		requestSpec=requestSpecBuilder.build();
		
	}
	
	@Test
	public void testReponse1()
	{
	 given().spec(requestSpec).when().get("https://jsonplaceholder.typicode.com/photos/1").then().statusCode(200).log().all();
	}

}
