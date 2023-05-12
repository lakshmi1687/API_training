package authentication;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

public class JWTToken implements Tokens {
	@Test
	void jwtToken() {
		
		given()
		
		.header("Authorization","Bearer " + bearerToken)
		
		.when()
		
		.get(bearerTokenURL)
		
		.then()
		.statusCode(200)
		.log().all();
	}

}
