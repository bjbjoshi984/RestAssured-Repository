package parsers;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import static io.restassured.RestAssured.given;

public class Test_Parsers 
{
	
    /* Registering parser at Rest Assured level*/  
	//@Test
	public void testDefaultParser()
	{
		/* This can be done in beforeClass */
		//Any one can be used
		RestAssured.defaultParser=Parser.JSON;
		RestAssured.defaultParser=Parser.XML;
		RestAssured.defaultParser=Parser.HTML;
		
	}
	
	/* Registering parser at Script level */
	//@Test
	public void testDefaultParser1()
	{
		given().get("http://localhost:3000/posts").then().using().defaultParser(Parser.XML).log().all();
	
	}
	
	/*Registering custom parser */
	
	//@Test
	public void testCustomParser()
	{
		/* This can be done in beforeClass */
		RestAssured.registerParser("application/vnd.uoml+xml",Parser.XML);
		RestAssured.unregisterParser("application/vnd.uoml+xml");
		
	}
	
	@Test
	public void testCustomParser2()
	{
		given().get("http://localhost:3000/posts").then().using().parser("application/vnd.uoml+xml",Parser.XML).log().all();
		
	}
	
	

}
