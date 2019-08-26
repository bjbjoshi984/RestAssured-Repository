package restAPIPractiseSet1;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class RestApiPostUsingJSONObject {
	@Test
	public void postMethod() throws IOException
	{
		JSONObject json=new JSONObject();
		int randomID=new Random().nextInt(1000);
		json.put("id",randomID);
		json.put("title","my title" + randomID);
		json.put("author", "author" + randomID);
		
		
		RestAssured.baseURI="http://localhost:3000/";
		
		given().
		        header("Content-Type","application/json").
		        body(json.toString()).
		when().
		       post("/posts")
		.then()
		       .statusCode(201)
		       .and()
		       .log().all();
	}
}

