package serialization;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class Test_serialization {
	
	//PATH=C:\Users\PC\json>
	/*Almost same thing we have done by using POJO class object on this path "estAPIPractiseSet1\RestApiPostJavaObject.java" */
	@Test
	public void testSerializationUsingHashMap()
	{
		
		// Java Objects as Map
		Map<String,String> input=new HashMap<String,String>();
		input.put("id", "4201");
		input.put("title", "json-server4201");
		input.put("author", "typeCode4201");
		
		given().relaxedHTTPSValidation().contentType("application/json").body(input).when().post("http://localhost:3000/posts").then().statusCode(201);
		
	}

}
