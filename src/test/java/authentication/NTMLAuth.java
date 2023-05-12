package authentication;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

public class NTMLAuth implements Tokens {

	@Test
	void ntmlAuth() {
		
		given()
		.auth().ntlm(userName, password, workStation, domain)
		
		.when()
		.get(ntlmAuthURL)
		
		.then()
		.statusCode(200)
		.log().all();
		
		
	}
}
