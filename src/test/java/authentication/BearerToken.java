package authentication;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class BearerToken implements Tokens {
	@Test
	void bearerToken() {
		
		given()
		
		   .header("Authorization","Bearer "+bearerToken)
		
		.when()
		.get(bearerTokenURL)
		
		.then()
		.statusCode(200)
		.log().all();
	}

}
