package restAPIPractiseSet1;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.POJOInputClass;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;

public class RestApiPostJavaObject {
	
	/* Implicit serialization happens here */
	//@Test
	public void postMethodImplicitSerialization() throws IOException
	{
		int randomID= new Random().nextInt(1000);
		POJOInputClass info=new POJOInputClass(randomID,"Title"+randomID,"Author"+randomID);
		
		RestAssured.baseURI="http://localhost:3000/";
		
		given().
		        header("Content-Type","application/json").
		        body(info).
		when().
		       post("/posts")
		.then()
		       .statusCode(201)
		       .and()
		       .log().all();
	}
	
	/*We converted object into JSON object using ObjectMapperType.JACKSON_2 object */
	//@Test
	public void postMethodExplicitSerialization() throws IOException
	{
		int randomID= new Random().nextInt(1000);
		POJOInputClass info=new POJOInputClass(randomID,"Title"+randomID,"Author"+randomID);
		
		RestAssured.baseURI="http://localhost:3000/";
		
		given().
		        header("Content-Type","application/json").
		        body(info,ObjectMapperType.JACKSON_2).
		when().
		       post("/posts")
		.then()
		       .statusCode(201)
		       .and()
		       .log().all();
	}
	
                ////////////DESERIALIZATION////////////////////
	/* Deserialization of JSON object into JAVA object */
	
	@Test
	public void postMethodDeserialization() throws IOException
	{
		int randomID= new Random().nextInt(1000);
		POJOInputClass info=new POJOInputClass(randomID,"Title"+randomID,"Author"+randomID);
		
		RestAssured.baseURI="http://localhost:3000/";
		
		POJOInputClass pojoInputClassResponse= given().
		        header("Content-Type","application/json").
		        body(info).
		when().
		       post("/posts").as(POJOInputClass.class);
		
		Assert.assertTrue(pojoInputClassResponse.getId()>0);
		       
	}
	
	
	

}
