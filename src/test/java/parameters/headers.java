package parameters;


import static io.restassured.RestAssured.get;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class headers {
	
	@Test
	void getHeaderValues() {
		
		Response res = get("https://www.google.com/"); 
		
		String contentType = res.getHeader("Content-Type");
		
		System.out.println(contentType);
		
		 Headers myheaders = res.getHeaders();
		 
		 for(Header allheaders:myheaders) {
			 
			 System.out.println(allheaders.getName()+ "                            "+allheaders.getValue());
			 
		 }
 	

}
	
	void testLogs() {
		
		given()
		
		.when()
		
		.then()
		
		.log().body()
		.log().cookies();
		
	}
}
