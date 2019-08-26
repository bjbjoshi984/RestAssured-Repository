package restApiPractiseSet2;

import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

public class SetRequestData {

	//@Test
	public void testConnectRequest()
	{
		when().request("CONNECT","https://api.fonts.com/rest/json/Accounts").
		then().statusCode(400);
		
	}
	
	//@Test
	public void testQueryParameter()
	{
		given().
		   queryParam("A","A Vol").
		   queryParam("B","B Vol").
		   when().get("https://api.fonts.com/rest/json/Accounts").
		   then().statusCode(400);
		
	}
	
	//@Test
	public void testFormParamter()
	{
		given().
		   formParam("A","A Vol").
		   formParam("B","B Vol").
		   when().post("https://api.fonts.com/rest/json/Accounts").
		   then().statusCode(400);
		
		
	}
	
	//@Test
	public void testSetParamter()
	{
		   given().
		   param("A","A Vol").
		   param("B","B Vol").
		   when().get("https://api.fonts.com/rest/json/Accounts").
		   then().statusCode(400);
		
		
	}
	
	//@Test
	public void testSetMultiValueParameter()
	{
		List<String> list=new ArrayList<String>();
		list.add("one");
		list.add("two");
		
		given().
		param("A","vol1","vol2","vol3").
		param("B").
		param("C",list).
		when().get("https://api.fonts.com/rest/json/Accounts").
		then().statusCode(400);
		
		
		
	}
	
	
	//If we have dynamic values in the URL which can be changed in that case we will use path parameter.
	@Test
	public void testPathParamter()
	{
		given().
		pathParam("page","posts").pathParam("id","654").
		when().get("http://localhost:3000/{page}/{id}/").then().statusCode(200).log().all();
		
	}
	
}
