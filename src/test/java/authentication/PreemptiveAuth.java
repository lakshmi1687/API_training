package authentication;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class PreemptiveAuth implements Tokens {
	@Test
	void apiKey() {
		
		given()
		.auth().preemptive().basic(userName, password)
		
		.when()
		   .get(basicAuthUrl)
		
		.then()
		 .statusCode(200)
		 .log().body();
		
		
		
	}

}
