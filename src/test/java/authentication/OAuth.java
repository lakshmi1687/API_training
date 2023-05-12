package authentication;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;


public class OAuth implements Tokens{
	
	@Test
	void oAuthRequest() {
		
		given()
		  .auth().oauth2(bearerToken)
		
		.when()
		  .get(bearerTokenURL)
		
		.then()
		.statusCode(200)
		.log().all();
	}

}
