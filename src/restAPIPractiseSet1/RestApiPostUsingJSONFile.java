package restAPIPractiseSet1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import org.apache.commons.*;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;



public class RestApiPostUsingJSONFile {
	
	
	@Test
	public void postMethod() throws IOException
	{
		/*JSONObject json=new JSONObject();
		int randomID=new Random().nextInt(1000);
		json.put("id",randomID);
		json.put("title","my title" + randomID);
		json.put("author", "author" + randomID);*/
		FileInputStream fileInputStream=new FileInputStream(new File(".//jsonFileInput/jsonRequest1.json"));
		
		RestAssured.baseURI="http://localhost:3000/";
		
		given().
		        header("Content-Type","application/json").
		        body(IOUtils.toString(fileInputStream, "UTF-8")).
		when().
		       post("/posts")
		.then()
		       .statusCode(201)
		       .and()
		       .log().all();
	}
}
