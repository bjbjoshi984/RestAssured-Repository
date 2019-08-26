package restAPIPractise;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;

public class RestAPIDeleteDemo {
	
	
	@Test
	public void postMethod()
	{
		
		RestAssured.baseURI="http://localhost:3000/";
		
		given().
		        header("content-type","application/json").
		        when().
		               delete("posts/21").
		               then().
		               statusCode(200).
		               log().all();
		        
		
	}

}
