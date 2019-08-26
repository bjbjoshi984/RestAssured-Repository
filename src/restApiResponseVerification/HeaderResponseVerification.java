package restApiResponseVerification;

import org.testng.annotations.Test;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;

public class HeaderResponseVerification {

	
	// status code and status line verification
	//@Test
	public void testStatusInResponse()
	{
		given().get("https://jsonplaceholder.typicode.com/photos").then().assertThat().statusCode(200).log().all();
		given().get("https://jsonplaceholder.typicode.com/photos").then().assertThat().statusLine("HTTP/1.1 200 OK");
		given().get("https://jsonplaceholder.typicode.com/photos").then().assertThat().statusLine(containsString("OK"));
		
	}
	
	/**
	 * headers verification
	 */
	//@Test
	public void testHeadersInResponse()
	{
		given().get("https://jsonplaceholder.typicode.com/photos/4").then().assertThat().header("X-Powered-By","Express").log().all();
		given().get("https://jsonplaceholder.typicode.com/photos").then().assertThat().headers("Vary","Origin, Accept-Encoding","Content-Type",containsString("json"));
		
	}
	
	
	/**
	 * Body text verification
	 * 
	 */
	//@Test
	public void testBodyInResponse()
	{
		String responseString=get("https://jsonplaceholder.typicode.com/photos/4").asString();
		given().when().get("https://jsonplaceholder.typicode.com/photos/4").then().assertThat().body(equalTo(responseString));
		
	}
	/**
	 * Cookie value changing from every hit, this one will fail
	 */
	//@Test
	public void testCookiesInResponse()
	{
		given().when().get("https://jsonplaceholder.typicode.com/photos/4").then().log().all().assertThat().cookie("__cfduid","8776gugyftycvhbug7t766yfy");
	}
	 
	//body attribute verification using lamba 8 verification
	@Test
	public void testBodyParametersInresponse()
	{
		//using Java 7
		given().when().get("https://jsonplaceholder.typicode.com/photos/4").then().body("thumbnailUrl",new ResponseAwareMatcher<Response>() {
			@Override
			public Matcher<?> matcher(Response response) throws Exception {
				// TODO Auto-generated method stub
				return equalTo("https://via.placeholder.com/150/d32776");
			}
		});
		
		//using Java 8
		given().when().get("https://jsonplaceholder.typicode.com/photos/4").then().body("thumbnailUrl", response->equalTo("https://via.placeholder.com/150/d32776"));
		
		given().when().get("https://jsonplaceholder.typicode.com/photos/4").then().body("thumbnailUrl", endsWith("/150/d32776"));
		
	}
	
	
	
	
}
