package restAPIPractiseSet1;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestApiPostJsonString {

	@Test
	public void postMethod() throws IOException
	{	
		RestAssured.baseURI="http://localhost:3000/";
		
		Response response=given().
		        header("Content-Type","application/json").
		        body("{"+
                     "\"id\": 502," +
                     "\"title\": \"json-server502\","+
                     "\"author\": \"typicode502\" " +
                     "}").
		when().
		       post("/posts")
		.then()
		       .statusCode(201)
		       .and()
		       .log().all()
		       .extract().response();
		
		JsonPath jsonPath=new JsonPath(response.asString());
		System.out.println("ID is"+jsonPath.get("id"));
	}
	
}
