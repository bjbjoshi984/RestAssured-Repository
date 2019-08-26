package basicAndDigestiveAuthentication;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

public class Test_BasicAndDigestiveAuthentication {
	
	/*Basic authentication- In basic authentication we don’t pass the authentication to server explicitly, 
	 * when server explicitly asked for it only then credentials passed to servers in headers 
	 * along with rest of the credentials. */
	@Test
	public void basicTestChallengeAuthentication()
	{
		/*Don't have api which needs authorization */
		given().auth().basic("Username","Password").when().get("http://localhost:3000/posts").then().statusCode(200);
		
		////////////OR///////////////////
		RestAssured.authentication=basic("Username","Password");
		given().when().get("").then().statusCode(200);
	}
	
	
	/*Pre-emptive basic authentication- Credentials will be passed to server before it asks. */
	
	@Test
	public void basicPremptiveTestChallengeAuthentication()
	{
		/*Don't have api which needs authorization */
		given().auth().preemptive().basic("Username","Password").when().get("http://localhost:3000/posts").then().statusCode(200);
		
		////////////OR///////////////////
		RestAssured.authentication=basic("Username","Password");
		given().when().get("").then().statusCode(200);
	}
	
	@Test
	public void testDigestiveAUthentication()
	{
		given().auth().digest("Username","Password").when().get("http://localhost:3000/posts").then().statusCode(200);
		
		
	}

}
