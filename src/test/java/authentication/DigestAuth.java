package authentication;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class DigestAuth implements Tokens {
	
	@Test
	void digestAuth() {
		
		given()
		.auth().digest(userName, password)
		
		.when()
		.get(basicAuthUrl)
		
		.then()
		.statusCode(200)
		.log().body()
		.body("authenticated", equalTo(true));
		
	}

}
