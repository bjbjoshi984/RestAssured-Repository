package restAPIPractise;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestApiPutDemo {
	
	@Test
	public void postMethod() throws IOException
	{	
		RestAssured.baseURI="http://localhost:3000/";
		
		Response response=given().
		        header("Content-Type","application/json").
		        body("{"+
                     "\"id\": 654," +
                     "\"title\": \"json-server654-again updated\","+
                     "\"author\": \"typicode654-again updated\" " +
                     "}").
		when().
		       put("/posts/654")
		.then()
		       .statusCode(200)
		       .and()
		       .log().all()
		       .extract().response();
		
		JsonPath jsonPath=new JsonPath(response.asString());
		System.out.println("ID is"+jsonPath.get("id"));
	}
	
}
	
