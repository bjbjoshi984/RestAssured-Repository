package restAPIPractiseSet1;

import org.testng.annotations.Test;

import groovy.time.BaseDuration.From;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.apache.http.entity.mime.content.ByteArrayBody;
import org.hamcrest.Matcher;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class RestAPIGetDemo {

	
	//given: can have headers(),parameters(),cookies(),body(),contentType(),relexHTTPSValidation()
	//when:  get(),post(),put(),Delete()
	//then:  assertTha(),statusCode(),log().all().
	
	//@Test  /* Json File Validation */
	public void getMethod()
	{
		RestAssured.baseURI="http://localhost:3000/";
		
		given().contentType("application/json").
		when().get("/posts/1").
		then().body("id",equalTo(1)).body("title",equalTo("json-server")).body("author",equalTo("typicode")).log().all();
		
		
	}
	
	//@Test  /* Json File Validation */
	public void getMethod1()
	{
		RestAssured.baseURI="http://localhost:3000/";
		
		String responseString=given().contentType("application/json").
		when().get("/posts").
		then().body("author",hasItems("typicode","Author508")).log().all().extract().asString();
		
		JsonPath jsonPath=new JsonPath(responseString);
		
		List<String> list=jsonPath.get("id");
		
		System.out.println(list.size());
		
	}
	// @Test /* XML File validation*/
	 public void getMethod2()
		{
			RestAssured.baseURI="http://www.thomas-bayer.com/sqlrest/";
			
			given().contentType("application/json").
			when().get("CUSTOMER/10").
			then().body("CUSTOMER.ID",equalTo("10")).
			body("CUSTOMER.FIRSTNAME",equalTo("Sue")).
			body("CUSTOMER.LASTNAME",equalTo("Fuller")).log().all();
			
			
		}
	 
	 //if we want to verify the text in one go
	 
	 //@Test /* XML File validation*/
	 public void getMethod3()
		{
			RestAssured.baseURI="http://www.thomas-bayer.com/sqlrest/";
			
			given().contentType("application/json").
			when().get("CUSTOMER/10").
			then().body("CUSTOMER.text()",equalTo("10SueFuller135 Upland Pl.Dallas")).
			body(hasXPath("/CUSTOMER/FIRSTNAME",containsString("Sue"))).log().all();
			
			
		}
	 
	//@Test /* XML File validation*/
	 public void getMethod4()
		{
			RestAssured.baseURI="http://www.thomas-bayer.com/sqlrest/";
			
			given().contentType("application/json").
			when().get("INVOICE/").
			then().
			body(hasXPath("/INVOICEList/INVOICE[text()='40']")).log().all();
			
			
		}
	 
	 //Code for attaching the root or we can call this setting the root
	 //@Test
	 public void testAfterSettingTheRoot()
		{
			
			String response=given().
			when().get("https://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22").
			then().root("weather").body("id",contains(800)).
		    body("main",contains("Clear")).body("description",contains("clear sky")).body("icon",contains("01n")).log().all().extract().asString();
			
			JsonPath jsonPath=new JsonPath(response);
			
			System.out.println("ID is"+jsonPath.get("weather.id")); 
		 
		}
	 
	//Code for Deattching the root or we can call deattach the root
		// @Test
		 public void testAfterDeattchingTheRoot()
			{
				
				given().
				when().get("https://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22").
				then().root("weather").body("id",contains(800)).
			    body("main",contains("Clear")).body("description",contains("clear sky")).body("icon",contains("01n")).
			    detachRoot("weather").log().all();
				 
			 
			}
       //@Test
       public void responseAsAString()
       {
    	   String responseString=get("https://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22").asString();
    	   System.out.println("Reponse String:"+responseString);
       }
       
       //@Test
       public void responseAsAInputStream() throws IOException
       {
    	   InputStream stream=get("https://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22").asInputStream();
    	   System.out.println("Stream Length:"+stream.toString().length());
    	   stream.close();
       }
       
      //@Test
       public void responseAsByteArray() throws IOException
       {
    	   byte[] byteArray=get("https://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22").asByteArray();
    	   System.out.println("Straem Length:"+byteArray.length);
       }
       
      //@Test
      public void getTheURLFromResponseAndHitTheURL()
      {
    	  //Type 1
    	  String href=given().when().get("https://jsonplaceholder.typicode.com/photos/1").then().contentType(ContentType.JSON).
    			  body("albumId",equalTo(1)).extract().path("url");
    	  System.out.println(href);
    	  when().get(href).then().statusCode(200);
    	  
    	  //Type 2
    	  String href1=given().when().get("https://jsonplaceholder.typicode.com/photos/1").path("url");
          System.out.println(href1);
    	  when().get(href1).then().statusCode(200);
    	  
    	  //Type3
    	  String href2=given().when().get("https://jsonplaceholder.typicode.com/photos/1").andReturn().jsonPath().getString("url");
          System.out.println(href1);
    	  when().get(href1).then().statusCode(200);
    	  
    	  
    }
     
      //Get the response object and get the values it has
      //@Test
      public void testExtractDetailsUsingResponse()
      {
    	  Response response=when().get("https://jsonplaceholder.typicode.com/photos/1").then().extract().response();
    	  
    	  System.out.println("Content Type"+response.contentType());
    	  System.out.println("URL"+response.path("url"));
    	  System.out.println("Status code"+ response.getStatusCode());
    	  
      }
      
      //Schema Validator
      //@Test
       public void matchTheSchemaValidator()
       {
    	  given().
    	          get("https://jsonplaceholder.typicode.com/photos/1").
    	          then().
    	             assertThat().body(matchesJsonSchemaInClasspath(".//restAPIPractise/photos.json"));
    	  
    	  
       }
       
      //Used to compare the length of a specific response
      //@Test
      public void totalLengthOfResponse()
      {
    	  when().
    	        get("https://jsonplaceholder.typicode.com/photos").
    	        then().
    	        body("title*.length().sum()",greaterThan(15)).log().all();
    	  
      }
      
      //Get the all the attributes of a file
      //@Test
      public void getTheAttribute()
      {
    	  
	      String response=get("https://jsonplaceholder.typicode.com/photos").asString();
	        
	      //JsonPath jsonPath=new JsonPath(response);
	    //List<String> list=jsonPath.get("title");
	      List<String> list=JsonPath.from(response).get("title");
	      for(String title:list)
	      {
	    	  System.out.println(title);
	      }
    	  
    	  
      }
      
      // Extract details as string and fetching further details using json Path
      
     //@Test
     public void testJsonpath()
     {
    	 String json=when().get("https://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22").
    			 then().extract().asString();
    	 
    	 JsonPath jsonpath=new JsonPath(json).setRoot("weather");
    	 List<String> list=jsonpath.get("name");
    	 System.out.println(list.size());
    	 
    	 
     }
     
     //Verify Response Headers
     //@Test
     public void testResponseHeaders()
     {
    	 Response response=get("https://jsonplaceholder.typicode.com/photos");
    	 
    	 String headerCFRAY=response.getHeader("CF-RAY");
    	 
    	 System.out.println("Header CF-RAY: "+headerCFRAY);
    	 
    	 Headers allHeaders=response.getHeaders();
    	 
    	 for(Header h:allHeaders)
    	 {
    		 System.out.println(h.getName()+": "+h.getValue());
    		 
    	 }
    	 
     }
      
     //Get the all the cookies
     //@Test
     public void testCookie()
     {
    	 Response response=get("https://jsonplaceholder.typicode.com/photos");
    	 
    	 Map<String,String> cookies=response.getCookies();
    	 
    	 for(Map.Entry<String, String> entry: cookies.entrySet())
    	 {
    		 
    		 System.out.println(entry.getKey()+": "+entry.getValue() );
    	 }
    }
     
     @Test
     public void testDetailedCookies()
     {
    	 Response response=get("https://jsonplaceholder.typicode.com/photos");
    	 
    	 Cookie cookie=response.getDetailedCookie("__cfduid");
    	 
    	 System.out.println("Detailed:"+ cookie.hasExpiryDate());
    	 System.out.println("Detailed:"+ cookie.getExpiryDate());
    	 System.out.println("Detailed:"+ cookie.hasValue());
    	 
    	 
    	 
    	 
     }
     
     
     
}