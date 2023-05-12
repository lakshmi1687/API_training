package authentication;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;


public class BasicAuth implements Tokens {
	
	@Test
	void basicAuthentication() {
		
		given()
		  .auth().basic(userName, password)
		
		.when()
		  .get(basicAuthUrl)
		   
		.then()
		   .statusCode(200)
		   .log().body();
		
	}

}
