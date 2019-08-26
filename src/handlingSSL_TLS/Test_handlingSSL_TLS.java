package handlingSSL_TLS;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;

public class Test_handlingSSL_TLS {
	
	@BeforeClass
	public void setUp()
	{
		
		RestAssured.useRelaxedHTTPSValidation();
	}
	
	/* Handling SSL certificates*/
	@Test
	public void testSSL1()
	{
		/*In BeforeClass method we have already bypassed the SSL certificate error */
		given().get("https://www.bupa.com.au").then().statusCode(200);
		/*If you don't want to define in @BeforeClass method then we can write here itself  */
		given().relaxedHTTPSValidation().get("https://www.bupa.com.au").then().statusCode(200).log().all();
		
	}
	
	/* We can also handle TLS protocol using an overloaded method */
	@Test
	public void TLS()
	{
		given().relaxedHTTPSValidation("TLS").get("https://www.bupa.com.au").then().statusCode(200).log().all();
		
	}
	
	

}
